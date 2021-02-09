package fr.feepin.maru.presenters;

import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.views.MvpView;

public abstract class BasePresenter<T extends MvpView> implements MvpPresenter<T> {

    private T view;
    private MeetingApi meetingApi;

    public BasePresenter(MeetingApi meetingApi) {
        this.meetingApi = meetingApi;
    }

    @Override
    public void onAttachView(T view) {
        this.view = view;
    }

    @Override
    public void onDetachView() {
        this.view = null;
    }

    public T getView() {
        return view;
    }

    private MeetingApi getMeetingApi() {
        return meetingApi;
    }
}
