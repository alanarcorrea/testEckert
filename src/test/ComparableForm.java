package test;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

public abstract class ComparableForm {

	public HashMap<String, String[]> errorFields = new HashMap<>();

	public int arrayPosition = 5;

	public String getMessage() {
		String message = "";
		for (String key : errorFields.keySet()) {
			String[] values = errorFields.get(key);
			message += values[0] + "----" + values[1] + "\n";
		}
		return message;
	}

	@Override
	public boolean equals(Object otherFormObject) {

		Field[] fields = this.getClass().getDeclaredFields();
		Field[] otherFields = otherFormObject.getClass().getDeclaredFields();
		System.out.println("class" + this.getClass() + "- other declar" + this.getClass().getDeclaringClass() + "Enc class" + this.getClass().getEnclosingClass());
		for (int i = 0; i < fields.length; i++) {
			try {
				Field field = fields[i];
				if (field.getName().contains("this$"))
					continue;
				Field otherField = otherFields[i];

				// Makes the field be acessible even if it's declared
				// private.
				field.setAccessible(true);
				otherField.setAccessible(true);
				if (field.get(this) instanceof Collection) {
					Collection<?> valuesList = (Collection<?>) field.get(this);
					Collection<?> otherValuesList = ((Collection<?>) otherField.get(otherFormObject));
					field.setAccessible(false);
					otherField.setAccessible(false);
					if (otherValuesList.size() != valuesList.size()) {
						errorFields.put(field.getName(),
								new String[] { field.getName() + "= size:" + "\"" + valuesList.size() + "\"", " size: " + "\"" + otherValuesList.size() + "\"" });
						continue;
					}

					int k = 0;
					Iterator<?> it = otherValuesList.iterator();
					for (Object t : valuesList) {
						ComparableForm comparableForm = null;
						if (t instanceof ComparableForm) {
							comparableForm = (ComparableForm) t;
							comparableForm.arrayPosition = k;
						}
						if (!t.equals(it.next())) {

							if (comparableForm != null)
								errorFields.putAll(comparableForm.errorFields);
						}
						// return false;

						k++;
					}
				} else {

					Object value = field.get(this);
					Object otherValue = otherField.get(otherFormObject);

					field.setAccessible(false);
					otherField.setAccessible(false);
					String[] fieldErrors = new String[2];
					if (value == null) {
						if (otherValue != null) {
							fieldErrors[0] = this.getClass() + ", i: " + this.arrayPosition + ", Field:" + field.getName() + "\n\"" + null + "\"";
							fieldErrors[1] = "\"" + otherValue.toString() + "\"";
							errorFields.put(this.getClass().getName() + field.getName(), fieldErrors);
							// return false;
						}
					} else {
						if (!value.equals(otherValue)) {
							if (value instanceof ComparableForm) {

								errorFields.putAll(((ComparableForm) value).errorFields);

							} else {
								fieldErrors[0] = this.getClass() + ", i: " + this.arrayPosition + ", Field: " + field.getName() + "\n\"" + (value != null ? value.toString() : null)
										+ " \"";
								fieldErrors[1] = "\"" + (otherValue != null ? otherValue.toString() : null) + "\"";
								errorFields.put(this.getClass().getName() + field.getName(), fieldErrors);
							}
							// return false;
						}
					}

				}
			} catch (SecurityException e) {

				e.printStackTrace();
				return false;
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return false;
			}
		}
		if (!errorFields.isEmpty())
			return false;
		return true;
	}
}
