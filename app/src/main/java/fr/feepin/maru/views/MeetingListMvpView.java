package fr.feepin.maru.views;

import java.util.List;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;

public interface MeetingListMvpView extends MvpView{
    void onMeetingListData(List<Meeting> meetingList);
    void onMeetingDeleted(Meeting meeting);
    void onFilterChange(MeetingListFilterData meetingListFilterData);
    void navigateToAddActivity();
}
