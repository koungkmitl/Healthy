package com.example.koung.healthy.sleep;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.koung.healthy.R;
import com.example.koung.healthy.date.DatePickerFragment;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SleepFormFragment extends Fragment {

    private List<Sleep> sleepList;
    private SQLiteDatabase database;
    private CalculateDuration calculateDuration;
    private ContentValues preInsert;

    public SleepFormFragment() {
        database = getActivity().openOrCreateDatabase("SleepDB.db", MODE_PRIVATE, null);
        sleepList = new ArrayList<>();
        calculateDuration = new CalculateDuration();
        preInsert = new ContentValues();
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
                String _duration = calculateDuration.calculate(_sleepTime, _wakeupTime);

                preInsert.clear();

                preInsert.put("date", _date);
                preInsert.put("sleeptime", _sleepTime);
                preInsert.put("waketime", _wakeupTime);
                preInsert.put("duration", _duration);

                database.insert("Sleep", null, preInsert);
                database.close();

                ((EditText) getView().findViewById(R.id.sleep_form_text_date)).getText().clear();
                ((EditText) getView().findViewById(R.id.sleep_form_text_sleep)).getText().clear();
                ((EditText) getView().findViewById(R.id.sleep_form_text_wake)).getText().clear();

                Toast.makeText(getActivity(), "Insert complete", Toast.LENGTH_SHORT).show();
                Log.d("SLEEP", "Insert data to DB");
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
