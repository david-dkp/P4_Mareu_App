package fr.feepin.maru.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.SharedElementCallback;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import fr.feepin.maru.R;
import fr.feepin.maru.adapters.MeetingListAdapter;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.databinding.ActivityMeetingListBinding;
import fr.feepin.maru.dialogs.FilterDialog;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.presenters.MeetingListMvpPresenter;
import fr.feepin.maru.presenters.MeetingListPresenter;
import fr.feepin.maru.views.MeetingListMvpView;

public class MeetingListActivity extends AppCompatActivity implements
        MeetingListMvpView,
        MeetingListAdapter.OnMeetingDelete,
        FilterDialog.OnFilterDataReceiveListener {

    private ActivityMeetingListBinding binding;
    private MeetingListAdapter meetingListAdapter;
    private MeetingListMvpPresenter meetingListPresenter;
    private FilterDialog filterDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Binding and inflating view
        binding = ActivityMeetingListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //Init datas
        filterDialog = new FilterDialog();

        //Setup
        setupRecyclerView();
        setupFab();
        setupToolbar();

        //Init presenter
        meetingListPresenter = new MeetingListPresenter(FakeMeetingApi.getInstance());
    }

    private void setupRecyclerView() {
        meetingListAdapter = new MeetingListAdapter(this);
        binding.rvMeetings.setAdapter(meetingListAdapter);
    }

    private void setupFab() {
        binding.fabAddMeeting.setOnClickListener((v) -> {
            binding.fabAddMeeting.setEnabled(false);
            Intent intent = new Intent(this, AddMeetingActivity.class);
            startActivity(intent);
        });
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        meetingListPresenter.onAttachView(this);
        filterDialog.setOnFilterDataReceiveListener(this);
        binding.fabAddMeeting.setEnabled(true);
    }

    @Override
    protected void onStop() {
        super.onStop();
        meetingListPresenter.onDetachView();
        filterDialog.setOnFilterDataReceiveListener(null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FakeMeetingApi.resetApi();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.filter && !filterDialog.isAdded()) {
            meetingListPresenter.onFilterIconClick();
            return true;
        }
        return false;
    }

    @Override
    public void onMeetingDelete(Meeting meeting) {
        meetingListPresenter.onDeleteMeetingIconClick(meeting);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_meeting_list_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void setMeetingListData(List<Meeting> meetingList) {
        meetingListAdapter.submitList(new ArrayList<>(meetingList));
    }

    @Override
    public void openFilterDialog() {
        filterDialog.show(getSupportFragmentManager(), "Dialog");
    }

    @Override
    public void onFilterDataReceive(MeetingListFilterData filterData) {
        meetingListPresenter.onFilterDataReceive(filterData);
    }
}