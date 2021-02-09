package fr.feepin.maru.presenters;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.views.MeetingListMvpView;

public interface MeetingListMvpPresenter extends MvpPresenter<MeetingListMvpView> {
    void onDeleteMeetingIconClick(Meeting meeting);
    void onFilterIconClick();
    void onRangeTimeChange(long startingTimeMillis, long endingTimeMillis);
    void onPlaceFilterSelect(String place);
    void onPlaceFilterUnselect(String place);
}
