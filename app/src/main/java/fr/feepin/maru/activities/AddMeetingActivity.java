package fr.feepin.maru.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.TransitionManager;

import com.google.android.material.transition.MaterialContainerTransform;

import java.util.ArrayList;
import java.util.List;

import fr.feepin.maru.R;
import fr.feepin.maru.adapters.AddParticipantListAdapter;
import fr.feepin.maru.adapters.ParticipantListAdapter;
import fr.feepin.maru.adapters.RoomSpinnerAdapter;
import fr.feepin.maru.data.local.FakeMeetingApi;
import fr.feepin.maru.databinding.ActivityAddMeetingBinding;
import fr.feepin.maru.models.Room;
import fr.feepin.maru.presenters.interfaces.AddMeetingMvpPresenter;
import fr.feepin.maru.presenters.impl.AddMeetingPresenter;
import fr.feepin.maru.utils.DateUtil;
import fr.feepin.maru.views.AddMeetingMvpView;

public class AddMeetingActivity extends AppCompatActivity implements
        ParticipantListAdapter.OnParticipantClickListener,
        AddParticipantListAdapter.OnAddParticipantClickListener,
        AddMeetingMvpView {

    private ActivityAddMeetingBinding binding;
    private ParticipantListAdapter participantListAdapter;
    private AddParticipantListAdapter addParticipantListAdapter;
    private AddMeetingMvpPresenter presenter;

    private OnBackPressedCallback addParticipantViewBackPressCallback = new OnBackPressedCallback(false) {
        @Override
        public void handleOnBackPressed() {
            toggleAddParticipantView(false);
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddMeetingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getOnBackPressedDispatcher().addCallback(this, addParticipantViewBackPressCallback);

        presenter = new AddMeetingPresenter(FakeMeetingApi.getInstance());

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        setupParticipantRecyclerView();
        setupAddParticipantRecyclerView();
        setupAddParticipantButton();
        setupRoomSpinner();
        setupSubjectField();
        setupTime();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_meeting_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addMeeting) {
            presenter.onAddMeetingItemClick();
            return true;
        }
        return false;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onAttachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        binding = null;
        presenter.onDetachView();
    }

    private void setupParticipantRecyclerView() {
        participantListAdapter = new ParticipantListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        binding.rvParticipants.setLayoutManager(linearLayoutManager);
        binding.rvParticipants.setAdapter(participantListAdapter);
    }

    private void setupAddParticipantRecyclerView() {
        addParticipantListAdapter = new AddParticipantListAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        binding.rvAddParticipant.setLayoutManager(linearLayoutManager);
        binding.rvAddParticipant.setAdapter(addParticipantListAdapter);

        //Setup scrim
        binding.scrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onScrimClick();
            }
        });
    }

    private void setupAddParticipantButton() {
        binding.ivAddParticipant.setOnClickListener((v) -> {
            presenter.onAddParticipantButtonClick();
        });
    }

    private void setupRoomSpinner() {
        RoomSpinnerAdapter roomSpinnerAdapter = new RoomSpinnerAdapter(this);
        binding.spinnerRooms.setAdapter(roomSpinnerAdapter);
        binding.spinnerRooms.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                presenter.onRoomSelect(roomSpinnerAdapter.getItem(i));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        binding.spinnerRooms.setSelection(0);
        presenter.onRoomSelect(roomSpinnerAdapter.getItem(0));
    }

    private void setupSubjectField() {
        binding.textInputMeetingSubject.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                presenter.onSubjectChange(editable.toString());
            }
        });
    }

    private void setupTime() {
        presenter.onTimeChange(DateUtil.getDateMillisFromTime(binding.timePicker.getCurrentHour(), binding.timePicker.getCurrentMinute()));
        binding.timePicker.setSaveEnabled(false);
        binding.timePicker.setOnTimeChangedListener((timePicker, i, i1) -> presenter.onTimeChange(DateUtil.getDateMillisFromTime(i, i1)));
    }

    @Override
    public void onAddParticipantClick(String email) {
        presenter.onAddParticipantClick(email);
    }

    @Override
    public void onParticipantClick(String email) {
        presenter.onParticipantClick(email);
    }

    @Override
    public void setParticipantList(List<String> participants) {
        participantListAdapter.submitList(new ArrayList<>(participants));
    }

    @Override
    public void setAddParticipantList(List<String> participants) {
        addParticipantListAdapter.submitList(new ArrayList<>(participants));
    }

    @Override
    public void toggleAddParticipantView(boolean open) {
        addParticipantViewBackPressCallback.setEnabled(open);
        MaterialContainerTransform containerTransform = new MaterialContainerTransform();

        if (open) {
            containerTransform.setStartView(binding.ivAddParticipant);
            containerTransform.setEndView(binding.rvAddParticipant);
            containerTransform.addTarget(binding.rvAddParticipant);
            containerTransform.setScrimColor(Color.TRANSPARENT);

            TransitionManager.beginDelayedTransition(binding.getRoot(), containerTransform);
            binding.scrim.setVisibility(View.VISIBLE);
            binding.ivAddParticipant.setVisibility(View.INVISIBLE);
            binding.rvAddParticipant.setVisibility(View.VISIBLE);
        } else {
            containerTransform.setStartView(binding.rvAddParticipant);
            containerTransform.setEndView(binding.ivAddParticipant);
            containerTransform.addTarget(binding.ivAddParticipant);
            containerTransform.setScrimColor(Color.TRANSPARENT);

            TransitionManager.beginDelayedTransition(binding.getRoot(), containerTransform);
            binding.scrim.setVisibility(View.INVISIBLE);
            binding.ivAddParticipant.setVisibility(View.VISIBLE);
            binding.rvAddParticipant.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void closeActivity() {
        finish();
    }
}
