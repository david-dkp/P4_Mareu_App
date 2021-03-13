package fr.feepin.maru.presenters.impl;

import fr.feepin.maru.data.local.MeetingApi;
import fr.feepin.maru.presenters.interfaces.MvpPresenter;
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

    protected T getView() {
        return view;
    }

    protected MeetingApi getMeetingApi() {
        return meetingApi;
    }
}
