package fr.feepin.maru.presenters;

import fr.feepin.maru.models.Room;
import fr.feepin.maru.views.FilterDialogMvpView;

public interface FilterDialogMvpPresenter extends MvpPresenter<FilterDialogMvpView> {
    void onRoomSelect(Room room);
    void onRoomUnselect(Room room);
    void onStartingTimeChange(int hours);
    void onEndingTimeChange(int hours);
    void onApplyButtonClick();
    void onDismissDialog();
}
