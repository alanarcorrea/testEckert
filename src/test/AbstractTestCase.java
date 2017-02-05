package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import util.Util;

public class AbstractTestCase {
	public static WebDriver driver;
	public static JavascriptExecutor js;
	public static boolean isRedesign;
	public static boolean needUrls = true;
	public static HashMap<String, String> urls;
	public static Properties properties = new Properties();
	public static HashMap<String, ArrayList<String>> names = new HashMap<String, ArrayList<String>>();
	public static ArrayList<String> topMenuNames = new ArrayList<String>();
	public static boolean generalTests;
	public static String path;
	public static boolean isSuit;
	public static boolean isOnTemplatePage;
	public static String loggedUser;

	public AbstractTestCase(String name) {
		super();
	}

	@BeforeClass
	public static void intialize() {

		if (driver == null) {
			try {
				properties.load(new FileInputStream("src/config.properties"));

				driver = Util.initialize(properties.getProperty("address"));
			} catch (IOException e) {
				System.out.println("Not possible to load config file, using localhost:8080 as url.");
				e.printStackTrace();
				driver = Util.initialize("http://localhost:8080/");
			}

		}
		//Used for pathing where to take a screenshot. Not relevant for the exam.
		if (path == null) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd- HH-mm-ss");
			Date date = new Date();
			File file = new File("dateFolder.txt");
			try {
				BufferedReader fileReader = new BufferedReader(new FileReader(file));
				path = fileReader.readLine();
				file.deleteOnExit();
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Impossible to get the date from the file 'dateFolder' error: " + e.getMessage());
				System.out.println("Creating path in default location");
				path = "automateTesting\\AutomatedScreenshots\\" + driver.getTitle() + "- " + dateFormat.format(date);

			}

		}
		if (js == null) {
			js = (JavascriptExecutor) driver;
		}

		if (loggedUser == null) {

			loggedUser = (String) js.executeScript("return global.currentUser.fullName");
		}
	}

	public AbstractTestCase() {
		super();

	}

	@AfterClass
	public static void closeDriver() {
		if (!isSuit) {
			Util.takeScreenshot("before close");
			driver.close();
			driver.quit();
			
		}
	}

	public void setUpMenus() {
		topMenuNames.add("Templates");
		topMenuNames.add("Process Templates");
		ArrayList<String> subMenu = new ArrayList<String>();
		subMenu.add("First sub menu");
		names.put(topMenuNames.get(0), subMenu);
		subMenu = new ArrayList<String>();
		subMenu.add("Second sub menu");
		names.put(topMenuNames.get(1), subMenu);
	}

	public static String getTopMenuUrl(int topMenuIndex) {
		String url = topMenuNames.get(topMenuIndex);
		url = url.replaceAll(" ", "-");
		url = url.toLowerCase();
		return url;
	}

	public static String getSubMenuUrl(int topMenuIndex, int subMenuIndex) {
		String url = names.get(topMenuNames.get(topMenuIndex)).get(subMenuIndex);
		url = url.replaceAll(" ", "-");
		url = url.toLowerCase();
		return getTopMenuUrl(topMenuIndex) + "/" + url;
	}

}
