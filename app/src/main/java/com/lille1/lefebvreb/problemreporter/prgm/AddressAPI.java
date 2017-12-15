package com.lille1.lefebvreb.problemreporter.prgm;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

/**
 * Created by Benjamin on 14/12/2017.
 */

public class AddressAPI {
    private double latitude;
    private double longitude;
    private String address;
    private String error;

    public AddressAPI(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * Queries Google Book API to find an address from coordinates.
     * @return JSON containing the address.
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public boolean guessAddressFromCoordinates() throws MalformedURLException, ExecutionException, InterruptedException {
        String addressSearchString = "http://maps.googleapis.com/maps/api/geocode/json?latlng="+latitude+","+longitude+"&sensor=true";
        URL url = new URL(addressSearchString);
        String stringJSON = new RetrieveInfo().execute(url).get();
        if(extractAddressFromJSON(stringJSON)) {
            return true;
        }
        else
            return false;
    }

    private class RetrieveInfo extends AsyncTask<URL, Void, String> {

        protected String doInBackground(URL... urls) {
            String res = "";
            try {
                URL url = urls[0];
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                if(urlConnection.getResponseCode() != 200)
                    return null;
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                res = readStream(in);
            } catch (Exception e){
                return null;
            }

            return res;
        }

        protected void onPostExecute(String result) {
            System.out.println("Result : "+result);
        }

        private String readStream(InputStream in) throws IOException {
            StringBuilder sb = new StringBuilder();
            BufferedReader r = new BufferedReader(new InputStreamReader(in), 1000);
            for (String line = r.readLine(); line != null; line = r.readLine()){
                sb.append(line);
            }
            in.close();
            return sb.toString();
        }
    }

    /**
     * Extract the address of the JSON string
     * @param stringJSON
     * @return
     */
    private boolean extractAddressFromJSON(String stringJSON){
        try {
            JSONObject json = new JSONObject(stringJSON);
            if(json.has("error_message")){
                error = json.getString("error_message");
                return false;
            }
            if(json.has("results")){
                json = (JSONObject) json.getJSONArray("results").get(0);
                if(json.has("formatted_address")){
                    address = json.getString("formatted_address");
                    return true;
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public String getAddress(){
        return this.address;
    }

    public String getError(){
        return this.error;
    }
}
