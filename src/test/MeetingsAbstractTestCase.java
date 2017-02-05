package test;

import org.junit.BeforeClass;

import page.Navigator;
import page.meetings.MeetingPage;
import util.Util;

public class MeetingsAbstractTestCase extends AbstractTestCase{
	
	protected static MeetingPage meetingPage;
	
	protected static Navigator navigator;
	@BeforeClass
	public static void goToTemplate(){
		navigator = new Navigator();
		
		if(!isOnTemplatePage){
			Util.closeAllPopup();
			Util.wait(2000);
			meetingPage = navigator.goToMeetingsPage("https://test.bridge-live.com/#/meeting/meeting-pool/meeting12/fc79139506de49d098155de1bcbeaffc");
				
			isOnTemplatePage = true;
			
			while(Util.isLoadingTextVisible() || Util.isScreenLockerVisible() || Util.isTemplateLoading());
		}
		
	}
	
	public MeetingsAbstractTestCase(){
		super();
	}
}
