package page;

import static util.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import page.generalPopup.Popup;
import util.Util;

public class TemplateForm extends Popup{
	WebElement popup;
	By templateForm = By.cssSelector(".template-form");
	public TemplateForm(){
		
		popup = Util.getCurrentPopup();
		assertTrue("This is not a template form.",Util.isElementPresent(templateForm));
	}
	
	
}
