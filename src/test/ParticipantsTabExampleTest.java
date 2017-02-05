package test;

import static util.Assertions.assertEquals;
import static util.Assertions.assertNotNull;
import static util.Assertions.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import page.generalPopup.ListPopup;
import page.meetings.MeetingFormPage;
import page.meetings.tab.ParticipantsTabFormPage;
import util.Util;

public class ParticipantsTabExampleTest extends MeetingsAbstractTestCase {

	static ParticipantsTabFormPage participantsTab;
	public static MeetingFormPage meetingForm;
	
	@BeforeClass
	public static void goToMeetingsPage() {
		meetingForm = meetingPage.openAddTemplatePopup();
		
		participantsTab = meetingForm.goToParticipantsTab();
		
	}

	private void testAddParticipantsButton() {
		int numberOfParticipants = participantsTab.getNumberOfParticipants();
		assertNotNull("The add button is not visible or doesn't exist.", participantsTab.clickAddParticipant());
		assertEquals("The add button was clicked but no participant has been added", numberOfParticipants + 1,
				participantsTab.getNumberOfParticipants());
	}


	@Test
	public void testAddParticipants() {

		int numberOfPopupsOpen = Util.getNumberOfPopupsOpen();

		if (participantsTab.getNumberOfParticipants() == 0) {
			testAddParticipantsButton();
		}

		ListPopup participantPopup = participantsTab.clickParticipantListButton(0);

		assertNotNull("The list button is not visible or doesn't exist.", participantPopup);

		assertTrue("Participants list button didn't open.", Util.getNumberOfPopupsOpen() > numberOfPopupsOpen);

		assertTrue("Participants list is empty.", participantPopup.selectAnyElement());

		Util.assertThatFieldDoesntContainTrash(participantsTab.getParticipantNameByIndex(0));

		assertTrue("Participants list button didn't close.", Util.getNumberOfPopupsOpen() == numberOfPopupsOpen);

		assertNotNull("There is no status to select", participantsTab.selectParticipantStatusByIndex(0, 0));

	}


}
