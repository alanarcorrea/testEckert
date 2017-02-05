package page.meetings;

import static util.Assertions.assertTrue;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.TemplateOverviewPage;
import util.Util;

public class MeetingsOverviewPage extends TemplateOverviewPage{
	By buttonMeetingFinder = By.cssSelector(".btn-meeting");
	By meetingsContainerFinder = By.cssSelector(".calendar-content");
	
	WebElement meetingsContainer;
	
	public MeetingsOverviewPage(){
		super();
		assertTrue("This is not meetings overview page.",Util.isElementPresent(meetingsContainerFinder));
		meetingsContainer = driver.findElement(meetingsContainerFinder);
	}
	@Override
	public MeetingFormPage openAddTemplatePopup( ){
		Util.getVisibleItems(templateManagerButton).get(0).click();
		System.out.println();
	
		Util.getVisibleItems(buttonAddTemplate).get(0).click();
		return new MeetingFormPage();
	}
	
	public MeetingPage enterMeetingByIndex(int index){
		ArrayList<WebElement> meetings = (ArrayList<WebElement>) meetingsContainer.findElements(buttonMeetingFinder);
		try{
			meetings.get(index).click();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
		return new MeetingPage();
	}
	
	public MeetingPage enterMeetingByName(String meetingName){
		By meetingFinderByName = By.partialLinkText(meetingName);
		try{
			meetingsContainer.findElement(meetingFinderByName).click();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			while(Util.isLoadingTextVisible());
			return null;
		}
		
		return new MeetingPage();
	}
}
