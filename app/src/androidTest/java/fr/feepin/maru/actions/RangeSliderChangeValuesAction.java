package fr.feepin.maru.actions;

import android.view.View;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import com.google.android.material.slider.RangeSlider;

import org.hamcrest.Matcher;

import java.util.Arrays;

import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;

public class RangeSliderChangeValuesAction implements ViewAction {

    private float startingValue, endingValue;

    public RangeSliderChangeValuesAction(float startingValue, float endingValue) {
        this.startingValue = startingValue;
        this.endingValue = endingValue;
    }

    @Override
    public Matcher<View> getConstraints() {
        return instanceOf(RangeSlider.class);
    }

    @Override
    public String getDescription() {
        return "Change slider values, startingValue: "+startingValue+", endingValue: "+endingValue;
    }

    @Override
    public void perform(UiController uiController, View view) {
        RangeSlider rangeSlider = (RangeSlider) view;
        rangeSlider.setValues(Arrays.asList(startingValue, endingValue));
    }
}
