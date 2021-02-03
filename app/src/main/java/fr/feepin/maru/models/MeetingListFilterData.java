package fr.feepin.maru.models;

import androidx.annotation.NonNull;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;

public class MeetingListFilterData {
    private String place;
    private long startingTimeMillis;
    private long endingTimeMillis;

    public MeetingListFilterData() {
        this.place = "";
        this.startingTimeMillis = 0;
        this.endingTimeMillis = 0;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getStartingTimeMillis() {
        return startingTimeMillis;
    }

    public void setStartingTimeMillis(long startingTimeMillis) {
        this.startingTimeMillis = startingTimeMillis;
    }

    public long getEndingTimeMillis() {
        return endingTimeMillis;
    }

    public void setEndingTimeMillis(long endingTimeMillis) {
        this.endingTimeMillis = endingTimeMillis;
    }
}
