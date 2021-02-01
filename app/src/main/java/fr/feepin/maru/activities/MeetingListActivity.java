package fr.feepin.maru.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


import java.util.ArrayList;

import fr.feepin.maru.R;
import fr.feepin.maru.adapters.MeetingListAdapter;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.databinding.ActivityMeetingListBinding;
import fr.feepin.maru.models.Meeting;

public class MeetingListActivity extends AppCompatActivity implements MeetingListAdapter.OnMeetingDelete {

    private ActivityMeetingListBinding binding;
    private MeetingApi meetingApi;
    private MeetingListAdapter meetingListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding and inflating view
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Init datas
        meetingApi = new FakeMeetingApi();
        meetingListAdapter = new MeetingListAdapter(this);

        setupRecyclerView();
        TextView textView;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        refreshData();
    }

    @Override
    public void onMeetingDelete(Meeting meeting) {
        meetingApi.deleteMeeting(meeting);
        refreshData();
    }

    private void refreshData() {
        meetingListAdapter.submitList(new ArrayList<Meeting>(meetingApi.getMeetings()));
    }

    private void setupRecyclerView() {
        meetingListAdapter.submitList(new ArrayList<Meeting>(meetingApi.getMeetings()));
        binding.rvMeetings.setAdapter(meetingListAdapter);
    }
}