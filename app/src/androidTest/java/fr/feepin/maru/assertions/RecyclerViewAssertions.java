package fr.feepin.maru.assertions;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewAssertion;

import org.junit.Assert;

public class RecyclerViewAssertions {

    public static ItemCountAssertion hasItemCount(int itemCount) {
        return new ItemCountAssertion(itemCount);
    }

    private static class ItemCountAssertion implements ViewAssertion {

        private int itemCount;

        public ItemCountAssertion(int itemCount) {
            this.itemCount = itemCount;
        }

        @Override
        public void check(View view, NoMatchingViewException noViewFoundException) {
            RecyclerView recyclerView = (RecyclerView) view;
            Assert.assertEquals(recyclerView.getAdapter().getItemCount(), this.itemCount);
        }
    }
}
