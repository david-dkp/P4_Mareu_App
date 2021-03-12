package fr.feepin.maru.presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.views.FilterDialogMvpView;

public class FilterDialogPresenter extends BasePresenter<FilterDialogMvpView> implements FilterDialogMvpPresenter {

    private HashMap<Room, Boolean> rooms;
    private int startingTimeHours = 0, endingTimeHours = 24;

    public FilterDialogPresenter(MeetingApi meetingApi) {
        super(meetingApi);
        rooms = new HashMap<>();
        for (Room room : Room.values()) {
            rooms.put(room, false);
        }
    }

    @Override
    public void onAttachView(FilterDialogMvpView view) {
        super.onAttachView(view);
        getView().setTimeSlider(startingTimeHours, endingTimeHours);
    }

    @Override
    public void onRoomSelect(Room room) {
        rooms.put(room, true);
    }

    @Override
    public void onRoomUnselect(Room room) {
        rooms.put(room, false);
    }

    @Override
    public void onStartingTimeChange(int hours) {
        startingTimeHours = hours;
    }

    @Override
    public void onEndingTimeChange(int hours) {
        endingTimeHours = hours;
    }

    @Override
    public void onApplyButtonClick() {
        long startingTimeMillis;
        long endingTimeMillis;

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        calendar.set(Calendar.HOUR_OF_DAY, startingTimeHours);
        startingTimeMillis = calendar.getTimeInMillis();

        calendar.set(Calendar.HOUR_OF_DAY, endingTimeHours);
        endingTimeMillis = calendar.getTimeInMillis();

        ArrayList<Room> selectedRooms = new ArrayList<>();

        for (Map.Entry<Room, Boolean> entry : rooms.entrySet()) {
            if (entry.getValue()) {
                selectedRooms.add(entry.getKey());
            }
        }

        MeetingListFilterData filterData = new MeetingListFilterData(selectedRooms, startingTimeMillis, endingTimeMillis);
        getView().sendFilterData(filterData);
    }

    @Override
    public void onDismissDialog() {
        for (Map.Entry<Room, Boolean> entry : rooms.entrySet()) {
            rooms.put(entry.getKey(), false);
        }

        startingTimeHours = 0;
        endingTimeHours = 24;
    }

}
