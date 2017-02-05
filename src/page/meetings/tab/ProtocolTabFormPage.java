package page.meetings.tab;

import static util.Assertions.*;
import static util.Finders.buttonEdit;
import static util.Finders.tableRowsFinder;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.meetings.MeetingFormPage;
import page.meetings.popup.MeetingsProtocolPopup;
import util.Helpers;
import util.Util;

public class ProtocolTabFormPage extends MeetingFormPage{
	WebElement protocolTab;
	WebElement protocolTable;
	By protocolTableFinder = By.id("protocolFormTable");
	By nameFinder = By.name("name");
	//Creates agenda point 
	private void createAgendaPoint(String title, String time){
		AgendaTabFormPage agendaTab = super.goToAgendaTab();
		assertNotNull("There is no points created and agenda tab is not visible.",agendaTab);
		agendaTab.clickAddPoint();
		
		agendaTab.addTitleByIndex(0, title);
		agendaTab.addTimeByIndex(0, time);
		
		agendaTab.goToProtocolTab();
	}
	public ProtocolTabFormPage() {
		popup = Util.getCurrentPopup();
		protocolTab = popup.findElement(protocolTabFinder);
		assertTrue("This is not protocol tab", Util.isTabSelected(protocolTab));
		protocolTable = popup.findElement(protocolTableFinder);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(Helpers.isTableEmpty(protocolTable)){
			createAgendaPoint("Agenda created for testing protocol", "15:00");
		}
		
	}
	
	public int getNumberOfPoints(){
		if(Helpers.isTableEmpty(protocolTable)){
			return 0;
		}else{
			return protocolTable.findElements(tableRowsFinder).size();
		}
	}
	
	public MeetingsProtocolPopup clickEditButtonByIndex(int index){
		protocolTable.findElements(buttonEdit).get(index).click();
		while(Util.isLoadingTextVisible());
		return new MeetingsProtocolPopup();
	}
	
	public String getTitleByIndex(int index){
		return protocolTable.findElements(nameFinder).get(index).getText();
	}
}
 