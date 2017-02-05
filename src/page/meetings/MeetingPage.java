package page.meetings;

import org.openqa.selenium.By;

import page.TemplatePage;
import util.Finders;
import util.Util;

public class MeetingPage extends TemplatePage{
	protected By meetingTabFinder = By.id("meetingTab");
	protected By participantsTabFinder = By.id("participantsTab");
	protected By agendaTabFinder = By.id("agendaTab");
	protected By protocolTabFinder = By.id("protocolTab");
	protected By todosTabFinder = By.id("todosTab");
	protected By decisionsTabFinder = By.id("decisionTab");
	
	public MeetingPage(){
		super();
	}
	
	@Override
	public MeetingFormPage openAddTemplatePopup() {
		Util.getVisibleItems(Finders.templateManagerButton).get(0).click();
		while (Util.isLoadingTextVisible())
			;
		Util.getVisibleItems(buttonAddTemplate).get(0).click();
		while (Util.isLoadingTextVisible())
			;
		return new MeetingFormPage();
	}
	
	@Override
	public MeetingFormPage openEditTemplatePopup() {
		Util.getVisibleItems(Finders.templateManagerButton).get(0).click();
		while (Util.isLoadingTextVisible())
			;
		Util.getVisibleItems(buttonEditTemplate).get(0).click();
		while (Util.isLoadingTextVisible())
			;
		return new MeetingFormPage();
	}
	
	@Override
	public MeetingsOverviewPage clickTemplateListButton() {
		templatePageContainer.findElement(Finders.buttonListTemplate).click();
		return new MeetingsOverviewPage();
	}
	
	@Override
	public MeetingPage clickExportPdfButton() {
		while (Util.isLoadingTextVisible())
			;
		templatePageContainer.findElement(Finders.buttonExport).click();
		Util.getVisibleItems(buttonExportPdf).get(0).click();
		return new MeetingPage();
	}
	
	
	@Override
	public TemplatePage clickExportXlsButton() {
		while (Util.isLoadingTextVisible())
			;
		templatePageContainer.findElement(Finders.buttonExport).click();
		Util.getVisibleItems(buttonExportXls).get(0).click();
		return null;
	}

	@Override
	public TemplatePage clickExportPptButton() {
		while (Util.isLoadingTextVisible())
			;
		templatePageContainer.findElement(Finders.buttonExport).click();
		Util.getVisibleItems(buttonExportPpt).get(0).click();
		return null;
	}
	
	public static MeetingPage forceDecisionsPoolConfig(boolean isDecision){

		if(isDecision){
			js.executeScript("urlHandler.getGroupPoolSettings().showDecisionsTab = true");
		}else{
			js.executeScript("urlHandler.getGroupPoolSettings().showDecisionsTab = false");
		}
		return new MeetingPage();
	}
	
	public static MeetingPage forceTodosPoolConfig(boolean isTodos){

		if(isTodos){
			js.executeScript("urlHandler.getGroupPoolSettings().showTodosTab = true");
		}else{
			js.executeScript("urlHandler.getGroupPoolSettings().showTodosTab = false");
		}
		return new MeetingPage();
	}
	
	public static MeetingPage forceUnitProfilePoolConfig(boolean isUnitProfile){

		if(isUnitProfile){
			js.executeScript("urlHandler.getGroupPoolSettings().showUnitProfile = true");
		}else{
			js.executeScript("urlHandler.getGroupPoolSettings().showUnitProfile = false");
		}
		return new MeetingPage();
	}
	
}
