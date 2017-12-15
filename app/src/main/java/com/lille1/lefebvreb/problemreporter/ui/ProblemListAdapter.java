package com.lille1.lefebvreb.problemreporter.ui;

/**
 * Created by Benjamin on 15/12/2017.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.lille1.lefebvreb.problemreporter.R;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemEntity;

import java.util.ArrayList;

/**
 * Created by Benjamin on 08/12/2016.
 */

public class ProblemListAdapter extends ArrayAdapter<ProblemEntity> {

    private Context context;
    private ArrayList<ProblemEntity> problems;

    public ProblemListAdapter(Context context, int resource, ArrayList<ProblemEntity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.problems = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_problem_list_item, parent, false);

        final ProblemEntity item = getItem(position);
        ((TextView) view.findViewById(R.id.problem_item_name)).setText(item.getName());

        ((TextView) view.findViewById(R.id.problem_item_type)).setText(item.getType());

        ((TextView) view.findViewById(R.id.problem_item_address)).setText(item.getAddress());
        return view;
    }
}