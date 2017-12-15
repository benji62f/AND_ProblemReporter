package com.lille1.lefebvreb.problemreporter.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lille1.lefebvreb.problemreporter.R;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemEntity;
import com.lille1.lefebvreb.problemreporter.db.entity.ProblemTypeEnum;
import com.lille1.lefebvreb.problemreporter.db.repository.ProblemRepository;

import java.util.ArrayList;

/**
 * Created by lefebvreb on 13/12/17.
 */

public class ProblemListFragment extends ListFragment implements View.OnClickListener {

    private ArrayList<ProblemEntity> problems;
    private ProblemListAdapter problemListAdapter;

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
        problems = (ArrayList) ProblemRepository.getAll();
        setHasOptionsMenu(true);

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_problem_list, container, false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_problem_list, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.menu_action_insert_fixtures:
                insertFixtures();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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

        ListView listView = this.getListView();

        problemListAdapter = new ProblemListAdapter(getContext(), R.layout.fragment_problem_list_item, problems);
        listView.setAdapter(problemListAdapter);

        /*
         * Listeners
         */
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long l){
                ProblemEntity selected = problems.get(position);
                Bundle args = new Bundle();
                args.putInt("problemId", selected.getId());
                ProblemDetailsFragment problemDetailsFragment = new ProblemDetailsFragment();
                problemDetailsFragment.setArguments(args);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, problemDetailsFragment).addToBackStack(null).commit();
            }
        });

        getActivity().findViewById(R.id.problem_create_btn_add).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.problem_create_btn_add:
                //open problem create fragment
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, new ProblemAddingFragment()).addToBackStack(null).commit();
                break;
        }
    }

    private void insertFixtures() {
        ProblemRepository.clearTable();
        ProblemRepository.insert("Problème 1", ProblemTypeEnum.ARBRE_A_ABATTRE.getLabel(), "Métro Cité Scientifique", "Arbre mort, gênant pour la circulation.", 50.6115472, 3.1424036);
        ProblemRepository.insert("Problème 2", ProblemTypeEnum.ARBRE_A_ABATTRE.getLabel(), "IUT Lille", "Arbre mort, gênant pour la circulation.", 50.6136265, 3.1370606);
        ProblemRepository.insert("Problème 3", ProblemTypeEnum.ARBRE_A_ABATTRE.getLabel(), "Résidence Universitaire Hélène Boucher", "Arbre mort, gênant pour la circulation.", 50.6106789, 3.1380163);
        ProblemRepository.insert("Problème 4", ProblemTypeEnum.MAUVAISE_HERBE.getLabel(), "Résidence Léonard de Vinci", "Pelouse à tondre.", 50.6101191, 3.1482737);
        ProblemRepository.insert("Problème 5", ProblemTypeEnum.MAUVAISE_HERBE.getLabel(), "Bibliothèque universitaire", "Pelouse à tondre.", 50.6093001, 3.1420491);
        ProblemRepository.insert("Problème 6", ProblemTypeEnum.MAUVAISE_HERBE.getLabel(), "Polytech Lille", "Pelouse à tondre.", 50.6076152, 3.1369324);
        ProblemRepository.insert("Problème 7", ProblemTypeEnum.DETRITUS.getLabel(), "4 Cantons - Grand Stade", "Amat de détritus à dégager.", 50.6054298, 3.1389672);
        ProblemRepository.insert("Problème 8", ProblemTypeEnum.HAIE_A_TAILLER.getLabel(), "Résidence Universitaire Albert Camus", "Pelouse à tondre.", 50.6046235, 3.1362821);
        ProblemRepository.insert("Problème 9", ProblemTypeEnum.MAUVAISE_HERBE.getLabel(), "Restaurant Universitaire Sully", "Pelouse à tondre.", 50.6054554, 3.1364592);
        ProblemRepository.insert("Problème 10", ProblemTypeEnum.MAUVAISE_HERBE.getLabel(), "Restaurant Universitaire Sully", "Pelouse à tondre.", 50.6096044, 3.1363221);
        problems = (ArrayList<ProblemEntity>) ProblemRepository.getAll();
        problemListAdapter.clear();
        problemListAdapter.addAll(problems);
        problemListAdapter.notifyDataSetChanged();
    }
}
