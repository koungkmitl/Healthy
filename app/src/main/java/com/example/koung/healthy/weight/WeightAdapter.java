package com.example.koung.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends ArrayAdapter<Weight> {

    private List<Weight> weights = new ArrayList<>();
    private Context context;


    public WeightAdapter(@NonNull Context context,
                         int resource,
                         @NonNull List<Weight> objects) {
        super(context, resource, objects);

        this.context = context;
        this.weights = (ArrayList<Weight>) objects;
    }

    @NonNull
    @Override
    public View getView(int position,
                        @Nullable View convertView,
                        @NonNull ViewGroup parent) {
        return super.getView(position, convertView, parent);
    }
}
