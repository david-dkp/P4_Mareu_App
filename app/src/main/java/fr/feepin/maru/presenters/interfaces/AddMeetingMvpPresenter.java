package fr.feepin.maru.presenters.interfaces;

import fr.feepin.maru.models.Room;
import fr.feepin.maru.views.AddMeetingMvpView;

public interface AddMeetingMvpPresenter extends MvpPresenter<AddMeetingMvpView>{
    void onRoomSelect(Room room);
    void onParticipantClick(String email);
    void onAddParticipantClick(String email);
    void onAddParticipantButtonClick();
    void onAddMeetingItemClick();
    void onTimeChange(long timeInMillis);
    void onScrimClick();
    void onSubjectChange(String input);
}
