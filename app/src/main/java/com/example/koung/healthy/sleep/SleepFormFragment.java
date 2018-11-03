package com.example.koung.healthy.sleep;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
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

    private SQLiteDatabase database;
    private CalculateDuration calculateDuration;
    private ContentValues preInsert;
    private Cursor query;
    private int state = 1;

    public SleepFormFragment() {
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
        Bundle bundle = getArguments();

        if (bundle != null) {
            setValueFromSleepFragment(bundle.getString("date"));
        }
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
        database = getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);

        Button addBtn = getView().findViewById(R.id.sleep_form_btn_save);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText dateEdit = getView().findViewById(R.id.sleep_form_text_date);
                EditText sleepTimeEdit = getView().findViewById(R.id.sleep_form_text_sleep);
                EditText wakeupTimeEdit = getView().findViewById(R.id.sleep_form_text_wake);

                String _date = dateEdit.getText().toString();
                String _sleepTime = sleepTimeEdit.getText().toString();
                String _wakeupTime = wakeupTimeEdit.getText().toString();
                String _duration = calculateDuration.calculate(_sleepTime, _wakeupTime);

                preInsert.clear();

                if (isEmpty()) {
                    Toast.makeText(getActivity(), "Empty filed happen", Toast.LENGTH_SHORT).show();
                    Log.d("SLEEP", "Empty filed");
                } else {
                    if (state == 0) {
                        updateValue();
                        Toast.makeText(getActivity(), "Update complete", Toast.LENGTH_SHORT).show();
                        Log.d("SLEEP", "Update value complete");
                    } else {
                        preInsert.put("date", _date);
                        preInsert.put("sleeptime", _sleepTime);
                        preInsert.put("wakeuptime", _wakeupTime);
                        preInsert.put("duration", _duration);

                        database.insert("sleep", null, preInsert);
                        database.close();

                        dateEdit.getText().clear();
                        sleepTimeEdit.getText().clear();
                        wakeupTimeEdit.getText().clear();

                        Toast.makeText(getActivity(), "Insert complete", Toast.LENGTH_SHORT).show();
                        Log.d("SLEEP", "Insert data to DB");
                    }
                }
            }
        });
    }

    private boolean isEmpty() {
        String _date = ((EditText) getView().findViewById(R.id.sleep_form_text_date)).getText().toString();
        String _sleepTime = ((EditText) getView().findViewById(R.id.sleep_form_text_sleep)).getText().toString();
        String _wakeupTime = ((EditText) getView().findViewById(R.id.sleep_form_text_wake)).getText().toString();

        if (_date.isEmpty() || _sleepTime.isEmpty() || _wakeupTime.isEmpty()) {
            return true;
        }
        return false;
    }

    private void setValueFromSleepFragment(String date) {
        database = getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);

        EditText dateEdit = getView().findViewById(R.id.sleep_form_text_date);
        EditText sleepTimeEdit = getView().findViewById(R.id.sleep_form_text_sleep);
        EditText wakeupTimeEdit = getView().findViewById(R.id.sleep_form_text_wake);

        query = database.rawQuery("SELECT * FROM sleep WHERE date=" + "'" + date + "'", null);

        query.moveToNext();

        dateEdit.setText(query.getString(0));
        sleepTimeEdit.setText(query.getString(1));
        wakeupTimeEdit.setText(query.getString(2));

        state = 0;

        query.close();
        database.close();
    }

    private void updateValue() {
        database = getActivity().openOrCreateDatabase("my.db", MODE_PRIVATE, null);

        String dateEdit = ((EditText) getView().findViewById(R.id.sleep_form_text_date)).getText().toString();
        String sleepTimeEdit = ((EditText) getView().findViewById(R.id.sleep_form_text_sleep)).getText().toString();
        String wakeupTimeEdit = ((EditText) getView().findViewById(R.id.sleep_form_text_wake)).getText().toString();
        String durationEdit = calculateDuration.calculate(sleepTimeEdit, wakeupTimeEdit);


        database.execSQL(String.format("UPDATE sleep " +
                "SET sleeptime='%s', wakeuptime='%s', duration='%s'" +
                "WHERE date='%s'", sleepTimeEdit, wakeupTimeEdit, durationEdit, dateEdit));

        database.close();
    }
}
