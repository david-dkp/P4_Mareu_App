package fr.feepin.maru.models;

import androidx.annotation.NonNull;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;

public class MeetingListFilterData {
    private final ArrayList<String> places;
    private long startingTimeMillis;
    private long endingTimeMillis;

    public MeetingListFilterData() {
        this.places = new ArrayList<String>();
        this.startingTimeMillis = 0;
        this.endingTimeMillis = 0;
    }

    public ArrayList<String> getPlaces() {
        return places;
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
