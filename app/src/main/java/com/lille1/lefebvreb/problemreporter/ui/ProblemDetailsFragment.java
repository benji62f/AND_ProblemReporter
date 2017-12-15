package com.lille1.lefebvreb.problemreporter.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lille1.lefebvreb.problemreporter.R;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemEntity;
import com.lille1.lefebvreb.problemreporter.db.repository.ProblemRepository;

/**
 * Created by Benjamin on 14/12/2017.
 */

public class ProblemDetailsFragment extends Fragment implements View.OnClickListener {

    private ProblemEntity problemEntity;

    public ProblemDetailsFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        problemEntity = ProblemRepository.getById(getArguments().getInt("problemId"));

        return inflater.inflate(R.layout.fragment_problem_details, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_action_edit:
                return true;
            case R.id.menu_action_delete:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ((TextView) getActivity().findViewById(R.id.problem_details_name)).setText(problemEntity.getName());
        ((TextView) getActivity().findViewById(R.id.problem_details_address)).setText(problemEntity.getAddress());
        ((TextView) getActivity().findViewById(R.id.problem_details_type)).setText(problemEntity.getType());
        ((TextView) getActivity().findViewById(R.id.problem_details_description)).setText(problemEntity.getDescription());

        /*
         * Listeners
         */
        getActivity().findViewById(R.id.problem_details_btn_map).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.problem_details_btn_map:
                String url = "https://www.google.fr/maps/@"+problemEntity.getLatitude()+","+problemEntity.getLongitude()+",20z";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
        }
    }
}
