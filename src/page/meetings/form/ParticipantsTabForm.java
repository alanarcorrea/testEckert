package page.meetings.form;

import java.util.ArrayList;

import test.ComparableForm;

final class Participant extends ComparableForm {
	String name, status, email;
}

public class ParticipantsTabForm extends ComparableForm {
	private ArrayList<Participant> participants;

	public ArrayList<Participant> getParticipants() {
		return participants;
	}

	public void setParticipants(ArrayList<Participant> participants) {
		this.participants = participants;
	}

	public ParticipantsTabForm() {
		participants = new ArrayList<Participant>();
	}

	public void addExternalParticipant(String name, String status, String email) {
		Participant participant = new Participant();
		participant.name = name;
		participant.status = status;
		participant.email = email;
		participants.add(participant);
	}

	public void addParticipant(String name, String status) {
		Participant participant = new Participant();
		participant.name = name;
		participant.status = status;
		participants.add(participant);
	}

	public String getParticipantName(int index) {
		return participants.get(index).name;
	}

	public String getParticipantStatus(int index) {
		return participants.get(index).status;
	}

	public String getExternalParticipantEmail(int index) {
		return participants.get(index).email;
	}
}
