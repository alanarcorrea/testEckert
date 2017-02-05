package page.meetings.popup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.generalPopup.Popup;
import util.Helpers;
import util.Util;
import static util.Assertions.assertEquals;
import static util.Finders.buttonSave;
import static util.Finders.nameById;
public class AddNewAgendaPointPopup extends Popup{
	WebElement popup;
	By timeFinder = By.name("time");
	By confidentialCheckBox = By.id("confidential");
	public AddNewAgendaPointPopup(int numberOfPopupsOpenBefore){
		assertEquals("The popup failed to open.", Helpers.getNumberOfPopupsOpen(), numberOfPopupsOpenBefore + 1);
		popup = Util.getCurrentPopup();
	}
	
	public AddNewAgendaPointPopup addTitle(String text){
		popup.findElement(nameById).sendKeys(text);
		return this;
	}
	
	public AddNewAgendaPointPopup addTime(String time){
		popup.findElement(timeFinder).sendKeys(time);
		return this;
	}
	
	public AddNewAgendaPointPopup checkConfidentialButton(){
		popup.findElement(confidentialCheckBox).click();
		return this;
	}
	
	public MeetingsProtocolPopup clickSaveButton(){
		popup.findElement(buttonSave).click();
		while(Util.isLoadingTextVisible());
		return new MeetingsProtocolPopup();
	}
}
