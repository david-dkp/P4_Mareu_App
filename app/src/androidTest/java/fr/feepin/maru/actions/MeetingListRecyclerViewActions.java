package fr.feepin.maru.actions;

import android.view.View;
import android.widget.ImageView;

import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;

import org.hamcrest.Matcher;

import fr.feepin.maru.R;

public class MeetingListRecyclerViewActions {

    public static DeleteItemAction deleteItemAction() { return new DeleteItemAction(); }

    private static class DeleteItemAction implements ViewAction {

        @Override
        public Matcher<View> getConstraints() {
            return null;
        }

        @Override
        public String getDescription() {
            return "Click on delete icon to delete the meeting.";
        }

        @Override
        public void perform(UiController uiController, View view) {
            ImageView ivDeleteIcon = view.findViewById(R.id.ivDeleteIcon);
            ivDeleteIcon.performClick();
        }
    }
}
