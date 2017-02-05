package page.meetings.popup;

import static util.Assertions.*;
import static util.Finders.a;
import static util.Finders.buttonAddDecision;
import static util.Finders.buttonAddFinder;
import static util.Finders.buttonSave;
import static util.Finders.li;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;



import page.generalPopup.Popup;
import page.meetings.tab.MeetingsTabFormPage;
import util.Helpers;
import util.Util;
public class MeetingsProtocolPopup extends Popup{
	By protocolPopupContainerFinder = By.cssSelector(".protocol-form");
	By addNewAgendaPointButton = By.cssSelector("#newAgendaPointTab .btn-add");
	By tabsFinder = By.cssSelector(".list-tabs");
	By previousButtonFinder = By.cssSelector(".arrow-big-previous");
	By nextButtonFinder = By.cssSelector(".arrow-big-next");
	By pointTitleFinder = By.cssSelector(".protocol-name");
	By activeTabFinder = By.cssSelector(".active");
	By sliderNextButtonFinder = By.cssSelector(".slider-button-next");
	By sliderPreviousButtonFinder = By.cssSelector(".slider-button-prev");
	
	WebElement popup, protocolPopupContainer;
	
	By todosDivFinder = By.cssSelector(".toDosSE");
	By decisionDivFinder = By.cssSelector(".decisionsSE");
	
	public MeetingsProtocolPopup() {
		
		popup = Util.getCurrentPopup();
		assertTrue("This is not a Meeting protocol popup", Util.isElementPresent(popup, protocolPopupContainerFinder));
		protocolPopupContainer = Util.getVisibleItems(popup, protocolPopupContainerFinder).get(0);
		
	}
	
	public AddNewAgendaPointPopup clickAddNewAgendaPointButton(){
		if(Util.isElementPresent(popup, sliderNextButtonFinder) && popup.findElement(sliderNextButtonFinder).isDisplayed()){
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			for(int i = 0; i < getNumberOfTabs()-2; i++){
				popup.findElement(sliderNextButtonFinder).click();
				if(Util.isElementPresent(popup, addNewAgendaPointButton) && popup.findElement(addNewAgendaPointButton).isDisplayed()){
					break;
				}
				try {
					Thread.sleep(450);
				} catch (InterruptedException e) {
					
				}
			}
		}
		int numberOfPopupsOpenBeforeClick = Helpers.getNumberOfPopupsOpen();
		popup.findElement(addNewAgendaPointButton).click();
		while(Util.isLoadingTextVisible());
		return new AddNewAgendaPointPopup(numberOfPopupsOpenBeforeClick);
		
	}
	
	public MeetingsTabFormPage clickOkButton(){
		popup.findElement(buttonSave).click();
		while(Util.isLoadingTextVisible());
		return new MeetingsTabFormPage();
	}
	
	public int getNumberOfTabs(){
		return popup.findElement(tabsFinder).findElements(li).size();
	}
	
	public MeetingsProtocolPopup clickPreviousButton(){
		WebElement previousButton = popup.findElement(previousButtonFinder);
		if(previousButton.isDisplayed()){
			previousButton.click();
			while(Util.isLoadingTextVisible());
			return new MeetingsProtocolPopup();
		}else{
			return null;
		}
	}
	
	public MeetingsProtocolPopup clickNextButton(){
		WebElement nextButton = popup.findElement(nextButtonFinder);
		if(nextButton.isDisplayed()){
			nextButton.click();
			while(Util.isLoadingTextVisible());
			return new MeetingsProtocolPopup();
		}else{
			return null;
		}
	}
	
	public Integer getTabSelectedIndex(){
		ArrayList<WebElement> tabs = (ArrayList<WebElement>) popup.findElement(tabsFinder).findElements(li);
		for(int i = 0; i < tabs.size(); i++){
			if(Util.isTabSelected(tabs.get(i).findElement(a))){
				return i;
			}
			
		}
		return null;
		
	}
	public MeetingsProtocolPopup clickOnTabByIndex(int index){
		
		if(Util.isElementPresent(popup, sliderNextButtonFinder) && popup.findElement(sliderNextButtonFinder).isDisplayed()){
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			for(int i = 0; i < getNumberOfTabs(); i++){
				if(getTabSelectedIndex()<index){
					popup.findElement(sliderNextButtonFinder).click();
					
				}else{
					popup.findElement(sliderPreviousButtonFinder).click();
				}
				try {
					Thread.sleep(450);
				} catch (InterruptedException e) {
					
				}
				if(popup.findElement(tabsFinder).findElements(li).get(index).findElement(a).isDisplayed()){
					break;
				}
				
			}
		}
		popup.findElement(tabsFinder).findElements(li).get(index).findElement(a).click();
		while(Util.isLoadingTextVisible());
		return new MeetingsProtocolPopup();
	}
	
	public int getIndexOfSelectedTab(){
		ArrayList<WebElement> tabs = (ArrayList<WebElement>) popup.findElement(tabsFinder).findElements(By.cssSelector("a"));
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		for(int i = 0; i<tabs.size(); i++){
			if(Util.isTabSelected(tabs.get(i))){
				return i;
			}
		}
		throw new NoSuchElementException("No tab is selected");
	}
	
	public String getSelectedPointTitle(){
		
		return protocolPopupContainer.findElement(pointTitleFinder).getText();
	}
	
	public String getSelectedTabTitle(){
		WebElement tabs = popup.findElement(tabsFinder);
		
		return tabs.findElement(activeTabFinder).getAttribute("title");
	}

	
	@Override
	public Object save(){
		popup.findElement(By.linkText("save")).click();
		while(Util.isLoadingTextVisible());
		return null;
	}
	
	public Object quickSave(){
		popup.findElement(By.partialLinkText("icksave")).click();
		while(Util.isLoadingTextVisible());
		return null;
	}
}
