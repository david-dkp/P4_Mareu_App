package fr.feepin.maru.assertions;

import android.view.View;

import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.junit.Assert;

import java.util.List;

public class ListAdapterAssertions {

    public static <T> CurrentListAssertion containsOnly(List<T> list) {
        return new CurrentListAssertion<T>(list);
    }

    private static class CurrentListAssertion<T> implements ViewAssertion {

        private List<T> list;

        public CurrentListAssertion(List<T> list) {
            this.list = list;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            RecyclerView recyclerView = (RecyclerView) view;
            if (recyclerView == null) throw noViewFoundException;
            ListAdapter listAdapter = (ListAdapter) recyclerView.getAdapter();
            assert listAdapter != null;
            Assert.assertTrue(listAdapter.getCurrentList().containsAll(list) && listAdapter.getCurrentList().size() == list.size());
        }
    }
}
