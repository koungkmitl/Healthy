package com.example.koung.healthy.weight;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.koung.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class WeightAdapter extends ArrayAdapter<Weight> {

    private List<Weight> weights;
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

        View weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_weight_item,
                parent,
                false);

        TextView dateText = (TextView) weightItem.findViewById(R.id.fragment_weight_date);
        TextView weightText = (TextView) weightItem.findViewById(R.id.fragment_weight_weight);
        TextView updownText = (TextView) weightItem.findViewById(R.id.fragment_weight_updown);

        Weight weight = weights.get(position);

        dateText.setText(weight.getDate());
        weightText.setText(String.valueOf(weight.getWeight()));
        updownText.setText(weight.getStatus());

        return weightItem;
    }
}
