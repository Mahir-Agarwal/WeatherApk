package com.example.weatherpromax;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    TextView humi, temp1, wind , longitude ,latitude;
    ImageView img ;
    EditText city;
    Button searchBtn;
    private final String API_KEY = "223b7c710a1f286e002a3d07578a1b53"; // Replace with your API Key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components
        humi = findViewById(R.id.textView5);
        temp1 = findViewById(R.id.textView3);
        wind = findViewById(R.id.textView0);
        city = findViewById(R.id.city);
        searchBtn = findViewById(R.id.btn);
        longitude = findViewById(R.id.log);
        latitude = findViewById(R.id.lan);
        img = findViewById(R.id.imageView2);
        // Initialize RecyclerView
        initRecyclerView();

        // Set button click listener
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fetchWeatherData();
            }
        });
    }

    private void fetchWeatherData() {
        String cityName = city.getText().toString().trim();

        // Check if the city name is empty
        if (cityName.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please enter a city", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&appid=" + API_KEY + "&units=metric";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String temp = String.valueOf(response.getJSONObject("main").getDouble("temp"));
                            String humidity = String.valueOf(response.getJSONObject("main").getInt("humidity"));
                            String windSpeed = String.valueOf(response.getJSONObject("wind").getDouble("speed"));
                            String lanText = String.valueOf(response.getJSONObject("coord").getDouble("lat"));
                            String logText = String.valueOf(response.getJSONObject("coord").getDouble("lon"));
                            String image = String.valueOf(response.getJSONArray("weather").getJSONObject(0).getString("icon"));

                            humi.setText(humidity + " %");
                            temp1.setText(temp + " °C");
                            wind.setText(windSpeed + " m/s");
                            latitude.setText("Latitude: " + lanText);
                            longitude.setText("Longitude: " + logText);

                            if (image.equals("10d") || image.equals("10n") || image.equals("09d") || image.equals("09n")) {
                                img.setImageResource(R.drawable.rainy); // Rainy weather
                            } else if (image.equals("13d") || image.equals("13n")) {
                                img.setImageResource(R.drawable.snowy); // Snowy weather
                            } else if (image.equals("11d") || image.equals("11n")) {
                                img.setImageResource(R.drawable.storm); // Stormy weather
                            } else if (image.equals("01d") || image.equals("01n") || image.equals("02d") || image.equals("02n")) {
                                img.setImageResource(R.drawable.cloudy1); // Clear or few clouds
                            } else if (image.equals("50d") || image.equals("50n")){
                                img.setImageResource(R.drawable.windy);
                            }
                            else {
                                img.setImageResource(R.drawable.cloudy1); // Default cloudy image (for unexpected cases)
                            }

                            Toast.makeText(getApplicationContext(), "Weather for: " + cityName, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            Log.e("WeatherError", "Error parsing JSON", e);
                            Toast.makeText(getApplicationContext(), "Invalid response from server", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("WeatherError", "Network error", error);
                Toast.makeText(getApplicationContext(), "Failed to fetch weather data", Toast.LENGTH_SHORT).show();
            }
        });

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    private void initRecyclerView() {
        ArrayList<hour> list = new ArrayList<>();
        list.add(new hour("10 PM", "cloudy", 28));
        list.add(new hour("12 PM", "sun", 28));
        list.add(new hour("5 AM", "rainy", 19));
        list.add(new hour("9 PM", "storm", 16));

        recyclerView = findViewById(R.id.view1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        adapter = new HourlyAdapter(list);
        recyclerView.setAdapter(adapter);
    }
}
