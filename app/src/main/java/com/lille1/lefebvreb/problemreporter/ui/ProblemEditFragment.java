package com.lille1.lefebvreb.problemreporter.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lille1.lefebvreb.problemreporter.R;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemEntity;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEnum;
import com.lille1.lefebvreb.problemreporter.db.repository.ProblemRepository;

import java.util.ArrayList;

/**
 * Created by Benjamin on 15/12/2017.
 */

public class ProblemEditFragment extends Fragment implements View.OnClickListener {

    private ProblemEntity problemEntity;
    private String problemType;

    public ProblemEditFragment(){
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        problemEntity = ProblemRepository.getById(getArguments().getInt("problemId"));

        return inflater.inflate(R.layout.fragment_problem_adding, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((TextView) getActivity().findViewById(R.id.problem_adding_name)).setText(problemEntity.getName());
        ((TextView) getActivity().findViewById(R.id.problem_adding_address)).setText(problemEntity.getAddress());
        ((TextView) getActivity().findViewById(R.id.problem_adding_description)).setText(problemEntity.getDescription());

        ArrayList<String> pbs = new ArrayList<>();
        for (ProblemTypeEnum type : ProblemTypeEnum.values()) {
            pbs.add(type.getLabel());
        }
        Spinner spinnerType = getActivity().findViewById(R.id.problem_adding_type);
        ProblemTypeAdapter problemTypeAdapter = new ProblemTypeAdapter(getContext(), R.layout.spinner_item_problem_type, R.id.spinner_problem_type_name, pbs);
        spinnerType.setAdapter(problemTypeAdapter);

        /*
         * Listeners
         */
        getActivity().findViewById(R.id.problem_adding_validate).setOnClickListener(this);
        getActivity().findViewById(R.id.problem_adding_validate).setOnClickListener(this);
        ((Spinner) getActivity().findViewById(R.id.problem_adding_type)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                problemType = (String) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                problemType = (String) adapterView.getItemAtPosition(0);
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.problem_adding_validate:
                String name = ((TextView) getActivity().findViewById(R.id.problem_adding_name)).getText().toString();
                String description = ((TextView) getActivity().findViewById(R.id.problem_adding_description)).getText().toString();
                String address = ((TextView) getActivity().findViewById(R.id.problem_adding_address)).getText().toString();

                if(name.equals("") || address.equals("")) {
                    Toast.makeText(getContext(), R.string.problem_adding_validation, Toast.LENGTH_LONG).show();
                    return;
                }

                problemEntity.setName(name);
                problemEntity.setDescription(description);
                problemEntity.setAddress(address);
                problemEntity.setType(problemType);
                problemEntity.save();
                getActivity().onBackPressed();
                break;
        }
    }
}
