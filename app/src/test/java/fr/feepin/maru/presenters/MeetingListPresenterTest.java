package fr.feepin.maru.presenters;

import android.os.Handler;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.data.local.FakeMeetingApiGenerator;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.presenters.impl.MeetingListPresenter;
import fr.feepin.maru.utils.DateUtil;
import fr.feepin.maru.views.MeetingListMvpView;

@RunWith(MockitoJUnitRunner.class)
public class MeetingListPresenterTest {

    private MeetingListPresenter presenter;
    private ExecutorService executorService;

    @Mock
    private MeetingListMvpView view;

    @Before
    public void setup() {
        executorService = Executors.newSingleThreadExecutor();
        presenter = new MeetingListPresenter(executorService, new FakeMeetingApi());
        presenter.onAttachView(view);
    }

    @After
    public void after() {
        executorService.shutdown();
        presenter.onDetachView();
    }

    @Test
    public void filterByTime_withSuccess() {
        MeetingListFilterData meetingListFilterData = new MeetingListFilterData(
                Arrays.asList(),
                DateUtil.getDateMillisFromTime(8, 0),
                DateUtil.getDateMillisFromTime(10, 0)
        );

        Meeting[] expectedMeetings = {
                FakeMeetingApiGenerator.fakeMeetings[1],
                FakeMeetingApiGenerator.fakeMeetings[3],
                FakeMeetingApiGenerator.fakeMeetings[5],
        };

        presenter.onFilterDataReceive(meetingListFilterData);

        Mockito.verify(view, Mockito.times(1)).setMeetingListData(Arrays.asList(expectedMeetings));
    }

    @Test
    public void filterByRoom_withSuccess() {

        MeetingListFilterData meetingListFilterData = new MeetingListFilterData(
                Arrays.asList(Room.ROSALINA, Room.DAISY),
                DateUtil.getDateMillisFromTime(0, 0),
                DateUtil.getDateMillisFromTime(24, 0)
                );

        Meeting[] expectedMeetings = {
                FakeMeetingApiGenerator.fakeMeetings[10],
                FakeMeetingApiGenerator.fakeMeetings[13],
        };

        presenter.onFilterDataReceive(meetingListFilterData);

        Mockito.verify(view, Mockito.timeout(1000)).setMeetingListData(Arrays.asList(expectedMeetings));
    }
}
