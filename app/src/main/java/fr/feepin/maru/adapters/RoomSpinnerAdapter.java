package fr.feepin.maru.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import fr.feepin.maru.models.Room;

public class RoomSpinnerAdapter extends ArrayAdapter<Room> {

    private LayoutInflater inflater;

    public RoomSpinnerAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item, Room.values());
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Room room = getItem(position);
        View view = inflater.inflate(android.R.layout.simple_spinner_item, parent, false);
        TextView textView = view.findViewById(android.R.id.text1);
        textView.setText(getContext().getString(room.getRoomName()));

        return view;
    }

}
