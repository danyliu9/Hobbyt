package com.example.danielryu.hobbyt.presenter;

import android.content.res.Resources;
import android.location.Location;
import android.os.AsyncTask;
import com.example.danielryu.hobbyt.R;
import com.example.danielryu.hobbyt.model.Weather;
import com.example.danielryu.hobbyt.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class WeatherManager extends Manager{

    private static String API_KEY = Resources.getSystem().getString(R.string.weather_api_key);

    public WeatherManager(View v) {
        super(v);
    }

    public static void getCurrentWeather(Location location) {
        double lat =  location.getLatitude();
        double lon = location.getLongitude();

        downloadWeather(lat, lon);
    }

    private static void downloadWeather(double lat, double lon) {
        DownloadTask dt = new DownloadTask(lat, lon);
        dt.execute();
    }

    private static void parse(JSONObject o) {
        // Refer to https://openweathermap.org/weather-conditions to make sense of these codes.
        try {
            JSONArray weatherArray = o.getJSONArray("weather");
            JSONObject weatherData = weatherArray.getJSONObject(0);

            String weatherType = weatherData.getString("id");

            char weatherCode = weatherType.charAt(0);

            if (weatherCode == '3' || weatherCode == '5')
                v.receiveData(Weather.RAINY);
            else if (weatherCode == '6')
                v.receiveData(Weather.SNOWING);
            else if (weatherCode == '8') {
                if (weatherType.equals("800") || weatherType.equals("801"))
                    v.receiveData(Weather.SUNNY);
                else
                    v.receiveData(Weather.CLOUDY);
            }
            else if (weatherCode == '2')
                v.receiveData(Weather.EXTREME);
            else {
                if (weatherType.equals("701") || weatherType.equals("741"))
                    v.receiveData(Weather.CLOUDY);
                else
                    v.receiveData(Weather.EXTREME);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private static class DownloadTask extends AsyncTask<String, String, JSONObject> {
        double lat;
        double lon;

        public DownloadTask(double lat, double lon) {
            super();
            this.lat = lat;
            this.lon = lon;
        }

        private JSONObject downloadJSONObject(URL url) {
            JSONObject obj = null;
            try {
                URLConnection conn = url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                StringBuilder line = new StringBuilder("");
                while (in.readLine() != null) {
                    line.append(in.readLine());
                }

                obj = new JSONObject(line.toString());

                in.close();

                return obj;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Should never reach here...

            if (obj == null)
                throw new RuntimeException("Something went wrong");

            return obj;
        }

        @Override
        protected JSONObject doInBackground(String... strings) {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon
                    + "&APPID=" + API_KEY;
            JSONObject obj = null;
            try {
                URL url = new URL(urlString);
                obj = downloadJSONObject(url);
                return obj;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }

            // Should never reach here, but just in case...
            if (obj == null)
                throw new RuntimeException("Somnething went wrong");

            return obj;
        }

        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            WeatherManager.parse(jsonObject);
        }
    }


}
