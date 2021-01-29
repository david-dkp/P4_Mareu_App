package fr.feepin.maru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import fr.feepin.maru.adapters.MeetingListAdapter;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.models.Meeting;

public class MainActivity extends AppCompatActivity implements MeetingListAdapter.OnMeetingDelete {

    private MeetingApi meetingApi;
    private MeetingListAdapter meetingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        meetingApi = new FakeMeetingApi();
        meetingListAdapter = new MeetingListAdapter(this);
    }

    @Override
    public void onMeetingDelete(Meeting meeting) {

    }
}