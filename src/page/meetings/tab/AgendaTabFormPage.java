package page.meetings.tab;

import static util.Assertions.assertTrue;
import static util.Finders.buttonListFinder;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.generalPopup.ListPopup;
import page.meetings.MeetingFormPage;
import util.Helpers;
import util.Util;

public class AgendaTabFormPage extends MeetingFormPage{
	WebElement agendaTab;
	WebElement agendaTable;
	By agendaTableFinder = By.id("agendaFormTable");
	By titleFinder = By.name("points[].title");
	By timeFinder = By.name("points[].time");
	By agendaPoint = By.cssSelector(".agendaPoint");
	By confidentialCheckBox = By.name("points[].confidential");
	By addpointButtonFinder = By.cssSelector(".btn-add");
	By addSubpointButtonFinder = By.cssSelector(".subagenda-point");
	By dayRowFinder = By.cssSelector(".dayRow");
	private By numberFinder = By.cssSelector(".number");
	
	public AgendaTabFormPage(){
		popup = Helpers.getCurrentPopup();
		agendaTab = popup.findElement(super.agendaTabFinder);
		assertTrue("This is not agenda tab", Util.isTabSelected(agendaTab));
		agendaTable = popup.findElement(agendaTableFinder);
	}
	
	public AgendaTabFormPage clickAddPoint() {
		try {
			ArrayList<WebElement> buttons = Util.getVisibleItems(agendaTable, addpointButtonFinder);
			buttons.get(0).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			e.printStackTrace();
			return null;
		}
		return new AgendaTabFormPage();
	}
	
	public AgendaTabFormPage clickAddPoint(int dayRow) {
		try {
			ArrayList<WebElement> rows = Util.getVisibleItems(agendaTable, dayRowFinder);
			WebElement button = Util.getVisibleItems(rows.get(dayRow), addpointButtonFinder).get(0);
			button.click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			e.printStackTrace();
			return null;
		}
		return new AgendaTabFormPage();
	}
	public AgendaTabFormPage clickAddSubpoint() {
		try {
			ArrayList<WebElement> buttons = Util.getVisibleItems(agendaTable, addSubpointButtonFinder);
			buttons.get(0).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			e.printStackTrace();
			return null;
		}
		return new AgendaTabFormPage();
	}
	
	public AgendaTabFormPage clickAddSubpoint(int dayRow) {
		try {
			ArrayList<WebElement> rows = Util.getVisibleItems(agendaTable, dayRowFinder);
			WebElement button = Util.getVisibleItems(rows.get(dayRow), addSubpointButtonFinder).get(0);
			button.click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			e.printStackTrace();
			return null;
		}
		return new AgendaTabFormPage();
	}
	
	public AgendaTabFormPage addTitleByIndex(int index, String text){
		agendaTable.findElements(titleFinder).get(index).sendKeys(text);
		return new AgendaTabFormPage();
	}
	
	public AgendaTabFormPage addTimeByIndex(int index, String text){
		agendaTable.findElements(timeFinder).get(index).sendKeys(text);
		return new AgendaTabFormPage();
	}
	
	public ListPopup clickProjectListButtonByIndex(int index){
		int numberOfPopupsBeforeClicking = Helpers.getNumberOfPopupsOpen();
		try{
			agendaTable.findElements(buttonListFinder).get(index).click();
			while(Util.isLoadingTextVisible());
		}catch(IndexOutOfBoundsException |NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		return new ListPopup(numberOfPopupsBeforeClicking);
	}
	
	public AgendaTabFormPage checkConfidentialBoxByIndex(int index){
		agendaTable.findElements(confidentialCheckBox).get(index).click();
		return new AgendaTabFormPage();
	}
	
	public int getNumberOfAgendas(){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(Helpers.isTableEmpty(agendaTable) || !Util.isElementPresent(agendaTable, agendaPoint)){
			return 0;
		}else{
			int a;
			a = agendaTable.findElements(agendaPoint).size();
			return a;
		}
	}
	
	
	public boolean isConfidential(int index){
		return agendaTable.findElements(confidentialCheckBox).get(index).isSelected();
	}
	public String getNumberTextByIndex(int index){
		return agendaTable.findElements(numberFinder).get(index).getText();
	}
	public String getTitleTextByIndex(int index){
		return agendaTable.findElements(titleFinder).get(index).getAttribute("value");
	}
	
	public String getTimeTextByIndex(int index){
		return agendaTable.findElements(timeFinder).get(index).getAttribute("value");
	}
	
	public String getProjectTextByIndex(int index){
		By projectTextFieldFinder = By.name("selected");
		try{
			return agendaTable.findElements(projectTextFieldFinder).get(index).getAttribute("value").trim();
		}catch(IndexOutOfBoundsException e){
			return null;
		}
	}
	
	
	
	
}
