package fr.feepin.maru.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import fr.feepin.maru.R;
import fr.feepin.maru.adapters.MeetingListAdapter;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.databinding.ActivityMeetingListBinding;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.presenters.MeetingListMvpPresenter;
import fr.feepin.maru.presenters.MeetingListPresenter;
import fr.feepin.maru.views.MeetingListMvpView;

public class MeetingListActivity extends AppCompatActivity implements MeetingListMvpView, MeetingListAdapter.OnMeetingDelete {

    private ActivityMeetingListBinding binding;
    private MeetingListAdapter meetingListAdapter;
    private MeetingListMvpPresenter meetingListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Binding and inflating view
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupToolbar();

        //Init datas
        meetingListAdapter = new MeetingListAdapter(this);
        setupRecyclerView();

        //Init presenter
        meetingListPresenter = new MeetingListPresenter(FakeMeetingApi.getInstance());
    }

    @Override
    protected void onStart() {
        super.onStart();
        meetingListPresenter.onAttachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        meetingListPresenter.onDetachView();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FakeMeetingApi.resetApi();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        return false;
    }

    @Override
    public void onMeetingDelete(Meeting meeting) {
        meetingListPresenter.onDeleteMeetingIconClick(meeting);
    }

    private void setupRecyclerView() {
        binding.rvMeetings.setAdapter(meetingListAdapter);
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    public void setMeetingListData(List<Meeting> meetingList) {
        meetingListAdapter.submitList(new ArrayList<>(meetingList));
    }

    @Override
    public void navigateToAddActivity() {

    }

    @Override
    public void toggleFilterView(boolean open) {

    }
}