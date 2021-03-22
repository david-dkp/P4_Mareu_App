package fr.feepin.maru.assertions;

import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

import fr.feepin.maru.adapters.MeetingListAdapter;
import fr.feepin.maru.models.Meeting;

public class MeetingListAdapterAssertions {

    public static CurrentListAssertion containsOnly(List<Meeting> meetings) {
        return new CurrentListAssertion(meetings);
    }

    public static HasItemAssertion contains(Meeting meeting) {
        return new HasItemAssertion(meeting);
    }

    protected static class ListAdapterAssertion implements ViewAssertion {

        private MeetingListAdapter listAdapter;

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            if (noViewFoundException != null) throw noViewFoundException;

            RecyclerView recyclerView = (RecyclerView) view;
            MeetingListAdapter listAdapter = (MeetingListAdapter) recyclerView.getAdapter();
            assert listAdapter != null;
            this.listAdapter = listAdapter;
        }

        public MeetingListAdapter getListAdapter() {
            return listAdapter;
        }
    }

    private static class CurrentListAssertion extends ListAdapterAssertion {

        private List<Meeting> meetings;

        public CurrentListAssertion(List<Meeting> meetings) {
            this.meetings = meetings;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            super.check(view, noViewFoundException);
            List<Meeting> currentList = getListAdapter().getCurrentList();
            Assert.assertTrue(currentList.containsAll(meetings) && currentList.size() == meetings.size());
        }
    }

    private static class HasItemAssertion extends ListAdapterAssertion {

        private Meeting meeting;

        public HasItemAssertion(Meeting meeting) {
            this.meeting = meeting;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            super.check(view, noViewFoundException);
            Assert.assertTrue(getListAdapter().getCurrentList().contains(meeting));
        }
    }
}
