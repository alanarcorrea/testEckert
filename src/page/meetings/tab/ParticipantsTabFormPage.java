package page.meetings.tab;

import static util.Assertions.assertTrue;
import static util.Finders.buttonAddFinder;
import static util.Finders.buttonDeleteFinder;
import static util.Finders.buttonListFinder;
import static util.Finders.tableRowsFinder;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import page.generalPopup.ListPopup;
import page.meetings.MeetingFormPage;
import page.meetings.form.ParticipantsTabForm;
import util.Helpers;
import util.Util;

public class ParticipantsTabFormPage extends MeetingFormPage{

	WebElement participantsTab;
	WebElement participantsTable;
	WebElement additionalParticipantsTable;
	public boolean isActor;
	
	By participantsTabFinder = By.id("participantsDataTab");
	By participantsTableFinder = By.id("participantsFormTable");
	By additionalParticipantsTableFinder = By.id("additionalFormTable");
	By dropDownWrapper = By.cssSelector(".DropdownWrapper");
	By dropDownValue = By.cssSelector(".DropdownValue");
	By participationSelectFinder = By.cssSelector(".participation");
	By tableHeaders = By.cssSelector("tr>th");
	public ParticipantsTabFormPage() {
		popup = Util.getCurrentPopup();
		participantsTab = popup.findElement(participantsTabFinder);
		assertTrue("This is not participants tab", Util.isTabSelected(participantsTab));
		participantsTable = popup.findElement(participantsTableFinder);
		additionalParticipantsTable = popup.findElement(additionalParticipantsTableFinder);
		if(participantsTable.findElements(tableHeaders).get(0).getText().contains("Actor Role")){
			isActor = true;
			
		}else{
			isActor = false;
			
		}
	}
	
	public ParticipantsTabForm getParticipantsForm() {
		ParticipantsTabForm participantForm = new ParticipantsTabForm();
		for (int j = 0; j < getNumberOfParticipants(); j++) {

			participantForm.addParticipant(getParticipantNameByIndex(j), getParticipantChosenStatusByIndex(j));

		}

		for (int j = 0; j < getNumberOfAdditionalParticipants(); j++) {

			participantForm.addExternalParticipant(getAdditionalParticipantNameByIndex(j), getAdditionalParticipantChosenStatusByIndex(j), getAdditionalParticipantEmailByIndex(j));

		}
		return participantForm;
	}
	
	public ParticipantsTabFormPage clickAddParticipant(){
		try{
			participantsTable.findElement(buttonAddFinder).click();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			e.printStackTrace();
			return null;
		}
		return new ParticipantsTabFormPage();
	}
	
	public ParticipantsTabFormPage searchForTextAndSelect(String text){
		if(Helpers.searchForTextAndSelect(popup, text, 0)==null){
			return null;
		}
		return this;
	}
	
	public ParticipantsTabFormPage clickAddAdditionalParticipant(){
		try{
			additionalParticipantsTable.findElement(buttonAddFinder).click();
			return new ParticipantsTabFormPage();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			e.printStackTrace();
			return null;
		}
		
	}
	
	public ListPopup clickParticipantListButton(int index){
		int nPopupOpenBefore = Helpers.getNumberOfPopupsOpen();
		try{
			participantsTable.findElements(buttonListFinder).get(index).click();
		}catch(IndexOutOfBoundsException | NoSuchElementException | ElementNotVisibleException e){
			e.printStackTrace();
			return null;
		}
		return new ListPopup(nPopupOpenBefore);
	}
	
	public ParticipantsTabFormPage selectParticipantStatusByIndex(int participantIndex, int index){
		if(Util.selectElementByIndex(participantsTable.findElements(tableRowsFinder).get(participantIndex), index)){
			return new ParticipantsTabFormPage();
		}else{
			return null;
		}
	}
	
	public ParticipantsTabFormPage selectAdditionalParticipantStatusByIndex(int participantIndex, int index){
		if(Util.selectElementByIndex(additionalParticipantsTable.findElements(tableRowsFinder).get(participantIndex), index)){
			return new ParticipantsTabFormPage();
		}else{
			return null;
		}
	}
	
	public ParticipantsTabFormPage setAdditionalParticipantNameByIndex(int index, String name){
		By additionalParticipantNameFinder = By.name("externalParticipant-"+index);
		additionalParticipantsTable.findElement(additionalParticipantNameFinder).sendKeys(name);
		return new ParticipantsTabFormPage();
	}
	
	public ParticipantsTabFormPage setAdditionalParticipantEmailByIndex(int index, String email){
		By additionalParticipantEmailFinder = By.name("externalParticipantEmail-"+index);
		additionalParticipantsTable.findElement(additionalParticipantEmailFinder).sendKeys(email);
		return new ParticipantsTabFormPage();
	}
	
	public ParticipantsTabFormPage deleteParticipant(int index){
		participantsTable.findElements(buttonDeleteFinder).get(index).click();
		return new ParticipantsTabFormPage();
	}
	
	public ParticipantsTabFormPage deleteAdditionalParticipant(int index){
		additionalParticipantsTable.findElements(buttonDeleteFinder).get(index).click();
		
		return new ParticipantsTabFormPage();
	}
	
	public int getNumberOfParticipants(){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(Helpers.isTableEmpty(participantsTable)){
			return 0;
		}else{
			return participantsTable.findElements(tableRowsFinder).size();
		}
	}
	
	public int getNumberOfAdditionalParticipants(){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(Helpers.isTableEmpty(additionalParticipantsTable)){
			return 0;			
		}else{
			return additionalParticipantsTable.findElements(tableRowsFinder).size();
		}
	}
	
	public ParticipantsTabFormPage checkShowInvitationBox(){
		By showInvitationBox = By.id("#participantsPageTabFormContainer #showInvitation");
		popup.findElement(showInvitationBox).click();
		return new ParticipantsTabFormPage();
		
	}
	
	public ListPopup clickParticipantStatusManagerListButton(){
		
		By listButton = By.cssSelector(".DropdownManagerFooter "+Util.buttonListSelector);
		WebElement dropDown = Util.getVisibleItems(participantsTable, dropDownWrapper).get(0);
		dropDown.findElement(dropDownValue).click();
		try{
			int numberOfPopupsOpenBeforeClick = Helpers.getNumberOfPopupsOpen();
			dropDown.findElement(listButton).click();
			while(Util.isLoadingTextVisible());
			return new ListPopup(numberOfPopupsOpenBeforeClick);
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
	}
	
	public ListPopup clickAdditionalParticipantStatusManagerListButton(){
		By listButton = By.cssSelector(".DropdownManagerFooter "+Util.buttonListSelector);
		WebElement dropDown = Util.getVisibleItems(additionalParticipantsTable, dropDownWrapper).get(0);
		dropDown.findElement(dropDownValue).click();
		try{
			int numberOfPopupsOpenBeforeClick = Helpers.getNumberOfPopupsOpen();
			dropDown.findElement(listButton).click();
			while(Util.isLoadingTextVisible());
			return new ListPopup(numberOfPopupsOpenBeforeClick);
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
	}
	
	public String getParticipantChosenStatusByIndex(int index){
		By dropDownContent = By.cssSelector(".DropdownValueContent");
		//DropdownValueContent
		WebElement dropDown = Util.getVisibleItems(participantsTable, dropDownWrapper).get(index);
		
		return dropDown.findElement(dropDownContent).getText();
	}
	
	public String getAdditionalParticipantChosenStatusByIndex(int index){
		By dropDownContent = By.cssSelector(".DropdownValueContent");
		//DropdownValueContent
		WebElement dropDown = Util.getVisibleItems(additionalParticipantsTable, dropDownWrapper).get(index);
		
		return dropDown.findElement(dropDownContent).getText();
	}
	
	public String getParticipantParticipationByIndex(int index){
		if(getNumberOfParticipants()==0 || index > getNumberOfParticipants()){
			return null;
		}
		try{
			return new Select(participantsTable.findElements(participationSelectFinder).get(index)).getFirstSelectedOption().getText();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
			
	}
	
	public String getAdditionalParticipantParticipationByIndex(int index){
		if(getNumberOfAdditionalParticipants()==0 || index > getNumberOfAdditionalParticipants() ){
			return null;
		}
		try{
			return new Select(additionalParticipantsTable.findElements(participationSelectFinder).get(index)).getFirstSelectedOption().getText();
		}catch(NoSuchElementException | ElementNotVisibleException e){
			return null;
		}
		
			
	}
	
	public String getAdditionalParticipantNameByIndex(int index){
		By additionalParticipantNameFinder = By.name("externalParticipant-"+index);
		if(getNumberOfAdditionalParticipants()==0 || index > getNumberOfAdditionalParticipants()){
			return null;
		}
	
		return additionalParticipantsTable.findElement(additionalParticipantNameFinder).getAttribute("value");
			
	}
	
	public String getAdditionalParticipantEmailByIndex(int index) {
		By additionalParticipantEmailFinder = By.name("externalParticipantEmail-" + index);
		if (getNumberOfAdditionalParticipants() == 0 || index > getNumberOfAdditionalParticipants()) {
			return null;
		}

		return additionalParticipantsTable.findElement(additionalParticipantEmailFinder).getAttribute("value");

	}
	
	public String getParticipantNameByIndex(int index){
		By participantNameFinder = By.name("internalParticipant-"+index);
		if(getNumberOfParticipants()==0 || index > getNumberOfParticipants()){
			return null;
		}
		String name = participantsTable.findElement(participantNameFinder).getAttribute("value");
		if(isActor){
			try{
				name = name.split(", ")[1];
			}catch(IndexOutOfBoundsException e){
				//fail("No actor profile is shown.");
				
			}
		}
		return name;
		
		
	}
	
	public String getActorProfileByIndex(int index){
		By participantNameFinder = By.name("internalParticipant-"+index);
		if(getNumberOfParticipants()==0 || index > getNumberOfParticipants()){
			return null;
		}
		String name = participantsTable.findElement(participantNameFinder).getAttribute("value");
		if(isActor){
			name = name.split(", ")[0];
		}else{
			return null;
		}
		return name;
		
	}
	
}
