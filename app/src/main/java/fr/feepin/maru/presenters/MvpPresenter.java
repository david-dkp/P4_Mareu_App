package fr.feepin.maru.presenters;

import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.views.MvpView;

public interface MvpPresenter<T extends MvpView> {
    void onAttachView(T view);
    void onDetachView();
}
