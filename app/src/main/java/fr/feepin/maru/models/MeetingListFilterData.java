package fr.feepin.maru.models;

import androidx.annotation.NonNull;

import com.google.android.material.slider.LabelFormatter;
import com.google.android.material.slider.RangeSlider;

import java.util.ArrayList;
import java.util.List;

public class MeetingListFilterData {

    private final List<Room> selectedRooms;
    private long startingTimeMillis;
    private long endingTimeMillis;

    public MeetingListFilterData(List<Room> selectedRooms, long startingTimeMillis, long endingTimeMillis) {
        this.selectedRooms = selectedRooms;
        this.startingTimeMillis = startingTimeMillis;
        this.endingTimeMillis = endingTimeMillis;
    }

    public List<Room> getSelectedRooms() {
        return selectedRooms;
    }

    public long getStartingTimeMillis() {
        return startingTimeMillis;
    }

    public long getEndingTimeMillis() {
        return endingTimeMillis;
    }

}
