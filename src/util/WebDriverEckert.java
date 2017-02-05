package util;

import static test.AbstractTestCase.driver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WebDriverEckert {

	public WebElement findElement(By by) {
		try {
			return driver.findElement(by);
		} catch (Exception e) {
			return null;
		}
	}

	public static WebElement findElement(WebElement container, By by) {
		try {
			return container.findElement(by);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<WebElement> findElements(WebElement container, By by) {
		try {
			return (ArrayList<WebElement>) container.findElements(by);
		} catch (Exception e) {
			return null;
		}
	}

	public static ArrayList<WebElement> findElements(By by) {
		try {
			return (ArrayList<WebElement>) driver.findElements(by);
		} catch (Exception e) {
			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByCssSelector(String cssSelector) {
		try {
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println("Fail to get: " + cssSelector);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByCssSelector(WebElement driver, String cssSelector) {
		try {
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println("Fail to get: " + cssSelector);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByCssSelector(String cssSelector, String errorMessage) {
		try {
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			if (errorMessage.equals(""))
				return null;
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByCssSelector(WebElement driver, String cssSelector, String errorMessage) {
		try {
			return driver.findElement(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println("Fail to get: " + errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByCssSelector(String cssSelector) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println("Fail to get: " + cssSelector);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByCssSelector(WebElement driver, String cssSelector) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println("Fail to get: " + cssSelector);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByCssSelector(WebElement driver, String cssSelector,
			String errorMessage) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByCssSelector(String cssSelector, String errorMessage) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.cssSelector(cssSelector));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByName(String name) {
		try {
			return driver.findElement(By.name(name));
		} catch (Exception e) {
			System.out.println("Fail to get: " + name);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByName(WebElement driver, String name) {
		try {
			return driver.findElement(By.name(name));
		} catch (Exception e) {
			System.out.println("Fail to get: " + name);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByName(String name, String errorMessage) {
		try {
			return driver.findElement(By.name(name));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByName(WebElement driver, String name, String errorMessage) {
		try {
			return driver.findElement(By.name(name));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByName(String name) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.name(name));
		} catch (Exception e) {
			System.out.println("Fail to get: " + name);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByName(String name, String errorMessage) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.name(name));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByName(WebElement driver, String name, String errorMessage) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.name(name));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByName(WebElement driver, String name) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.name(name));
		} catch (Exception e) {
			System.out.println("Fail to get: " + name);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementById(String id) {
		try {
			return driver.findElement(By.id(id));
		} catch (Exception e) {
			System.out.println("Fail to get: " + id);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementById(WebElement driver, String id) {
		try {
			return driver.findElement(By.id(id));
		} catch (Exception e) {
			System.out.println("Fail to get: " + id);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementById(String id, String errorMessage) {
		try {
			return driver.findElement(By.id(id));
		} catch (Exception e) {
			if (errorMessage.equals(""))
				return null;
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementById(WebElement driver, String id, String errorMessage) {
		try {
			return driver.findElement(By.id(id));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsById(String id) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.id(id));
		} catch (Exception e) {
			System.out.println("Fail to get: " + id);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsById(WebElement driver, String id) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.id(id));
		} catch (Exception e) {
			System.out.println("Fail to get: " + id);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByLinkText(String linkText) {
		try {
			return driver.findElement(By.linkText(linkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + linkText);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByLinkText(WebElement driver, String linkText) {
		try {
			return driver.findElement(By.linkText(linkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + linkText);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByLinkText(String linkText, String errorMessage) {
		try {
			return driver.findElement(By.linkText(linkText));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByLinkText(WebElement driver, String linkText, String errorMessage) {
		try {
			return driver.findElement(By.linkText(linkText));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByLinkText(String linkText) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.linkText(linkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + linkText);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByLinkText(WebElement driver, String linkText) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.linkText(linkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + linkText);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByPartialLinkText(String partialLinkText) {
		try {
			return driver.findElement(By.partialLinkText(partialLinkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + partialLinkText);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByPartialLinkText(WebElement driver, String partialLinkText) {
		try {
			return driver.findElement(By.partialLinkText(partialLinkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + partialLinkText);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByPartialLinkText(String partialLinkText, String errorMessage) {
		try {
			return driver.findElement(By.partialLinkText(partialLinkText));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static WebElement findElementByPartialLinkText(WebElement driver, String partialLinkText, String errorMessage) {
		try {
			return driver.findElement(By.partialLinkText(partialLinkText));
		} catch (Exception e) {
			System.out.println(errorMessage);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByPartialLinkText(String partialLinkText) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.partialLinkText(partialLinkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + partialLinkText);

			return null;
		}
	}

	@Deprecated
	public static ArrayList<WebElement> findElementsByPartialLinkText(WebElement driver, String partialLinkText) {
		try {
			return (ArrayList<WebElement>) driver.findElements(By.partialLinkText(partialLinkText));
		} catch (Exception e) {
			System.out.println("Fail to get: " + partialLinkText);

			return null;
		}
	}

	public static void changeTimeout(int seconds) {
		driver.manage().timeouts().implicitlyWait(seconds, TimeUnit.SECONDS);
	}
}
