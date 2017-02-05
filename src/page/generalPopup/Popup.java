package page.generalPopup;

import static util.Assertions.*;
import static util.Finders.buttonClose;
import static util.Finders.buttonSave;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import test.AbstractTestCase;
import util.Helpers;
import util.Util;

public class Popup extends AbstractTestCase{
	By popupTitleFinder = By.cssSelector(".popup-title");
	
	public Popup(){
		while(Util.isLoadingTextVisible());
		assertTrue("Ain't no popup open.",Util.getNumberOfPopupsOpen()>0);
	}
	
	public Popup(int nPopupsOpenedBefore){
		assertTrue("Ain't no popup open.",Util.getNumberOfPopupsOpen()>0);
		assertTrue("Failed to open popup.",Util.getNumberOfPopupsOpen() == nPopupsOpenedBefore + 1);
	}
	public Popup(String popupTitle){
		while(Util.isLoadingTextVisible());
		assertEquals("Popup title is not as expected", popupTitle.trim(), getTitle().trim());
	}
	
	public void closePopup(){
		ArrayList<WebElement> closeButtons = Util.getVisibleItems(Util.getCurrentPopup(), buttonClose);
		closeButtons.get(closeButtons.size()-1).click();
		while(Util.isLoadingTextVisible());
	}
	
	public String getTitle(){
		WebElement popup = Helpers.getCurrentPopup();
		assertNotNull("No popup open.", popup);
		return popup.findElement(popupTitleFinder).getText();
	}
	
	public Object save(){
		Util.getCurrentPopup().findElement(By.cssSelector(".popup-footer ")).findElement(buttonSave).click();
		while(Util.isLoadingTextVisible());
		return null;
	}
	
}
