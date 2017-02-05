package page;

import static util.Assertions.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static util.Finders.*;
import test.AbstractTestCase;
import util.Util;

public class TemplateOverviewPage extends AbstractTestCase{
	By searchContainerFinder = By.cssSelector(".tools-content");
	WebElement searchContainer;
	By searchFieldFinder = By.id("searchField");
	By searchButton = By.cssSelector(".btn-search");
	protected By templateManagerButton = By.cssSelector(".btn-template-edit");
	protected By buttonAddTemplate = By.cssSelector(".dropdown-items .btnAddTemplate");
	protected By buttonExportXls = By.cssSelector(".btnExportXls");
	protected By buttonExport = By.cssSelector(".btn-template-export");
	
	public TemplateOverviewPage(){
		assertTrue("This is not a template overview page", (boolean)js.executeScript("return urlHandler.isTemplateList()"));
		while(Util.isLoadingTextVisible());
		searchContainer = Util.getVisibleItems(searchContainerFinder).get(0);
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if(!Util.isElementPresent(searchFieldFinder)){
			searchFieldFinder = By.id("searchQuery");
		}
	}
	
	public TemplateOverviewPage searchFor(String text){
		searchContainer.findElement(searchFieldFinder).sendKeys(text);
		searchContainer.findElement(searchButton).click();
		while(Util.isLoadingTextVisible());
		return new TemplateOverviewPage();
	}
	
	public TemplateOverviewPage clearText(){
		searchContainer.findElement(buttonCancel).click();
		return new TemplateOverviewPage();
	}
	
	public TemplateOverviewPage insertText(String text){
		searchContainer.findElement(searchFieldFinder).sendKeys(text);
		return new TemplateOverviewPage();
	}
	
	public TemplateForm openAddTemplatePopup(){
		Util.getVisibleItems(templateManagerButton).get(0).click();
		Util.getVisibleItems(buttonAddTemplate).get(0).click();
		return new TemplateForm();
	}
	
	public TemplatePage enterFirstTemplate(){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			
			Util.getVisibleItems(By.cssSelector("table tbody>tr a.color")).get(0).click();
			while(Util.isScreenLockerVisible());
			while(Util.isLoadingTextVisible());
			while(Util.isScreenLockerVisible());
		}catch(IndexOutOfBoundsException e){
			return null;
		}
		return new TemplatePage();
	}
	public TemplateOverviewPage clickExportXlsButton( ){
		while(Util.isLoadingTextVisible());
		Util.getVisibleItems(buttonExport).get(0).click();
		Util.getVisibleItems(buttonExportXls).get(0).click();
		return new TemplateOverviewPage();
	}
	
	public boolean isExportXlsButtonVisible(){
		while(Util.isLoadingTextVisible());
		try{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			return Util.getVisibleItems(buttonExport).size()>0;
		}catch(Exception e){
			return false;
		}
	}
	
	public boolean isManagerButtonVisible(){
		while(Util.isLoadingTextVisible());
		try{
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
			return Util.getVisibleItems(templateManagerButton).size()>0;
		}catch(Exception e){
			return false;
		}
	}
	
}
