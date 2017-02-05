package util;

import static test.AbstractTestCase.driver;
import static util.Finders.buttonListFinder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.generalPopup.ListPopup;

public class Helpers {
	
	public static boolean isElementPresent(By by){
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
	
	public static boolean isElementPresent(WebElement element, By by){
	    try {
	      element.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	}
	
	public static Object searchForTextAndSelect(WebElement container, String text, int index){
		By basicSearchTextFieldFinder = By.id("nameInput");
		By suggestedUserFinder = By.cssSelector(".suggestion-box-list a.btn-user");
		WebElement textBox = container.findElement(basicSearchTextFieldFinder);
		textBox.sendKeys(text);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		while(Util.isLoadingTextVisible());
		try{
			driver.findElements(suggestedUserFinder).get(index).click();
		}catch(NoSuchElementException | ElementNotVisibleException | IndexOutOfBoundsException e){
			return null;
		}
		return new Object();
	}
	
	
	
	
	public static boolean isTextPresent(WebElement element, String text){
	    
		return element.getText().contains(text); 
	    
	}
	
	public static boolean isTableEmpty(WebElement table){
		By emptyCellFinder = By.cssSelector(".empty-cell");
		if(isElementPresent(table, emptyCellFinder) && table.findElement(emptyCellFinder).isDisplayed()){
			return true;
		}else{
			return false;
		}
	}
	
	public static ListPopup clickListInsideDropDown(WebElement element){
		By dropDownFinder = By.cssSelector(".DropdownWrapper");
		int numberOfPopups = Util.getNumberOfPopupsOpen();
		try{
			WebElement dropdown =  Util.getVisibleItems(element, dropDownFinder).get(0);
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			if(!Util.isElementPresent(element, By.cssSelector(".DropdownManagerContainer.z-index-over"))){
				dropdown.findElement(By.cssSelector(".DropdownValue")).click();
			}
		
			Util.getVisibleItems(dropdown, buttonListFinder).get(0).click();
		
		}catch(NoSuchElementException | IndexOutOfBoundsException e){
			return null;
		}
		return new ListPopup(numberOfPopups);
	}
	
	public static int getNumberOfPopupsOpen(){
		try{
			while(Util.isLoadingTextVisible());
			WebDriverEckert.changeTimeout( 0);
			int n = driver.findElements(By.cssSelector(".popup")).size();
			WebDriverEckert.changeTimeout( 2);
			return n;
		}catch(Exception e){
			WebDriverEckert.changeTimeout( 2);
			return 0;
		}
	}
	
	public static void checkBox(WebElement element){
		if(!element.isSelected()){
			element.click();
		}
		
	}
	
	public static void unCheckBox(WebElement element){
		if(element.isSelected()){
			element.click();
		}
		
	}
	
	public static void checkAllBoxes(WebElement element){
		ArrayList<WebElement> boxes = Util.getVisibleItems(element, "input");
		for(WebElement box : boxes){
			if(box.getAttribute("type").equals("checkbox") && !box.isSelected()){
				box.click();
			}
		}
		
	}
	
	public static void unCheckAllBoxes(WebElement element){
		ArrayList<WebElement> boxes = Util.getVisibleItems(element, "input");
		for(WebElement box : boxes){
			if(box.getAttribute("type").equals("checkbox") && box.isSelected()){
				box.click();
			}
		}
		
	}
	
	public static WebElement getCurrentPopup(){
		try{
			int index = driver.findElements(By.cssSelector(".popup")).size();
			return driver.findElements(By.cssSelector(".popup")).get(index-1);
		}catch(Exception e){
			System.out.println("Not inside any Popup");
			return null;
		}
	}
	

	public static boolean isValidationMessageVisible() {
		try {
			return driver.findElement(By.cssSelector(".error")).isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}
}
