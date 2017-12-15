package com.lille1.lefebvreb.problemreporter.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
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
        setHasOptionsMenu(true);

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
                openEditFragment();
                return true;
            case R.id.menu_action_delete:
                showDeletionAlertDialog();
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
        switch (view.getId()) {
            case R.id.problem_details_btn_map:
                String url = "https://www.google.fr/maps/@" + problemEntity.getLatitude() + "," + problemEntity.getLongitude() + ",20z";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                break;
        }
    }

    private void showDeletionAlertDialog() {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle(R.string.alert_problem_delete_title)
                .setMessage(R.string.alert_problem_delete_message)
                .setPositiveButton(R.string.alert_problem_delete_answer_positive, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        problemEntity.delete();
                        getActivity().onBackPressed();
                    }
                })
                .setNegativeButton(R.string.alert_problem_delete_answer_negative, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void openEditFragment(){
        Bundle args = new Bundle();
        args.putInt("problemId", problemEntity.getId());
        ProblemEditFragment problemEditFragment = new ProblemEditFragment();
        problemEditFragment.setArguments(args);
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, problemEditFragment).addToBackStack(null).commit();
    }
}
