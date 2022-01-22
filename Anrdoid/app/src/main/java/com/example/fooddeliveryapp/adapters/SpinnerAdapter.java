package com.example.fooddeliveryapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fooddeliveryapp.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SpinnerAdapter extends BaseAdapter {
    private final LayoutInflater inflator;
    List<String> items ;

    public SpinnerAdapter(Context context , List<String>counting)
    {
        inflator = LayoutInflater.from(context);
        items= counting;
    }
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflator.inflate(R.layout.options_layout_selecter, null);
        TextView tv = (TextView) convertView.findViewById(R.id.textView);
        tv.setText(items.get(position));
        return convertView;
    }
}
