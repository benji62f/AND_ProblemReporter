package com.lille1.lefebvreb.problemreporter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lille1.lefebvreb.problemreporter.R;

import java.util.ArrayList;

/**
 * Created by Benjamin on 14/12/2017.
 */

public class ProblemTypeAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> problems;

    public ProblemTypeAdapter(Context context, int resource, int textViewResourceId, ArrayList<String> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.problems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item_problem_type, parent, false);

        final String item = getItem(position);
        ((TextView) view.findViewById(R.id.spinner_problem_type_name)).setText(item);

        return view;
    }
}
