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

import fr.feepin.maru.R;
import fr.feepin.maru.models.Room;

public class RoomSpinnerAdapter extends ArrayAdapter<Room> {

    private LayoutInflater inflater;

    public RoomSpinnerAdapter(@NonNull Context context) {
        super(context, android.R.layout.simple_spinner_item, Room.values());
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Room room = getItem(position);
        View view = inflater.inflate(R.layout.item_subject, parent, false);
        TextView textView = view.findViewById(R.id.tvSubject);
        textView.setText(getContext().getString(room.getRoomName()));

        return view;
    }
}
