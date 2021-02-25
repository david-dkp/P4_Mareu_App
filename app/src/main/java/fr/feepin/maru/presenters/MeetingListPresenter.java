package fr.feepin.maru.presenters;

import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.views.MeetingListMvpView;

public class MeetingListPresenter extends BasePresenter<MeetingListMvpView> implements MeetingListMvpPresenter {

    private boolean filterOpened;

    public MeetingListPresenter(MeetingApi meetingApi) {
        super(meetingApi);
        filterOpened = false;
    }

    private void updateMeetingList() {
        getView().setMeetingListData(getMeetingApi().getMeetings());
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

    }

    @Override
    public void onRangeTimeChange(long startingTimeMillis, long endingTimeMillis) {

    }

    @Override
    public void onPlaceFilterSelect(String place) {

    }

    @Override
    public void onPlaceFilterUnselect(String place) {

    }
}
