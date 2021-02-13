package fr.feepin.maru.views;

import java.util.List;

import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;

public interface MeetingListMvpView extends MvpView{
    void setMeetingListData(List<Meeting> meetingList);
    void deleteMeeting(Meeting meeting);
    void changeFilter(MeetingListFilterData meetingListFilterData);
    void navigateToAddActivity();
    void toggleFilterView(boolean open);
}
