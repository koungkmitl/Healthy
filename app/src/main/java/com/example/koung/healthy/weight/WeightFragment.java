package com.example.koung.healthy.weight;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.koung.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class WeightFragment extends Fragment {

    private List<Weight> weight;

    public WeightFragment() {
        weight = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weight, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        aaa();
    }

    private void aaa() {
        weight.add(new Weight("aaa", 2.090, "bbb"));
        weight.add(new Weight("bbb", 3.009, "aaa"));

        WeightAdapter weightAdapter = new WeightAdapter(getContext(),
                                                        R.layout.fragment_weight_item,
                                                        weight);

        ListView listView = getView().findViewById(R.id.weight_list);

        listView.setAdapter(weightAdapter);



    }
}
