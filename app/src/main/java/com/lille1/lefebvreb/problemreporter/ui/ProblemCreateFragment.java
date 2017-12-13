package com.lille1.lefebvreb.problemreporter.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lille1.lefebvreb.problemreporter.R;

/**
 * Created by lefebvreb on 13/12/17.
 */

public class ProblemCreateFragment extends Fragment {

    public ProblemCreateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_problem_create, container, false);
    }
}
