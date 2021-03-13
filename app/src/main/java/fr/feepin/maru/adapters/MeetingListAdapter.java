package fr.feepin.maru.adapters;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import fr.feepin.maru.R;
import fr.feepin.maru.databinding.ItemMeetingBinding;
import fr.feepin.maru.models.Meeting;
import fr.feepin.maru.utils.DateFormatUtil;
import fr.feepin.maru.utils.StringUtil;

public class MeetingListAdapter extends ListAdapter<Meeting, MeetingListAdapter.ViewHolder> {

    private TextView selectedTextView;

    private static final DiffUtil.ItemCallback<Meeting> diffCallback = new DiffUtil.ItemCallback<Meeting>() {
        @Override
        public boolean areItemsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Meeting oldItem, @NonNull Meeting newItem) {
            return oldItem == newItem;
        }
    };

    private OnMeetingDelete onMeetingDeleteListener;

    public MeetingListAdapter(OnMeetingDelete onMeetingDeleteListener) {
        super(diffCallback);
        this.onMeetingDeleteListener = onMeetingDeleteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        return new ViewHolder(layoutInflater.inflate(R.layout.item_meeting, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = getCurrentList().get(position);
        holder.bind(meeting);
    }

    public void setOnMeetingDeleteListener(OnMeetingDelete onMeetingDeleteListener) {
        this.onMeetingDeleteListener = onMeetingDeleteListener;
    }

    public OnMeetingDelete getOnMeetingDeleteListener() {
        return onMeetingDeleteListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemMeetingBinding binding;

        public ViewHolder(View rootView) {
            super(rootView);
            binding = ItemMeetingBinding.bind(rootView);
            binding.ivDeleteIcon.setOnClickListener(view -> {
                if (getAdapterPosition() == -1) return;
                onMeetingDeleteListener.onMeetingDelete(getCurrentList().get(getAdapterPosition()));
            });
        }

        @SuppressLint("ClickableViewAccessibility")
        public void bind(Meeting meeting) {
            binding.tvMeetingInfo.setText(
                    String.format("%s - %s - %s", binding.getRoot().getContext().getString(meeting.getRoom().getRoomName()), DateFormatUtil.formatToTime(meeting.getStartingTime()), meeting.getSubject())
            );
            binding.tvMeetingParticipants.setText(StringUtil.join(", ", meeting.getParticipantsEmail()));
            binding.ivMeetingIcon.setImageTintList(ColorStateList.valueOf(
                    ContextCompat.getColor(
                            binding.getRoot().getContext(),
                            meeting.getRoom().getRoomColor()
                    )
            ));
            binding.getRoot().setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                        if (selectedTextView == binding.tvMeetingParticipants) return false;
                        if (selectedTextView != null) {
                            selectedTextView.setSelected(false);
                            binding.tvMeetingParticipants.setSelected(true);
                            selectedTextView = binding.tvMeetingParticipants;
                        } else {
                            binding.tvMeetingParticipants.setSelected(true);
                            selectedTextView = binding.tvMeetingParticipants;
                        }
                    }
                    return false;
                }
            });
        }

    }

    public interface OnMeetingDelete {
        void onMeetingDelete(Meeting meeting);
    }
}
