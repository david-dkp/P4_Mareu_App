package fr.feepin.maru.presenters;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.utils.DateUtil;
import fr.feepin.maru.views.MeetingListMvpView;

public class MeetingListPresenter extends BasePresenter<MeetingListMvpView> implements MeetingListMvpPresenter {

    private MeetingListFilterData filterData;
    private Handler handler;

    public MeetingListPresenter(MeetingApi meetingApi) {
        super(meetingApi);
        handler = new Handler();
    }

    private void updateMeetingList() {
        if (filterData == null) {
            getView().setMeetingListData(getMeetingApi().getMeetings());
        } else {
            new Thread(new Runnable() {
                @Override
                public void run() {
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
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            getView().setMeetingListData(filteredList);
                        }
                    });
                }
            }).start();
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
