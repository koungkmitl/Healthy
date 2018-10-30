package com.example.koung.healthy.sleep;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.koung.healthy.R;
import com.example.koung.healthy.date.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SleepFormFragment extends Fragment {

    private List<Sleep> sleepList;
    private SQLiteDatabase database;
    private CalculateDuration calculateDuration;

    public SleepFormFragment() {
        database = getActivity().openOrCreateDatabase("SleepDB", MODE_PRIVATE, null);
        sleepList = new ArrayList<>();
        calculateDuration = new CalculateDuration();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep_form, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onClickBack();
        onClickAdd();
        onClickBack();
    }

    private void onClickBack() {
        Button backBtn = getView().findViewById(R.id.sleep_form_btn_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, new SleepFragment())
                        .addToBackStack(null)
                        .commit();
            }
        });
    }

    private void onClickAdd() {
        Button addBtn = getView().findViewById(R.id.sleep_form_btn_save);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _date = ((EditText) getView().findViewById(R.id.sleep_form_text_date)).getText().toString();
                String _sleepTime = ((EditText) getView().findViewById(R.id.sleep_form_text_sleep)).getText().toString();
                String _wakeupTime = ((EditText) getView().findViewById(R.id.sleep_form_text_wake)).getText().toString();


            }
        });
    }

    private void onClickDate() {
        EditText date = getView().findViewById(R.id.sleep_form_text_date);

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment datePicker = new DatePickerFragment();
                datePicker.show(getFragmentManager(), "Data Picker");
            }
        });
    }
}
