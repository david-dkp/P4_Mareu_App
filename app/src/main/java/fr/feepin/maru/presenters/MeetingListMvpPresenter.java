package fr.feepin.maru.presenters;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.views.MeetingListMvpView;

public interface MeetingListMvpPresenter extends MvpPresenter<MeetingListMvpView> {
    void onDeleteMeetingIconClick(Meeting meeting);
    void onFilterIconClick();
    void onFilterDataReceive(MeetingListFilterData filterData);
}
