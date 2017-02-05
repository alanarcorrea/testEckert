package page.meetings.tab;

import static util.Assertions.assertTrue;
import static util.Finders.input;
import static util.Finders.label;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.generalPopup.ListPopup;
import page.meetings.MeetingFormPage;
import util.Util;

public class MeetingsTabFormPage extends MeetingFormPage {

	By meetingsTabContainerFinder = By.id("new_meeting_form");
	// public static boolean isAllDayEvent;
	WebElement meetingsTabContainer;

	// buttons
	By findDate = By.cssSelector(".findDate");
	By placeFinder = By.name("place");
	By linkFinder = By.name("placeLink");
	By responsibleButtonFinder = By.id("responsiblesButton");
	By systemButtonFinder = By.id("systemButton");
	By moderatorButtonFinder = By.id("moderatorButton");
	By processButtonFinder = By.id("processButton");
	By unitProfileButtonFinder = By.id("unitButton");
	By toolsButton = By.id("toolsButton");
	By newProjectButtonFinder = By.cssSelector(".newProjectRow .btn-list");
	By protocolButtonFinder = By.id("protocolButton");
	By setDateButtonFinder = By.id("setDateRadio");
	By allDayEvent = By.name("allDayEvent");
	By nameFinder = By.name("name");
	By frequencyFinder = By.name("frequency");
	By durationContainerFinder = By.id("setDateForm");
	// rows
	By placeRowFinder = By.cssSelector(".placeRow");
	By linkRowFinder = By.cssSelector(".linkRow");
	By systemRowFinder = By.cssSelector(".systemRow");
	By responsiblesRowFinder = By.cssSelector(".responsiblesRow");
	By moderatorsRowFinder = By.cssSelector(".moderatorsRow");
	By unitProfileRowFinder = By.cssSelector(".unitRow");
	By processRowFinder = By.cssSelector(".processesRow");
	By toolsRowFinder = By.cssSelector(".toolsRow");
	By newProjectRowFinder = By.cssSelector(".newProjectRow");
	By remarksRowFinder = By.cssSelector(".remarksRow");
	By protocolsRowFinder = By.cssSelector(".protocolsRow");
	By projectRowFinder = By.cssSelector(".newProjectRow");
	By setDateRowFinder = By.id("setDateForm");
	By calendarTextBoxFinder = By.cssSelector(".hasDatepicker");
	By doodleLinkTextBoxFinder = By.name("doodleLink");

	By projectButton = By.cssSelector(".projectRow .btn-list");
	By processSponsorFinder = By.id("processSponsor");

	int numberOfPopupsOpenBeforeChanging;

	public MeetingsTabFormPage() {
		assertTrue(Util.isTabSelected(popup.findElement(meetingTab)));
		meetingsTabContainer = popup.findElement(meetingsTabContainerFinder);
		numberOfPopupsOpenBeforeChanging = Util.getNumberOfPopupsOpen();
	}

	public MeetingsTabFormPage setFindDate() {
		try {
			meetingsTabContainer.findElement(findDate).click();
			return new MeetingsTabFormPage();
		} catch (Exception e) {
			return null;
		}
	}

	public MeetingsTabFormPage setDate() {
		WebElement setDateButton = meetingsTabContainer.findElement(setDateButtonFinder);
		if (setDateButton.isDisplayed()) {
			setDateButton.click();
			
		}
		Util.setDate(meetingsTabContainer);
		return new MeetingsTabFormPage();
	}

	public MeetingsTabFormPage oldSetDate() {
		WebElement setDateButton = meetingsTabContainer.findElement(setDateButtonFinder);
		if (setDateButton.isDisplayed()) {
			setDateButton.click();
		}
		Util.setDate(meetingsTabContainer);
		return new MeetingsTabFormPage();
	}

	public String getDate() {
		try {
			WebElement container = popup.findElement(durationContainerFinder);
			String date = "";
			date += container.findElements(input).get(0).getAttribute("value");
			date += " " + container.findElements(input).get(2).getAttribute("value");
			if (!isAllDayEventCheckBoxSelected()) {
				date += " " + container.findElements(input).get(1).getAttribute("value");
				date += " " + container.findElements(input).get(3).getAttribute("value");
			}
			return date.trim();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	public MeetingsTabFormPage clickSetDateButton() {
		while (Util.isLoadingTextVisible())
			;
		try {
			meetingsTabContainer.findElement(setDateButtonFinder).click();

		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new MeetingsTabFormPage();
	}
	
	public MeetingsTabFormPage setDoodleLink(String doodle){
		try{
			meetingsTabContainer.findElement(doodleLinkTextBoxFinder).sendKeys(doodle);
			MeetingsTabFormPage meetingsTabFormPage = new MeetingsTabFormPage();
			System.out.println(meetingsTabFormPage);
			return meetingsTabFormPage;
		}catch(Exception e){
			return null;
		}
	}

	public MeetingsTabFormPage setName(String name) {
		Util.insertName(name);
		return new MeetingsTabFormPage();
	}

	public MeetingsTabFormPage setFrequency(String frequency) {
		try {
			meetingsTabContainer.findElement(frequencyFinder).sendKeys(frequency);
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new MeetingsTabFormPage();
	}

	public String getFrequency() {
		try {
			return meetingsTabContainer.findElement(frequencyFinder).getAttribute("value");
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}

	}

	public String getBeginDate() {
		try {
			return meetingsTabContainer.findElement(setDateRowFinder).findElements(calendarTextBoxFinder).get(0).getAttribute("value");
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}

	}

	public String getEndDate() {
		try {
			return meetingsTabContainer.findElement(setDateRowFinder).findElements(calendarTextBoxFinder).get(1).getAttribute("value");
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}

	}

	public MeetingsTabFormPage setPlace(String place) {
		try {
			meetingsTabContainer.findElement(placeFinder).sendKeys(place);
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new MeetingsTabFormPage();
	}

	public MeetingsTabFormPage setLink(String link) {
		try {
			meetingsTabContainer.findElement(linkFinder).sendKeys(link);
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new MeetingsTabFormPage();

	}

	public MeetingsTabFormPage addDescription() {
		if (!Util.isElementPresent(popup, remarksRowFinder) || !popup.findElement(remarksRowFinder).isDisplayed()) {
			return null;
		}
		js.executeScript("CKEDITOR.instances['remarks'].setData('Selenium test details......');");
		return new MeetingsTabFormPage();
	}

	public ListPopup clickResponsibleButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(responsibleButtonFinder).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickSystemButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(systemButtonFinder).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickModeratorButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(moderatorButtonFinder).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickProcessButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(processButtonFinder).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickUnitProfileButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(unitProfileButtonFinder).click();
			while (Util.isLoadingTextVisible())
				;
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickToolsButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(toolsButton).click();

		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickProjectButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(newProjectButtonFinder).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickMasterMeetingOldProjectButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(projectButton).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public ListPopup clickProtocolButton() {
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try {
			popup.findElement(protocolButtonFinder).click();
		} catch (NoSuchElementException | ElementNotVisibleException e) {
			return null;
		}
		return new ListPopup(numberOfPopupsOpenBeforeChanging);
	}

	public String getNameFieldText() {
		return popup.findElement(nameFinder).getAttribute("value");
	}

	public String[] getPlaceFieldText() {
		return getRowContent(placeRowFinder);
	}

	public String[] getLinkFielText() {
		return getRowContent(linkRowFinder);
	}

	public String[] getResponsiblesFieldText() {
		return getRowContent(responsiblesRowFinder);
	}

	public String[] getSystemFieldText() {
		return getRowContent(systemRowFinder);
	}

	public String[] getModeratorFieldText() {
		return getRowContent(moderatorsRowFinder);
	}

	public String[] getToolsFieldText() {
		return getRowContent(toolsRowFinder);
	}

	public String[] getUnitProfileFieldText() {
		return getRowContent(unitProfileRowFinder);
	}

	public String[] getProcessFieldText() {
		return getRowContent(processRowFinder);
	}

	public String[] getProjectFieldText() {
		return getRowContent(projectRowFinder);
	}

	public String[] getProtocolFieldText() {
		return getRowContent(protocolsRowFinder);
	}

	public boolean isAllDayEventCheckBoxSelected() {
		return popup.findElement(durationContainerFinder).findElement(allDayEvent).isSelected();

	}

	public String[] getDescriptionFieldText() {
		String[] labelAndContent = new String[2];
		labelAndContent[0] = popup.findElement(remarksRowFinder).findElement(label).getAttribute("value");
		labelAndContent[1] = Util.cleanIFrameText((String) js.executeScript("return CKEDITOR.instances.remarks.getData()"));
		return labelAndContent;

	}

	private String[] getRowContent(By by) {
		String[] labelAndContent = new String[2];
		WebElement container = popup.findElement(by);
		if (!container.isDisplayed()) {
			return null;
		}
		labelAndContent[1] = popup.findElement(by).findElement(input).getAttribute("value");
		if (labelAndContent[1].isEmpty()) {
			return null;
		}
		labelAndContent[0] = popup.findElement(by).findElement(label).getText();
		labelAndContent[1] = Util.cleanString(labelAndContent[1]).trim();
		return labelAndContent;
	}

}
