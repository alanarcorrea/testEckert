package page;

import static util.Assertions.assertEquals;
import static util.Assertions.assertTrue;
import static util.Assertions.fail;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import test.AbstractTestCase;
import util.Util;

//This class basically has the template headers function.
public class TemplatePage extends AbstractTestCase{
	protected By buttonAddTemplate = By.cssSelector(".btnAddTemplate");
	protected By buttonEditTemplate = By.cssSelector(".btnEditTemplate");
	protected By templateManagerButton = By.cssSelector(".btn-template-edit");
	protected By templatePageContainerFinder = By.cssSelector(".template-page");
	protected By buttonDeleteTemplate = By.cssSelector(".btnDeleteTemplate");
	protected By buttonListTemplate = By.cssSelector(".btn-template-view");
	protected By buttonExport = By.cssSelector(".btn-template-export");
	protected By buttonExportPdf = By.cssSelector(".btnExportPdf");
	protected By buttonExportXls = By.cssSelector(".btnExportXls");
	protected By buttonExportPpt = By.cssSelector(".btnExportPpt");
	protected By buttonManageTemplatePool = By.cssSelector(".btnManagePools");
	protected By headerFinder = By.id("header");
	protected By globalSearchContainer = By.id("search");
	
	protected WebElement templatePageContainer, header;
	
	public String templateUrl;
	
	public TemplatePage(String templateTitle){
		while(Util.isLoadingTextVisible());
		while(Util.isScreenLockerVisible());
		while(Util.isLoadingTextVisible());
		String title = null;
		try{
			title = getTemplateTitle();
		}catch(IndexOutOfBoundsException e){
			fail("This is not a template. URL: " + driver.getCurrentUrl());
		}
		assertEquals("This is not the right template! title",templateTitle, title);
		templateUrl = driver.getCurrentUrl();
		templatePageContainer = Util.getVisibleItems(templatePageContainerFinder).get(0);
		header = driver.findElement(headerFinder);
	}
	
	public TemplatePage(){
		while(Util.isLoadingTextVisible() || Util.isTemplateLoading());

		assertTrue("This is not a template page.", Util.getVisibleItems(templatePageContainerFinder).size()>0);
		
		templatePageContainer = Util.getVisibleItems(templatePageContainerFinder).get(0);
		templateUrl = driver.getCurrentUrl();
		header = driver.findElement(headerFinder);
	}
	
	public TemplateOverviewPage clickOnTabByIndex(int index){
		Util.getPageTabs().get(index).click();
		while(Util.isLoadingTextVisible());
		if(Util.isElementPresent(templatePageContainerFinder)){
			return this.clickTemplateListButton();
		}
		return new TemplateOverviewPage();
	}
	public Integer getSelectedTemplateTabIndex(){
		ArrayList<WebElement> tabs = Util.getPageTabs();
		for(int i = 0; i < tabs.size(); i++){
			if(Util.isTabSelected(tabs.get(i))){
				return i;
			}
		}
		return null;
	}
	public int getNumberOfTabs(){
		return Util.getPageTabs().size();
	}
	
	
	public TemplateForm openEditTemplatePopup( ){
		Util.clickEditTemplateButton();
		return new TemplateForm();
	}
	
	public TemplatePage goToTemplatePage(){
		driver.get(this.templateUrl);
		while(Util.isLoadingTextVisible());
		return new TemplatePage();
	}
	
	public TemplateForm openAddTemplatePopup( ){
		Util.getVisibleItems(templateManagerButton).get(0).click();
		Util.getVisibleItems(buttonAddTemplate).get(0).click();
		return new TemplateForm();
	}
	
	public TemplateOverviewPage clickTemplateListButton(){
		templatePageContainer.findElement(buttonListTemplate).click();
		return new TemplateOverviewPage();
	}
	
	public TemplatePage clickExportPdfButton( ){
		while(Util.isLoadingTextVisible());
		templatePageContainer.findElement(buttonExport).click();
		Util.getVisibleItems(buttonExportPdf).get(0).click();
		return new TemplatePage();
	}
	
	public TemplatePage clickExportXlsButton( ){
		while(Util.isLoadingTextVisible());
		templatePageContainer.findElement(buttonExport).click();
		Util.getVisibleItems(buttonExportXls).get(0).click();
		return new TemplatePage();
	}
	
	public TemplatePage clickExportPptButton( ){
		while(Util.isLoadingTextVisible());
		templatePageContainer.findElement(buttonExport).click();
		Util.getVisibleItems(buttonExportPpt).get(0).click();
		return new TemplatePage();
	}
	
	public String getTemplateTitle(){
		return Util.getTemplateTitle();
	}
	
	
	public boolean isExportButtonVisible(){
		return Util.getVisibleItems(templatePageContainer, buttonExport).size()>0;
	}
	
	public boolean isTemplateListButtonVisible(){
		return Util.getVisibleItems(templatePageContainer, buttonListTemplate).size()>0;
	}
	
	public boolean isEditTemplateButtonVisible(){
		return Util.getVisibleItems(templatePageContainer, templateManagerButton).size()>0;
	}
}
