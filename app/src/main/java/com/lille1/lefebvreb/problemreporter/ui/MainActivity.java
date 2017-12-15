package com.lille1.lefebvreb.problemreporter.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.lille1.lefebvreb.problemreporter.R;

import static com.raizlabs.android.dbflow.config.FlowManager.getContext;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_ACCESS_LOCATION = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermissions();

        getSupportFragmentManager().beginTransaction().replace(R.id.activity_main_container, new ProblemListFragment()).commit();
    }

    private void checkPermissions() {
        if (!hasGPSPermissions() && !permissionAlreadyAsked()) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
            }, PERMISSION_ACCESS_LOCATION);
        } else if(!hasGPSPermissions()){
            closeApp();
        }
    }

    /**
     * Checks if the user accepted GPS permissions
     * @return
     */
    private boolean hasGPSPermissions(){
        return ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) ==
                PackageManager.PERMISSION_GRANTED;
    }

    /**
     * Check if the permission request was already done before
     * We must not ask for permission again if it has already been done
     * @return
     */
    private boolean permissionAlreadyAsked(){
        return ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                && ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_COARSE_LOCATION);
    }

    /**
     * Check user's answer for permission request
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_ACCESS_LOCATION:
                if(grantResults[0] == PackageManager.PERMISSION_DENIED)
                    closeApp();
                break;
        }
    }

    private void closeApp(){
        Toast.makeText(getContext(), R.string.permission_gps_missing, Toast.LENGTH_LONG).show();
        finish();
    }
}
