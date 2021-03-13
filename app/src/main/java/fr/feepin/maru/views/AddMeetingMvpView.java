package fr.feepin.maru.views;

import java.util.List;

public interface AddMeetingMvpView extends MvpView{
    void setParticipantList(List<String> participants);
    void setAddParticipantList(List<String> participants);
    void toggleAddParticipantView(boolean open);
    void closeActivity();
}
