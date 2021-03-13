package fr.feepin.maru.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.feepin.maru.models.Room;

public class RoomSpinnerAdapter extends ArrayAdapter<Room> {

    private OnRoomSelectListener listener;
    private LayoutInflater inflater;

    public RoomSpinnerAdapter(@NonNull Context context, OnRoomSelectListener listener) {
        super(context, android.R.layout.simple_spinner_item, Room.values());
        this.listener = listener;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Room room = getItem(position);
        View view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRoomSelect(room);
            }
        });

        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getContext().getString(room.getRoomName()));

        return view;
    }

    public interface OnRoomSelectListener {
        void onRoomSelect(Room room);
    }

}
