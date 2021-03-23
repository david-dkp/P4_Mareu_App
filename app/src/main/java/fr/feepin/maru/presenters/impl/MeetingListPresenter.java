package fr.feepin.maru.presenters.impl;

import android.os.Handler;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;


import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.presenters.interfaces.MeetingListMvpPresenter;
import fr.feepin.maru.views.MeetingListMvpView;

public class MeetingListPresenter extends BasePresenter<MeetingListMvpView> implements MeetingListMvpPresenter {

    private MeetingListFilterData filterData;
    private ExecutorService executorService;

    public MeetingListPresenter(ExecutorService executorService, MeetingApi meetingApi) {
        super(meetingApi);
        this.executorService = executorService;
    }

    private void updateMeetingList() {
        if (filterData == null) {
            getView().setMeetingListData(getMeetingApi().getMeetings());
        } else {
            executorService.execute(() -> {
                long startingTime = filterData.getStartingTimeMillis();
                long endingTime = filterData.getEndingTimeMillis();
                List<Room> selectedRooms = filterData.getSelectedRooms();

                List<Meeting> meetings = getMeetingApi().getMeetings();
                ArrayList<Meeting> filteredList = new ArrayList<>();

                for (Meeting meeting : meetings) {
                    boolean isInRange = meeting.getStartingTime() < endingTime && meeting.getStartingTime() >= startingTime;
                    if (selectedRooms.size() == 0 && isInRange) {
                        filteredList.add(meeting);
                    } else if (selectedRooms.contains(meeting.getRoom()) && isInRange) {
                        filteredList.add(meeting);
                    }
                }
                getView().setMeetingListData(filteredList);
            });
        }
    }

    @Override
    public void onDeleteMeetingIconClick(Meeting meeting) {
        getMeetingApi().deleteMeeting(meeting);
        updateMeetingList();
    }

    @Override
    public void onAttachView(MeetingListMvpView view) {
        super.onAttachView(view);
        updateMeetingList();
    }

    @Override
    public void onFilterIconClick() {
        getView().openFilterDialog();
    }

    @Override
    public void onFilterDataReceive(MeetingListFilterData filterData) {
        this.filterData = filterData;
        updateMeetingList();
    }
}
