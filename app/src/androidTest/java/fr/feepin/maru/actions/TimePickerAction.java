package fr.feepin.maru.actions;

import android.view.View;
import android.widget.TimePicker;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import java.util.Timer;

import static org.hamcrest.Matchers.instanceOf;

public class TimePickerAction implements ViewAction {

    private int hour, minutes;

    public TimePickerAction(int hour, int minutes) {
        this.hour = hour;
        this.minutes = minutes;
    }

    @Override
    public Matcher<View> getConstraints() {
        return instanceOf(TimePicker.class);
    }

    @Override
    public String getDescription() {
        return "Choose specifique time to time picker, hour: " + hour + ", minutes: " + minutes;
    }

    @Override
    public void perform(UiController uiController, View view) {
        TimePicker timePicker = (TimePicker) view;
        timePicker.setHour(hour);
        timePicker.setMinute(minutes);
    }
}
