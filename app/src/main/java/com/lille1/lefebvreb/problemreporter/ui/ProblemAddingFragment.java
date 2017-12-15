package com.lille1.lefebvreb.problemreporter.ui;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.util.Log;
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
import com.lille1.lefebvreb.problemreporter.prgm.AddressAPI;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

/**
 * Created by lefebvreb on 13/12/17.
 */

public class ProblemAddingFragment extends Fragment implements View.OnClickListener {

    private ProblemEntity problemEntity = new ProblemEntity();
    private LocationManager locationManager;
    private LocationListener locationListener;
    private String problemType;
    private Activity activity;

    public ProblemAddingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_problem_adding, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        
        ArrayList<String> pbs = new ArrayList<>();
        for (ProblemTypeEnum type : ProblemTypeEnum.values()) {
            pbs.add(type.getLabel());
        }
        Spinner spinnerType = getActivity().findViewById(R.id.problem_adding_type);
        ProblemTypeAdapter problemTypeAdapter = new ProblemTypeAdapter(getContext(), R.layout.spinner_item_problem_type, R.id.spinner_problem_type_name, pbs);
        spinnerType.setAdapter(problemTypeAdapter);

        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        getCurrentLocation();

        /*
         * Listeners
         */
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
        switch (view.getId()) {
            case R.id.problem_adding_validate:
                String name = ((TextView) getActivity().findViewById(R.id.problem_adding_name)).getText().toString();
                String description = ((TextView) getActivity().findViewById(R.id.problem_adding_description)).getText().toString();
                String address = ((TextView) getActivity().findViewById(R.id.problem_adding_address)).getText().toString();
                problemEntity.setName(name);
                problemEntity.setDescription(description);
                problemEntity.setAddress(address);
                problemEntity.setType(problemType);
                problemEntity.save();
                getActivity().onBackPressed();
                break;
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        locationManager.removeUpdates(locationListener);
        Log.i(TAG, "onPause, done");
    }

    @Override
    public void onResume(){
        super.onResume();
        Log.i(TAG, "onResume, done");
        getCurrentLocation();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }

    private void getCurrentLocation() {
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                String address = "";
                try {
                    AddressAPI addressAPI = new AddressAPI(location.getLatitude(), location.getLongitude());
                    if(addressAPI.guessAddressFromCoordinates()) {
                        address = addressAPI.getAddress();
                        TextView addressInput = activity.findViewById(R.id.problem_adding_address);
                        if(addressInput != null)
                            addressInput.setText(address);
                    }
                    else
                        Toast.makeText(activity, addressAPI.getError(), Toast.LENGTH_LONG).show();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                problemEntity.setLatitude(location.getLatitude());
                problemEntity.setLongitude(location.getLongitude());
                problemEntity.setAddress(address);
            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {
            }

            @Override
            public void onProviderEnabled(String s) {
            }

            @Override
            public void onProviderDisabled(String s) {
            }
        };

        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getContext(), R.string.permission_gps_missing, Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
        locationManager.requestLocationUpdates(chooseBestLocationProvider(), 10000, 0, locationListener);
    }

    private String chooseBestLocationProvider(){
        String provider = LocationManager.NETWORK_PROVIDER;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            provider = LocationManager.GPS_PROVIDER;
        }
        return provider;
    }
}
