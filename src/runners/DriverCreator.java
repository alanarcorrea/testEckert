package runners;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

public class DriverCreator {

	public WebDriver chromeDriver() {
		WebDriver driver;

		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setBrowserName("Chrome browser");
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		// cap.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY,
		// "chromedriver.exe");
		try {
			driver = new ChromeDriver(cap);
			driver.manage().window().maximize();
		} catch (Exception e) {
			e.printStackTrace();
			driver = null;
		}

		return driver;

		// WebDriver driver = new ChromeDriver();
		// return driver;*/
	}



	public WebDriver firefoxDriver() {
		WebDriver driver;
		FirefoxProfile firefoxProfile;
		try {

			File prof = new File("automateTesting/k3b7du0t.default");
			firefoxProfile = new FirefoxProfile(prof);// application/Portable
														// Document Format
														// application/Adobe
														// Acrobat Document
														// //C:/Users/lluz/AppData/Roaming/Mozilla/Firefox/Profiles/k3b7du0t.default
			// firefoxProfile.addExtension(new
			// File("k3b7du02t.default/extensions/keyconfig.xpi"));

		} catch (Exception e) {
			//e.printStackTrace();
			firefoxProfile = new FirefoxProfile();

		}
		// Sets the pdf and xls files to be saved automatically.
		firefoxProfile
				.setPreference(
						"browser.helperApps.neverAsk.saveToDisk",
						"application/xlsx,application/xls,application/excell,application/octet-stream,application/pdf,application/Portable Document Format");

		// Used for the download .xls files test. Sets the default download
		// folder to be inside the project.
		new File("downloads").mkdirs();
		File folder = new File("downloads");
		firefoxProfile.setPreference("browser.download.dir", folder.getAbsolutePath());
		firefoxProfile.setPreference("browser.download.folderList", 2);
		driver = new FirefoxDriver(firefoxProfile);

		return driver;
	}
}
