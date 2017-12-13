package com.lille1.lefebvreb.problemreporter.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lille1.lefebvreb.problemreporter.R;

/**
 * Created by lefebvreb on 13/12/17.
 */

public class ProblemListFragment extends ListFragment implements View.OnClickListener {

    public ProblemListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_problem_list, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getActivity().findViewById(R.id.problem_create_btn_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.problem_create_btn_add:
                //open problem create fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, new ProblemCreateFragment()).addToBackStack(null).commit();
                break;
        }
    }
}
