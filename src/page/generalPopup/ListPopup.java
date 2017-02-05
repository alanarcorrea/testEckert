package page.generalPopup;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static test.AbstractTestCase.driver;
import static util.Assertions.*;
import util.Helpers;
import util.Util;
import static util.Finders.*;
public class ListPopup extends Popup{
	WebElement popup, table;
	By listTable = By.id("table");
	By searchFieldId = By.id("searchField");
	By searchButtonFinder = By.cssSelector(".btn-search");
	By tableNormal = By.cssSelector(".table-normal");
	
	public ListPopup(){
		popup = Util.getCurrentPopup();
		while(Util.isLoadingTextVisible());
		assertTrue("This is not a list popup", !popup.getAttribute("class").contains("template-form"));
		table = popup.findElement(listTable);
		
	}
	public ListPopup(int numberOfPopupsOpenBefore){
		while(Util.isLoadingTextVisible());
		popup = Util.getCurrentPopup();
		assertTrue("The list popup failed to open", numberOfPopupsOpenBefore<Util.getNumberOfPopupsOpen());
		table = popup.findElement(tableNormal);
	}
	
	
	
	public boolean selectAnyElement(){
		Random generator = new Random();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		ArrayList<WebElement> buttonSelects = (ArrayList<WebElement>) table.findElements(buttonSelectFinder);
		if(buttonSelects.size()==0){
			return false;
		}else{
			int chosen = generator.nextInt(buttonSelects.size());
			buttonSelects.get(chosen).click();
			while(Util.isLoadingTextVisible());
			return true;
		}
		
	}
	
	public boolean lookForSomethingInAllPages(String text){
		boolean textFound = false;
		ListPopup thisListPopup = this;
		if(thisListPopup.getNumberOfPages()==0){
			textFound = thisListPopup.isTextPresentOnPopup(text);
		}else{
			for(int i = 0; i<thisListPopup.getNumberOfPages(); i++){
				thisListPopup = thisListPopup.changePage(i);
				textFound = thisListPopup.isTextPresentOnPopup(text);
				if(textFound){
					return true;
				}
			}
		}
		return textFound;
	}
	
	public String getRowTextByIndex(int index){
		try{
			ArrayList<WebElement> tableRows = (ArrayList<WebElement>) table.findElements(tableRowsFinder);
			return tableRows.get(index).getText();
		}catch(NoSuchElementException | ElementNotVisibleException | IndexOutOfBoundsException e){
			return null;
		}
	}
	public ListPopup changePage(int index){
		popup.findElements(paginationButtonFinder).get(index).click();
		return new ListPopup(Util.getNumberOfPopupsOpen()-1);
	}
	
	public int getNumberOfPages(){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			return popup.findElements(paginationButtonFinder).size();
		}catch(NoSuchElementException | ElementNotVisibleException | IndexOutOfBoundsException e){
			return 0;
		}
		
	}
	
	public boolean isTextPresentOnPopup(String text){
		return Helpers.isTextPresent(table, text);
	}
	public boolean selectFirstElement() {
		ArrayList<WebElement> buttonSelects = (ArrayList<WebElement>) table.findElements(buttonSelectFinder);
		if(buttonSelects.size()==0){
			return false;
		}else{
			buttonSelects.get(0).click();
			while(Util.isLoadingTextVisible());
			return true;
		}
		
	}
	
	public int getNumberOfCandidates() {
		ArrayList<WebElement> buttonSelects = (ArrayList<WebElement>) table.findElements(buttonSelectFinder);
		return buttonSelects.size();
		
	}
	
	public boolean selectElementByIndex(int index){
		ArrayList<WebElement> buttonSelects = (ArrayList<WebElement>) table.findElements(buttonSelectFinder);
		if(buttonSelects.size()==0 || buttonSelects.size() < index){
			return false;
		}else{
			buttonSelects.get(index).click();
			while(Util.isLoadingTextVisible());
			return true;
		}
		
	}
	
	
	public ListPopup searchForSomething(String searchFor){
		popup.findElement(searchFieldId).sendKeys(searchFor);
		popup.findElement(searchButtonFinder).click();
		while(Util.isLoadingTextVisible());
		return this;
		
	}
	
	public void clickSaveButton(){
		popup.findElement(buttonSave).click();
		while(Util.isLoadingTextVisible());
	}
}
