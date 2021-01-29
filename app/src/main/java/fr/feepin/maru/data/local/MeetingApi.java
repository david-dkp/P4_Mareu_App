package fr.feepin.maru.data.local;

import java.util.List;

import fr.feepin.maru.models.Meeting;

public interface MeetingApi {
    void addMeeting(Meeting meeting);
    void deleteMeeting(Meeting meeting);
    List<Meeting> getMeetings();
}
