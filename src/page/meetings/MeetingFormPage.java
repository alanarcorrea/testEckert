package page.meetings;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.TemplateForm;

import page.meetings.tab.AgendaTabFormPage;
import page.meetings.tab.MeetingsTabFormPage;
import page.meetings.tab.ParticipantsTabFormPage;
import page.meetings.tab.ProtocolTabFormPage;
import util.Util;
import static util.Assertions.*;
import static util.Finders.buttonSave;
public class MeetingFormPage extends TemplateForm{
	
	protected By meetingTab = By.id("meetingDataTab");
	protected By meetingFormContainer = By.id("template_newmeeting_form");
	protected By participantsTabFinder = By.id("participantsDataTab");
	protected By protocolTabFinder = By.id("protocolDataTab");
	protected By agendaTabFinder = By.id("agendaDataTab");
	protected By decisionTabFinder = By.id("decisionTab");
	protected By todosTabFinder = By.id("todosTab");
	protected WebElement popup;
	
	public MeetingFormPage(){
		assertTrue("This is not the meetings form. The form popup may not be open.", Util.isElementPresent(meetingFormContainer));
		popup = Util.getCurrentPopup();
	}
	
	public MeetingsTabFormPage goToMeetingTab(){
		try{
			popup.findElement(meetingTab).click();
			while(Util.isLoadingTextVisible());
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		return new MeetingsTabFormPage();
	}
	
	public ParticipantsTabFormPage goToParticipantsTab(){
		try{
			popup.findElement(participantsTabFinder).click();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		return new ParticipantsTabFormPage();
	}
	
	public AgendaTabFormPage goToAgendaTab(){
		try{
			popup.findElement(agendaTabFinder).click();
			return new AgendaTabFormPage();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
	}
	
	public ProtocolTabFormPage goToProtocolTab(){
		try{
			popup.findElement(protocolTabFinder).click();
			return new ProtocolTabFormPage();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
	}
	
	public MeetingPage clickSaveButton(){
		popup.findElement(buttonSave).click();
		return new MeetingPage();
	}
}
