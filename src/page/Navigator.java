package page;

import static org.junit.Assume.assumeNotNull;
import static test.AbstractTestCase.driver;
import static util.Finders.buttonClose;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import page.meetings.MeetingPage;
import test.AbstractTestCase;
import util.Util;
import util.WebDriverEckert;

public class Navigator extends AbstractTestCase{
	public ArrayList<WebElement> topMenu;
	By topMenuFinder = By.cssSelector("#topNavigation");
	By subMenuFinder = By.cssSelector("#subNavigation");
	
	By topMenuButtonsFinder = By.cssSelector("#topNavigation li a");
	By subMenuButtonsFinder = By.cssSelector("#subNavigation li a");
	ArrayList<WebElement> subMenu ;
	
	public Navigator(){
		topMenu = Util.getVisibleItems(topMenuButtonsFinder);
		subMenu = Util.getVisibleItems(subMenuButtonsFinder);
	}
	
	public String getTitle(){
		return driver.getTitle();
	}
	public boolean isTopMenuSelected(int index){
		return topMenu.get(index).getAttribute("class").contains("selected");
	}
	public MeetingPage goToMeetingsPage(){
		String fullUrl = "";
		String poolUrl = (String) js.executeScript("return util.getTemplateUrlByClass('com.eckertcaine.bridge.modules.template.newmeeting.NewMeeting');");
		assumeNotNull(poolUrl);
		fullUrl = properties.getProperty("address") + poolUrl;
		System.out.println(properties.getProperty("address"));
		//isOnTemplatePage = true;
		driver.get(fullUrl);  
		while(Util.isLoadingTextVisible());
		while(Util.isLoadingTextVisible());
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		return new MeetingPage();
	}
	public MeetingPage goToMeetingsPage(String url){
		driver.get(url);
		while(Util.isLoadingTextVisible());
		while(Util.isLoadingTextVisible());
		driver.manage().timeouts().implicitlyWait(35, TimeUnit.SECONDS);
		return new MeetingPage();
	}
	/*
	public UnitProfilePage goToUnitProfilePage(){
		String fullUrl = "";
		String poolUrl = (String) js.executeScript("return util.getTemplateUrlByClass('com.eckertcaine.bridge.modules.template.unitprofile.UnitProfile');");
		assumeNotNull(poolUrl);
		fullUrl = properties.getProperty("address") + poolUrl;
		System.out.println(properties.getProperty("address"));
		//isOnTemplatePage = true;
		driver.get(fullUrl);
		//driver.get("http://localhost:8080/#/qualitatsentwicklung/zeit-meilensteinplan-und-projekt/project/7f1dcf12f66740b78665e964c97c2901");
		while(Util.isLoadingTextVisible());
		while(Util.isLoadingTextVisible());
		driver.manage().timeouts().implicitlyWait(95, TimeUnit.SECONDS);
		return new UnitProfilePage();
	}
	
	public UnitProfilePage goToUnitProfilePage(String url){

		driver.get(url);
		//driver.get("http://localhost:8080/#/qualitatsentwicklung/zeit-meilensteinplan-und-projekt/project/7f1dcf12f66740b78665e964c97c2901");
		while(Util.isLoadingTextVisible());
		while(Util.isLoadingTextVisible());
		driver.manage().timeouts().implicitlyWait(95, TimeUnit.SECONDS);
		return new UnitProfilePage();
	}
	
	public NewProjectPage goToNewProjectPage(){
		String fullUrl = "";
		String poolUrl = (String) js.executeScript("return util.getTemplateUrlByClass('com.eckertcaine.bridge.modules.template.newproject.NewProject');");
		assumeNotNull(poolUrl);
		fullUrl = properties.getProperty("address") + poolUrl;
		System.out.println(properties.getProperty("address"));
		//isOnTemplatePage = true;
		driver.get(fullUrl);
		//driver.get("http://localhost:8080/#/qualitatsentwicklung/zeit-meilensteinplan-und-projekt/project/7f1dcf12f66740b78665e964c97c2901");
		while(Util.isLoadingTextVisible());
		while(Util.isLoadingTextVisible());
		driver.manage().timeouts().implicitlyWait(95, TimeUnit.SECONDS);
		return new NewProjectPage();
	}
	
	public NewProjectPage goToNewProjectPage(String url){
		
		driver.get(url);  
		while(Util.isLoadingTextVisible());
		while(Util.isLoadingTextVisible());
		driver.manage().timeouts().implicitlyWait(95, TimeUnit.SECONDS);
		return new NewProjectPage();
	}*/
	
	public Navigator goToTopMenuByIndex(int index){
		
		while(Util.isLoadingTextVisible());
		while(Util.getNumberOfPopupsOpen()>0){
			System.out.println("Some popup has failed to close.");
			ArrayList<WebElement> visibleItems = new ArrayList<WebElement>();
			visibleItems = Util.getVisibleItems( buttonClose);
			visibleItems.get(visibleItems.size()-1).click();	
		}
			
		while(Util.isLoadingTextVisible());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		topMenu.get(index).click();
		while(Util.isScreenLockerVisible());
		WebDriverEckert.changeTimeout( 3);
		while(Util.isLoadingTextVisible());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		topMenu.get(index).click();
		while(Util.isScreenLockerVisible());
		WebDriverEckert.changeTimeout( 3);
		while(Util.isLoadingTextVisible());
		
		return new Navigator();
	}
	
	public int getNumberOfTopMenus(){
		return topMenu.size();
	}
	
	public int getNumberOfSubMenus(){
		return subMenu.size();
	}
	
	public boolean isThisTemplatePage(){
		String s = (String) js.executeScript("return urlHandler.getGroupContainerInfo().mainPageJSClass");
		if(s == null){
			return false;
		}
		return s.contains("template");
	}
	
	public boolean isThisTemplateListPage(){
		return (boolean) js.executeScript("return urlHandler.isTemplateList()");
		
	}
	
	public Navigator goToSubMenuByIndex(int index){
		subMenu.get(index).click();
		while(Util.isLoadingTextVisible());
		 
		return new Navigator();
	}
	
	public Navigator goToTabByIndex(int index){
		try{
			Util.getPageTabs().get(index).click();
			while(Util.isLoadingTextVisible());
		}catch(NoSuchElementException | IndexOutOfBoundsException | ElementNotVisibleException e){
			return null;
		}
		while(Util.isLoadingTextVisible());
		
		return new Navigator();
	}
	
	public int getNumberOfTabs(){
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		try{
			return Util.getPageTabs().size();
		}catch(NoSuchElementException | IndexOutOfBoundsException | ElementNotVisibleException e){
			return 0;
		}
	}
	
	public Navigator selectItemOnGovernanceUnitDropdown(int index){
		try{
			Util.selectElementByIndex(driver.findElement(topMenuFinder), index);
		}catch(Exception e){
			return null;
		}
		return this;
	}
}
