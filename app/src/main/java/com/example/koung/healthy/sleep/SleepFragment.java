package com.example.koung.healthy.sleep;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.koung.healthy.MenuFragment;
import com.example.koung.healthy.R;

import java.util.ArrayList;
import java.util.List;


public class SleepFragment extends Fragment {

    private static final String SQL_LISTALL_SLEEP = "SELECT * FROM sleep";
    private SQLiteDatabase database;
    private Cursor query;

    private List<Sleep> sleepList;
    private SleepAdapter sleepAdapter;
    private ListView listView;

    private Bundle bundle;

    public SleepFragment() {
        sleepList = new ArrayList<>();
        bundle = new Bundle();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sleep, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        onClickAdd();
        onClickBack();
        showListView();
    }

    private void onClickBack() {
        Button backBtn = getView().findViewById(R.id.sleep_btn_back);

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view, new MenuFragment())
                        .commit();
            }
        });
    }

    private void onClickAdd() {
        Button addBtn = getView().findViewById(R.id.sleep_btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.main_view, new SleepFormFragment())
                        .commit();
            }
        });
    }

    private void showListView() {
        database = getActivity().openOrCreateDatabase("my.db", Context.MODE_PRIVATE, null);

        String _date;
        String _sleeptime;
        String _wakeuptime;
        String _duration;

        String _format;


        sleepAdapter = new SleepAdapter(
                getActivity(),
                R.layout.fragment_sleep_item,
                sleepList
        );

        listView = getView().findViewById(R.id.sleep_listView_list);
        listView.setAdapter(sleepAdapter);

        sleepAdapter.clear();

        query = database.rawQuery(SQL_LISTALL_SLEEP, null);

        while(query.moveToNext()) {
            _date = query.getString(0);
            _sleeptime = query.getString(1);
            _wakeuptime = query.getString(2);
            _duration = query.getString(3);

            _format = _sleeptime + " - " + _wakeuptime;

            sleepList.add(new Sleep(_date, _format, _duration));
        }

        query.close();
        database.close();
        sleepAdapter.notifyDataSetChanged();

        onClickListView(listView);
    }

    private void onClickListView(ListView listView) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Sleep sleep = (Sleep) parent.getItemAtPosition(position);

                bundle.putString("date", sleep.getDate());

                SleepFormFragment sleepFormFragment = new SleepFormFragment();
                sleepFormFragment.setArguments(bundle);

                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_view, sleepFormFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });
    }


}
