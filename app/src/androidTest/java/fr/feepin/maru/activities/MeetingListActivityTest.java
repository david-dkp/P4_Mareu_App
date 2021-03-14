package fr.feepin.maru.activities;

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
import fr.feepin.maru.assertions.ListAdapterAssertions;
import fr.feepin.maru.assertions.RecyclerViewAssertions;
import fr.feepin.maru.data.local.FakeMeetingApiGenerator;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.Room;

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
        onView(withId(R.id.rvMeetings)).check(RecyclerViewAssertions.hasItemCount(FakeMeetingApiGenerator.fakeMeetings.length-1));
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
        onView(withId(R.id.rvMeetings)).check(ListAdapterAssertions.containsOnly(expectedMeetings));
    }

}
