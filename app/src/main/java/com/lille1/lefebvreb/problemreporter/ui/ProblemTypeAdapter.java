package com.lille1.lefebvreb.problemreporter.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lille1.lefebvreb.problemreporter.R;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEntity;

import java.util.ArrayList;

/**
 * Created by Benjamin on 14/12/2017.
 */

public class ProblemTypeAdapter extends ArrayAdapter<ProblemTypeEntity> {

    private Context context;
    private ArrayList<ProblemTypeEntity> problems;

    public ProblemTypeAdapter(Context context, int resource, int textViewResourceId, ArrayList<ProblemTypeEntity> objects) {
        super(context, resource, textViewResourceId, objects);
        this.context = context;
        this.problems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.spinner_item_problem_type, parent, false);

        final ProblemTypeEntity item = getItem(position);
        ((TextView) view.findViewById(R.id.spinner_problem_type_name)).setText(item.getName());

        return view;
    }
}
