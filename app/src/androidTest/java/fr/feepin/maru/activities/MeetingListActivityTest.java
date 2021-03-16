package fr.feepin.maru.activities;

import android.util.Log;

import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.List;

import fr.feepin.maru.R;
import fr.feepin.maru.actions.MeetingListRecyclerViewActions;
import fr.feepin.maru.actions.RangeSliderChangeValuesAction;
import fr.feepin.maru.actions.TimePickerAction;
import fr.feepin.maru.assertions.MeetingListAdapterAssertions;
import fr.feepin.maru.assertions.RecyclerViewAssertions;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.data.local.FakeMeetingApiGenerator;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.utils.DateUtil;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MeetingListActivityTest {

    @Rule
    public ActivityScenarioRule<MeetingListActivity> activityScenarioRule = new ActivityScenarioRule<MeetingListActivity>(MeetingListActivity.class);

    @Test
    public void meetingListRecyclerview_shouldNotBeEmpty() {
        onView(withId(R.id.rvMeetings)).check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void onDeleteMeeting_recyclerView_shouldHaveFewerMeetings() {
        onView(withId(R.id.rvMeetings)).perform(RecyclerViewActions.actionOnItemAtPosition(3, MeetingListRecyclerViewActions.deleteItemAction()));
        onView(withId(R.id.rvMeetings)).check(RecyclerViewAssertions.hasItemCount(FakeMeetingApiGenerator.fakeMeetings.length - 1));
    }

    @Test
    public void clickOnFab_shouldNavigate_toAddMeetingActivity() {
        Intents.init();
        onView(withId(R.id.fabAddMeeting)).perform(ViewActions.click());
        intended(hasComponent(AddMeetingActivity.class.getName()));
        Intents.release();

    }

    @Test
    public void filteringByRoom_withSuccess() throws InterruptedException {
        Room roomToFilter = Room.MARIO;
        List<Meeting> expectedMeetings = Arrays.asList(
                FakeMeetingApiGenerator.fakeMeetings[9],
                FakeMeetingApiGenerator.fakeMeetings[3]
        );

        onView(withId(R.id.filter)).perform(ViewActions.click());
        onView(withText("Mario")).perform(ViewActions.click());
        onView(withId(R.id.btnApply)).perform(ViewActions.click());
        onView(withId(R.id.rvMeetings)).check(MeetingListAdapterAssertions.containsOnly(expectedMeetings));
    }

    @Test
    public void filteringByTime_withSuccess() {
        List<Meeting> expectedMeetings = Arrays.asList(
                FakeMeetingApiGenerator.fakeMeetings[0],
                FakeMeetingApiGenerator.fakeMeetings[2],
                FakeMeetingApiGenerator.fakeMeetings[4],
                FakeMeetingApiGenerator.fakeMeetings[6],
                FakeMeetingApiGenerator.fakeMeetings[12],
                FakeMeetingApiGenerator.fakeMeetings[13]
        );

        int startingHour = 14;
        int endingHour = 18;

        onView(withId(R.id.filter)).perform(ViewActions.click());
        onView(withId(R.id.sliderTime)).perform(new RangeSliderChangeValuesAction(startingHour, endingHour));
        onView(withId(R.id.btnApply)).perform(ViewActions.click());
        onView(withId(R.id.rvMeetings)).check(MeetingListAdapterAssertions.containsOnly(expectedMeetings));
    }

    @Test
    public void addingMeeting_withSuccess() {
        Meeting expectedNewMeeting = new Meeting(
                FakeMeetingApi.getInstance().getMeetings().size()+1,
                DateUtil.getDateMillisFromTime(17, 30),
                Room.DAISY,
                "Brainstorming",
                Arrays.asList(FakeMeetingApiGenerator.fakeEmails[2], FakeMeetingApiGenerator.fakeEmails[7])
                );

        onView(withId(R.id.fabAddMeeting)).perform(ViewActions.click());

        //Subject
        onView(withId(R.id.editTextSubject)).perform(ViewActions.typeText("Brainstorming"));

        //Room
        onView(withId(R.id.spinnerRooms)).perform(ViewActions.click());
        onView(withText("Daisy")).perform(ViewActions.click());

        //Participants
        onView(withId(R.id.ivAddParticipant)).perform(ViewActions.click());
        onView(withId(R.id.rvAddParticipant)).perform(
                RecyclerViewActions.actionOnItemAtPosition(2, ViewActions.click())
        );
        onView(withId(R.id.ivAddParticipant)).perform(ViewActions.click());
        onView(withId(R.id.rvAddParticipant)).perform(
                RecyclerViewActions.actionOnItemAtPosition(6, ViewActions.click())
        );

        //Time
        onView(withId(R.id.timePicker)).perform(new TimePickerAction(17, 30));


        onView(withId(R.id.addMeeting)).perform(ViewActions.click());

        Log.d("debug", expectedNewMeeting.toString());
        onView(withId(R.id.rvMeetings)).check(MeetingListAdapterAssertions.contains(expectedNewMeeting));
    }
}
