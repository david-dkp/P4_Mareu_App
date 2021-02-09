package fr.feepin.maru.data.local;

import java.util.ArrayList;
import java.util.List;

import fr.feepin.maru.models.Meeting;

public class FakeMeetingApi implements MeetingApi {

    private static FakeMeetingApi instance = null;

    private ArrayList<Meeting> meetings = FakeMeetingApiGenerator.generateMeetings();

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    public static FakeMeetingApi getInstance() {
        if (instance == null) {
            instance = new FakeMeetingApi();
            return instance;
        }
        return instance;
    }
}
