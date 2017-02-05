package page.meetings.popup;

import static util.Finders.textAreaFinder;

import org.openqa.selenium.WebElement;

import page.generalPopup.Popup;
import util.Util;

public class ParticipantsEmailAddressPopup extends Popup{
	
	WebElement popup;
	
	public String getParticipantsEmail(int index){
		popup = Util.getCurrentPopup();
		String emails = popup.findElement(textAreaFinder).getAttribute("value");
		return emails.split(";")[index].trim();
	}
	
}
