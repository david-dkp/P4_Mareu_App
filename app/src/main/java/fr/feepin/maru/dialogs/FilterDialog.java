package fr.feepin.maru.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.slider.RangeSlider;

import fr.feepin.maru.adapters.RoomsChipAdapter;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.databinding.DialogFilterBinding;
import fr.feepin.maru.models.MeetingListFilterData;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.presenters.interfaces.FilterDialogMvpPresenter;
import fr.feepin.maru.presenters.impl.FilterDialogPresenter;
import fr.feepin.maru.views.FilterDialogMvpView;

public class FilterDialog extends DialogFragment implements FilterDialogMvpView, RoomsChipAdapter.OnRoomSelectListener {

    private DialogFilterBinding binding;
    private RoomsChipAdapter roomsChipAdapter;
    private FilterDialogMvpPresenter presenter;
    private OnFilterDataReceiveListener onFilterDataReceiveListener;

    public FilterDialog() {
        presenter = new FilterDialogPresenter(FakeMeetingApi.getInstance());
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DialogFilterBinding.inflate(inflater, container, false);
        roomsChipAdapter = new RoomsChipAdapter(binding.chipGroupRooms, this);
        presenter.onAttachView(this);

        binding.scrollViewRooms.setSaveEnabled(false);
        binding.sliderTime.setSaveEnabled(false);
        binding.tvTime.setText("0-24");
        binding.sliderTime.addOnChangeListener(new RangeSlider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull RangeSlider slider, float value, boolean fromUser) {
                int fromHour = (int) (float) slider.getValues().get(0);
                int toHour = (int) (float) slider.getValues().get(1);
                presenter.onStartingTimeChange(fromHour);
                presenter.onEndingTimeChange(toHour);

                binding.tvTime.setText(String.format("%s-%s", fromHour, toHour));
            }
        });

        binding.btnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onApplyButtonClick();
                dismiss();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.onDetachView();
        roomsChipAdapter = null;
        binding = null;
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        presenter.onDismissDialog();
    }

    @Override
    public void onRoomSelect(Room room) {
        presenter.onRoomSelect(room);
    }

    @Override
    public void onRoomUnselect(Room room) {
        presenter.onRoomUnselect(room);
    }

    @Override
    public void setTimeSlider(int startingHours, int endingHours) {
        binding.sliderTime.setValues((float) startingHours, (float) endingHours);
    }

    public void setOnFilterDataReceiveListener(OnFilterDataReceiveListener onFilterDataReceiveListener) {
        this.onFilterDataReceiveListener = onFilterDataReceiveListener;
    }

    @Override
    public void sendFilterData(MeetingListFilterData filterData) {
        if (onFilterDataReceiveListener != null) {
            onFilterDataReceiveListener.onFilterDataReceive(filterData);
        }
    }

    public interface OnFilterDataReceiveListener {
        void onFilterDataReceive(MeetingListFilterData filterData);
    }
}
