package com.example.koung.healthy.sleep;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.koung.healthy.MenuFragment;
import com.example.koung.healthy.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class SleepFragment extends Fragment {

    private SQLiteDatabase database;
    private static final String SQL_LISTALL_SLEEP = "SELECT * FROM Sleep";
    private List<Sleep> sleepList;

    public SleepFragment() {
        database = getActivity().openOrCreateDatabase("SleepDB", MODE_PRIVATE, null);
        sleepList = new ArrayList<>();
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
        SleepAdapter sleepAdapter = new SleepAdapter(
                getActivity(),
                R.layout.fragment_sleep_item,
                sleepList
        );

        ListView listView = getView().findViewById(R.id.sleep_listView_list);

        listView.setAdapter(sleepAdapter);

        // query data from sqlite


    }
}
