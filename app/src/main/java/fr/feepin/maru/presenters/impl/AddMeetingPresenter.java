package fr.feepin.maru.presenters.impl;

import java.util.ArrayList;
import java.util.Arrays;

import fr.feepin.maru.data.local.FakeMeetingApiGenerator;
import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.presenters.interfaces.AddMeetingMvpPresenter;
import fr.feepin.maru.views.AddMeetingMvpView;

public class AddMeetingPresenter extends BasePresenter<AddMeetingMvpView> implements AddMeetingMvpPresenter {

    private Room selectedRoom;
    private long timeInMillis;
    private ArrayList<String> participants, addParticipants;
    private String subject;

    public AddMeetingPresenter(MeetingApi meetingApi) {
        super(meetingApi);
        participants = new ArrayList<>();
        addParticipants = new ArrayList<String>(Arrays.asList(FakeMeetingApiGenerator.fakeEmails));
    }

    @Override
    public void onAttachView(AddMeetingMvpView view) {
        super.onAttachView(view);
        updateAddParticipantList();
        updateParticipantList();
    }

    @Override
    public void onRoomSelect(Room room) {
        this.selectedRoom = room;
    }

    @Override
    public void onParticipantClick(String email) {
        participants.remove(email);
        addParticipants.add(email);
        updateParticipantList();
        updateAddParticipantList();
    }

    @Override
    public void onAddParticipantClick(String email) {
        participants.add(email);
        addParticipants.remove(email);
        getView().toggleAddParticipantView(false);
        updateParticipantList();
        updateAddParticipantList();
    }

    @Override
    public void onAddParticipantButtonClick() {
        getView().toggleAddParticipantView(true);
    }

    @Override
    public void onAddMeetingItemClick() {
        Meeting meeting = new Meeting(
                FakeMeetingApiGenerator.fakeMeetings.length,
                timeInMillis,
                selectedRoom,
                subject,
                participants
        );

        getMeetingApi().addMeeting(meeting);
        getView().closeActivity();
    }

    @Override
    public void onSubjectChange(String input) {
        this.subject = input;
    }

    @Override
    public void onScrimClick() {
        getView().toggleAddParticipantView(false);
    }

    @Override
    public void onTimeChange(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    private void updateParticipantList() {
        getView().setParticipantList(participants);
    }

    private void updateAddParticipantList() {
        getView().setAddParticipantList(addParticipants);
    }
}
