package fr.feepin.maru.adapters;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.widget.CompoundButton;

import androidx.core.content.ContextCompat;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;


import fr.feepin.maru.R;
import fr.feepin.maru.models.Room;

public class RoomsChipAdapter {

    private OnRoomSelectListener listener;
    private ChipGroup chipGroup;
    private Context context;

    public RoomsChipAdapter(ChipGroup chipGroup, OnRoomSelectListener listener) {
        this.listener = listener;
        this.chipGroup = chipGroup;
        this.context = chipGroup.getContext();
        createChips();
    }

    private void createChips() {
        for(Room room : Room.values()) {
            Chip chip = (Chip) LayoutInflater.from(context).inflate(R.layout.item_room_filter, chipGroup, false);

            int[][] colorStates = {
                    {android.R.attr.state_checked},
                    {}
            };

            int[] colors = {
                    ContextCompat.getColor(context, room.getRoomColor()),
                    Color.WHITE
            };

            ColorStateList colorStateList = new ColorStateList(colorStates, colors);
            chip.setChipBackgroundColor(colorStateList);

            chip.setText(context.getString(room.getRoomName()));

            chip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        listener.onRoomSelect(room);
                    } else {
                        listener.onRoomUnselect(room);
                    }
                }
            });

            chipGroup.addView(chip);
        }
    }

    public interface OnRoomSelectListener{
        void onRoomSelect(Room room);
        void onRoomUnselect(Room room);
    }
}
