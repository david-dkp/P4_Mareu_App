package fr.feepin.maru.views;

import java.util.List;

import fr.feepin.maru.models.MeetingListFilterData;

public interface FilterDialogMvpView extends MvpView{
    void setTimeSlider(int startingHours, int endingHours);
    void sendFilterData(MeetingListFilterData filterData);
}
