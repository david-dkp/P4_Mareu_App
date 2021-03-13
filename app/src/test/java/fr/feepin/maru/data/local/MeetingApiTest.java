package fr.feepin.maru.data.local;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.utils.DateUtil;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class MeetingApiTest {

    private MeetingApi meetingApi;

    @Before
    public void setup() {
        meetingApi = new FakeMeetingApi();
    }

    @Test
    public void addingMeeting_withSuccess() {
        Meeting meeting = new Meeting(
                45,
                DateUtil.getDateMillisFromTime(10, 10),
                Room.DAISY,
                "Subject",
                FakeMeetingApiGenerator.generateRandomParticipants(2)
        );

        meetingApi.addMeeting(meeting);
        assertTrue(meetingApi.getMeetings().contains(meeting));
    }

    @Test
    public void deletingMeeting_withSuccess() {
        Meeting meeting = new Meeting(
                45,
                DateUtil.getDateMillisFromTime(10, 10),
                Room.MARIO,
                "Subject",
                FakeMeetingApiGenerator.generateRandomParticipants(2)
        );

        meetingApi.addMeeting(meeting);
        meetingApi.deleteMeeting(meeting);

        assertFalse(meetingApi.getMeetings().contains(meeting));
    }
}
