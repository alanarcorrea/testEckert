package util;

import static test.AbstractTestCase.driver;
import static test.AbstractTestCase.isRedesign;
import static test.AbstractTestCase.properties;
import static util.Assertions.assertFalse;
import static util.Assertions.assertNotNull;
import static util.Assertions.assertTrue;
import static util.Finders.templatePageContainer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import runners.DriverCreator;
import test.AbstractTestCase;






/************************************This class is used by the system internally, not relevant for the exam *******************************************************/




public class Util {
	public static String buttonSelectSelector = ".btn-select", buttonUnselectSelector = ".btn-unselect",
			buttonListSelector = ".btn-list", buttonAddSelector = ".btn-add";

	public static ArrayList<WebElement> getPageTabs() {
		try {
			WebElement templateContainer = driver.findElement(By.cssSelector(".template-page"));
			return (ArrayList<WebElement>) templateContainer.findElements(By.cssSelector(".list-tabs")).get(0)
					.findElements(By.cssSelector("li a"));
		} catch (Exception e) {
			return (ArrayList<WebElement>) driver.findElements(By.cssSelector(".list-tabs")).get(0)
					.findElements(By.cssSelector("li a"));
		}
	}

	public static String getTemplateTitle() {
		try {
			return Util.getVisibleItems(".template-page .internal-box-title h3").get(0).getText();
		} catch (NullPointerException e) {
			return null;
		}
	}


	public static boolean isScreenLockerVisible() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			return driver.findElement(By.id("screenlocker")).isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} finally {
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
	}

	public static void takeScreenshot(String name) {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = AbstractTestCase.path;
		/*
		if(path.contains("_15")){
			try {
				FileUtils.deleteDirectory(new File("AutomatedScreenshots"));
			} catch (IOException e) {
				
			}
		}*/
		try {

			// new File(path).mkdirs();
			String fileName = name + ".jpeg";
			File screenshot = new File(path + "\\Screenshots " + driver.getTitle() + "\\" + fileName);
			System.out.println(path + fileName);
			FileUtils.copyFile(scrFile, screenshot);
		} catch (IOException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public static void takeScreenshot() {
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = AbstractTestCase.path;
		/*
		if(path.contains("_15")){
			try {
				FileUtils.deleteDirectory(new File("AutomatedScreenshots"));
			} catch (IOException e) {
				
			}
		}*/
		try {

			// new File(path).mkdirs();
			String fileName = Util.getClassWhoCallMethod() + ".jpeg";
			File screenshot = new File(path + "\\Screenshots " + driver.getTitle() + "\\" + fileName);
			System.out.println(path + fileName);
			FileUtils.copyFile(scrFile, screenshot);
		} catch (IOException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}

	}

	public static String getClassWhoCallMethod() {

		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		int index = 0;
		for (int i = 0; i < st.length; i++) {
			if (st[i].getClassName().contains("test")) {
				index = i;
				break;
			}
		}
		return (st[index].getClassName().split("test.")[1] + "." + st[index].getMethodName());
	}

	public static void setDate(Object element) {
		WebElement popup;
		ArrayList<WebElement> datepickers;
		if (element != null) {
			popup = (WebElement) element;
			datepickers = Util.getVisibleItems(popup, ".ui-datepicker-trigger");
			assertTrue("No date pickers", datepickers.size() > 0);
			datepickers.get(0).click();
			Util.getVisibleItems(".ui-datepicker-week-end a").get(0).click();
			while (Util.isLoadingTextVisible());
			if (properties.get("browser").equals("chrome")) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			datepickers.get(1).click();
			while (Util.isLoadingTextVisible());

			Util.getVisibleItems(".ui-datepicker-week-end a").get(1).click();
		} else {
			datepickers = Util.getVisibleItems(".ui-datepicker-trigger");
			assertTrue("No date pickers", datepickers.size() > 0);
			datepickers.get(0).click();
			driver.findElement(By.cssSelector(".ui-datepicker-week-end a")).click();
			datepickers.get(1).click();
			driver.findElements(By.cssSelector(".ui-datepicker-week-end a")).get(1).click();
		}
	}

	public static void setOneDate(Object element) {
		WebElement popup;
		ArrayList<WebElement> datepickers;
		if (element != null) {
			popup = (WebElement) element;
			datepickers = Util.getVisibleItems(popup, ".ui-datepicker-trigger");
			assertNotNull("No date pickers", datepickers);
			assertTrue("No date pickers", datepickers.size() > 0);
			datepickers.get(0).click();
			Util.getVisibleItems(".ui-datepicker-week-end a").get(0).click();
		} else {
			datepickers = Util.getVisibleItems(".ui-datepicker-trigger");
			assertNotNull("No date pickers", datepickers);
			assertTrue(datepickers.size() > 0);
			datepickers.get(0).click();
			driver.findElement(By.cssSelector(".ui-datepicker-week-end a")).click();

		}
	}

	public static ArrayList<String> getLabels() {
		ArrayList<String> labels = new ArrayList<String>();
		for (WebElement element : Util.getVisibleItems(Util.getCurrentPopup(), ".popup td.label")) {
			labels.add(element.getText());
		}
		return labels;
	}
	
	public static void setRedesignFinders() {
		if (isRedesign) {
			Finders.templateManagerButton = By.cssSelector(".editSE");
			Finders.buttonListTemplate = By.cssSelector(".view-listSE");
			Finders.buttonExport = By.cssSelector(".exportSE");
		}
	}

	public static WebDriver initialize(String url) {
		DriverCreator driverCreator = new DriverCreator();
		String browserName = properties.getProperty("browser");
		if (browserName.equalsIgnoreCase("firefox")) {
			driver = driverCreator.firefoxDriver();
		}
		/*if (properties.getProperty("browser").equalsIgnoreCase("phantomjs")) {
			driver = driverCreator.phantomJsDriver();
		}*/
		if (properties.getProperty("browser").equalsIgnoreCase("chrome")) {
			driver = driverCreator.chromeDriver();
		}
		driver.get(url);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		while (Util.isScreenLockerVisible())
			;
		while (Util.isLoadingTextVisible())
			;
		while (Util.isLoadingTextVisible())
			;
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		//
		if (Util.isElementPresent(By.id("loginWrapper"))) {
			Util.login();
			Util.wait(5000);
		}
		while(Util.isScreenLockerVisible() || Util.isLoadingTextVisible());
		
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		System.out.println("Page title is:" + driver.getTitle());
		WebDriverWait wait = new WebDriverWait(driver, 60);

		if (Util.isElementPresent(By.cssSelector(".admin-navigation"))) {
			isRedesign = true;
			Actions action = new Actions(driver);
			action.moveToElement(Util.getVisibleItems(By.cssSelector(".admin-dropdown")).get(0)).perform();
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".BUTTON_LOGOUT")));
		} else {
			isRedesign = false;
			wait.until(ExpectedConditions.elementToBeClickable((By.id("userLogout"))));
		}
		
		while(isLoadingTextVisible());

		Util.setRedesignFinders();

		Util.makeSureIsEnglish();
		while (Util.isLoadingTextVisible())
			;

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("pageContentManager.currentPage.template.w.footerBox.stopTimer();");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}


	public static ArrayList<WebElement> getTabs() {
		return WebDriverEckert.findElementsByCssSelector(".popup .list-tabsa li");
	}

	public static ArrayList<WebElement> getPageTopMenu() {
		return WebDriverEckert.findElementsByCssSelector(".top-navigation");
	}

	public static ArrayList<WebElement> getCurrentSubMenu() {
		return WebDriverEckert.findElementsByCssSelector(".sub-navigation");
	}


	public static boolean goToFirstElement() {
		while (Util.isLoadingTextVisible());
		try {
			Util.getVisibleItems(".table-normal tbody>tr a").get(0).click();
			while (Util.isLoadingTextVisible());
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public static int getNumberOfPopupsOpen() {
		try {
			while (Util.isLoadingTextVisible());
			WebDriverEckert.changeTimeout(0);
			int n = driver.findElements(By.cssSelector(".popup")).size();
			WebDriverEckert.changeTimeout(10);
			return n;
		} catch (Exception e) {
			WebDriverEckert.changeTimeout(10);
			return 0;
		}
	}


	public static WebElement getCurrentPopup() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		try {
			int index = driver.findElements(By.cssSelector(".popup")).size();
			return driver.findElements(By.cssSelector(".popup")).get(index - 1);
		} catch (Exception e) {
			System.out.println("Not inside any Popup");
			return null;
		}
	}

	public static String getErrorMessage() {
		return driver.findElement(By.cssSelector(".tooltip-error .tooltip-content ul li")).getText();
	}

	public static boolean isDropdown(WebElement element) {
		WebElement dropdown;
		try {
			dropdown = element.findElement(By.cssSelector(".popup .DropdownWrapper"));
			if (dropdown.isDisplayed()) {
				return true;
			}
			return false;
		} catch (NoSuchElementException e) {
			return false;
		}

	}

	public static boolean isDateField(WebElement element) {
		String dateField;
		dateField = element.getAttribute("class");
		if (dateField != null && dateField.contains("hasDatepicker")) {
			return true;
		}
		return false;

	}

	public static String getTextOfDropdownElementByIndex(WebElement element, int index) {

		try {

			ArrayList<WebElement> dropdown = Util.getVisibleItems(element, ".DropdownWrapper");
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			dropdown.get(0).findElement(By.cssSelector(".DropdownValue")).click();
			ArrayList<WebElement> options = (ArrayList<WebElement>) dropdown.get(0).findElements(
					By.cssSelector(".DropdownManagerList li"));

			String text = options.get(index).getText();
			return text;

		} catch (Exception e) {
			return null;
		} finally {
			if (element.findElement(By.cssSelector(".DropdownManagerList")).isDisplayed()) {
				element.findElement(By.cssSelector(".DropdownValue")).click();
			}
		}
	}

	public static boolean selectElementByIndex(WebElement element, int index) {

		try {
			ArrayList<WebElement> dropdown = Util.getVisibleItems(element, ".DropdownWrapper");
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			dropdown.get(0).findElement(By.cssSelector(".DropdownValue")).click();
			dropdown.get(0).findElements(By.cssSelector(".DropdownManagerList li")).get(index).click();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void assertThatFieldDoesntContainTrash(String textFromTextField) {
		assertFalse("The field is empty.", textFromTextField.isEmpty());
		textFromTextField = textFromTextField.toLowerCase();
		assertFalse("object object is shown in the textfield instead of content",
				textFromTextField.contains("object object"));
		assertFalse("null is shown in the textfield instead of content", textFromTextField.contains("null"));
		assertFalse("not defined is shown in the textfield instead of content",
				textFromTextField.contains("not defined"));
		assertFalse("not.defined is shown in the textfield instead of content",
				textFromTextField.contains("not.defined"));
		assertFalse("n.n object is shown in the textfield instead of content", textFromTextField.contains("n.n"));
		assertFalse("notdefined is shown in the textfield instead of content", textFromTextField.contains("notdefined"));
		assertFalse("undefined is shown in the textfield instead of content", textFromTextField.contains("undefined"));

	}

	public static boolean isTextBox(WebElement element) {
		String type = element.getAttribute("type");
		if (type != null && type.equals("text")) {
			return true;
		}
		return false;
	}

	public static boolean isWritable(WebElement textField) {
		String readonly = textField.getAttribute("readonly");
		if (readonly != null && readonly.equals("true")) {
			return false;
		}
		if (textField.isEnabled() == false) {
			return false;
		}
		return true;
	}

	public static boolean isLabel(WebElement element) {
		String classs = element.getAttribute("class");
		if (classs != null & classs.contains("label")) {
			return true;
		}
		return false;
	}

	public static boolean isTextArea(WebElement element) {
		String tagName = element.getTagName();
		if (tagName != null && tagName.equals("textarea")) {
			return true;
		} else {
			return false;
		}

	}
	
	public static void wait(int milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<WebElement> getLateralTabs() {
		ArrayList<WebElement> tabs = new ArrayList<WebElement>();
		try {
			tabs = WebDriverEckert.findElementsByCssSelector(Util.getCurrentPopup(), ".list-tabsa li a");
		} catch (NoSuchElementException e) {
			// do nothing, just leave the array empty.
		}
		return tabs;
	}


	public static String cleanString(String toBeCleaned) {

		toBeCleaned = toBeCleaned.replace("  ", " ");
		toBeCleaned = toBeCleaned.replaceAll("\n", "");
		toBeCleaned = toBeCleaned.trim();
		return toBeCleaned;
	}

	public static String cleanNewLines(String toBeCleaned) {
		if (toBeCleaned == null) {
			return null;
		}
		for (int i = 0; i < 20; i++) {
			toBeCleaned = toBeCleaned.replace("\n", " ");
		}
		for (int i = 0; i < 20; i++) {
			toBeCleaned = toBeCleaned.replace("\n", "");
		}
		return toBeCleaned;
	}

	public static String cleanSpaces(String toBeCleaned) {
		for (int i = 0; i < 5; i++) {
			toBeCleaned = toBeCleaned.replace(" ", "");
		}
		return toBeCleaned;
	}

	public static String cleanIFrameText(String toBeCleaned) {
		assertNotNull(toBeCleaned);
		toBeCleaned = toBeCleaned.replaceAll("</.*>", ""); // [<(*)><(*)>]
		toBeCleaned = toBeCleaned.replaceAll("<.*>", "");
		toBeCleaned = Util.cleanString(toBeCleaned);
		return toBeCleaned;
	}

	public static boolean isTabSelected(WebElement button) {
		String klass = button.getAttribute("class");
		if (klass.contains("active")) {
			return true;
		}
		return false;
	}

	

	public static void checkAllBoxes(WebElement element) {
		ArrayList<WebElement> boxes = Util.getVisibleItems(element, "input");
		for (WebElement box : boxes) {
			if (box.getAttribute("type").equals("checkbox") && !box.isSelected()) {
				box.click();
			}
		}

	}

	public static void unCheckAllBoxes(WebElement element) {
		ArrayList<WebElement> boxes = Util.getVisibleItems(element, "input");
		for (WebElement box : boxes) {
			if (box.getAttribute("type").equals("checkbox") && box.isSelected()) {
				box.click();
			}
		}

	}

	public static void insertName(String name) {
		Util.getCurrentPopup().findElement(By.name("name")).clear();
		Util.getCurrentPopup().findElement(By.name("name")).sendKeys(name);
	}
	


	public static ArrayList<WebElement> getVisibleItems(WebElement element, By by) {
		ArrayList<WebElement> visibleItems = new ArrayList<WebElement>();
		for (WebElement e : element.findElements(by)) {
			if (e.isDisplayed()) {
				visibleItems.add(e);
			}
		}
		return visibleItems;
	}

	public static WebElement getTemplatePage() {
		return Util.getVisibleItems(templatePageContainer).get(0);
	}

	public static ArrayList<WebElement> getVisibleItems(By by) {
		ArrayList<WebElement> visibleItems = new ArrayList<WebElement>();
		for (WebElement e : driver.findElements(by)) {
			if (e.isDisplayed()) {
				visibleItems.add(e);
			}
		}
		return visibleItems;
	}

	@Deprecated
	public static ArrayList<WebElement> getVisibleItems(String cssSelector) {
		ArrayList<WebElement> visibleItems = new ArrayList<WebElement>();
		for (WebElement e : driver.findElements(By.cssSelector(cssSelector))) {
			if (e.isDisplayed()) {
				visibleItems.add(e);
			}
		}
		return visibleItems;
	}

	@Deprecated
	public static ArrayList<WebElement> getVisibleItems(WebElement driver, String cssSelector) {

		ArrayList<WebElement> visibleItems = new ArrayList<WebElement>();
		for (WebElement e : driver.findElements(By.cssSelector(cssSelector))) {
			if (e.isDisplayed()) {
				visibleItems.add(e);
			}
		}
		return visibleItems;
	}





	/*
	 * Is intended to be called inside a loop, so it holds the execution of the code until the loading text on the top right of the screen disappear.
	 * 
	 */
	public static boolean isLoadingTextVisible() {
		try {

			WebDriverEckert.changeTimeout(0);

			if (driver.findElement(By.id("generalLoader")).isDisplayed()
					|| driver.findElement(By.id("loadingApplication")).isDisplayed()) {

				Thread.sleep(300);
				return true;
			} else {

				return false;
			}

		} catch (Exception e) {
			try {
				if (driver.findElement(By.id("loadingApplication")).isDisplayed()) {
					return true;
				}
			} catch (Exception ex) {
				return false;
			}
			return false;
		}
	}

	public static boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static boolean isElementPresent(WebElement driver, By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public static void closeAllPopup() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		while (Util.getNumberOfPopupsOpen() > 0) {
			System.out.println("Some popup has failed to close.");
			ArrayList<WebElement> visibleItems = new ArrayList<WebElement>();
			visibleItems = Util.getVisibleItems(".popup .btn-close");
			visibleItems.get(visibleItems.size() - 1).click();
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}
	
	public static void login() {
		WebElement login = driver.findElement(By.name("userId"));
		login.sendKeys("alana");
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("alana");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		password.submit();
		System.out.println("Submitted password");
		try {
			System.out.println("Before ts");
			Thread.sleep(1000);
			System.out.println("After ts");
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Before while");
		while (Util.isLoadingTextVisible());
		System.out.println("Finished login");
	}

	public static void makeSureIsEnglish() {

		String txt = driver.findElement(By.id("search-field")).getAttribute("Value");
		while (Util.isLoadingTextVisible());
		while(Util.isScreenLockerVisible());
		// js.executeScript("$('#screenlocker').remove()");

		if (isRedesign) {
			Actions action = new Actions(driver);
			action.moveToElement(Util.getVisibleItems(By.cssSelector(".admin-dropdown")).get(0)).perform();
			driver.findElement(By.cssSelector(".dropdown-items")).findElements(Finders.li).get(0).findElement(By.cssSelector(".button")).click();
		} else {
			driver.findElement(By.id("userFullNameButton")).click();
		}
		
		Select clickThis = new Select(driver.findElement(By.name("locale")));
		clickThis.selectByVisibleText("English");
		Util.getCurrentPopup().findElement(By.cssSelector(".btn-save")).click();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		while (Util.isLoadingTextVisible())
			;
		
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		while ((driver.findElement(By.id("loadingApplication")).isDisplayed()))
			;
		while (Util.isLoadingTextVisible())
			;

	}

	
	public static boolean clickEditTemplateButton() {
		while (getNumberOfPopupsOpen() > 0) {
			System.out.println("Some popup has failed to close.");
			Util.getVisibleItems(".btn-close").get(0).click();
		}
		try {
			Util.getVisibleItems(".btn-template-edit").get(0).click();
		} catch (IndexOutOfBoundsException e) {
			while (Util.isLoadingTextVisible());
			return false;
		}

		if (WebDriverEckert.findElementByCssSelector(".btnEditTemplate", "Fail to click Add button.").isDisplayed()) {
			WebDriverEckert.findElementByCssSelector(".btnEditTemplate", "Fail to click Add button.").click();
			while (Util.isLoadingTextVisible());
			return true;
		} else {
			try {
				Util.getVisibleItems(".btn-template-edit").get(0).click();
			} catch (IndexOutOfBoundsException e) {
				while (Util.isLoadingTextVisible());
				return false;
			}
			WebDriverEckert.findElementByCssSelector(".btnEditTemplate", "Fail to click Add button.").click();
			while (Util.isLoadingTextVisible());
			return true;
		}
	}

	public static boolean isTemplateLoading() {
		try {
			return driver.findElement(By.cssSelector(".loading-template")).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}
