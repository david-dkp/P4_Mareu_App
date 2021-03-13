package fr.feepin.maru.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import fr.feepin.maru.R;
import fr.feepin.maru.databinding.ItemAddParticipantBinding;
import fr.feepin.maru.databinding.ItemParticipantBinding;

public class AddParticipantListAdapter extends ListAdapter<String, AddParticipantListAdapter.ViewHolder> {

    private OnItemClickListener listener;

    private static DiffUtil.ItemCallback<String> diffCallback = new DiffUtil.ItemCallback<String>() {
        @Override
        public boolean areItemsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }

        @Override
        public boolean areContentsTheSame(@NonNull String oldItem, @NonNull String newItem) {
            return oldItem.equals(newItem);
        }
    };

    public AddParticipantListAdapter(OnItemClickListener listener) {
        super(diffCallback);
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_add_participant, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ItemAddParticipantBinding binding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemAddParticipantBinding.bind(itemView);
        }

        public void bind(String email) {
            binding.tvParticipant.setText(email);
            binding.getRoot().setOnClickListener((v) -> {
                listener.onClick(email);
            });
        }
    }

    public interface OnItemClickListener {
        void onClick(String email);
    }
}
