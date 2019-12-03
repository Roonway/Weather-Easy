package com.example.weathereasy;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.androdocs.httprequest.HttpRequest;
import com.example.weathereasy.database.CityDB;
import com.example.weathereasy.models.City;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    String API = "e010e3bc6198cb9359ac35e4fc68dd10";

    Button addButton, deleteButton;
    EditText cityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.editText);

        addButton = (Button) findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buscar e Adicionar cidade a lista
                new cityThread(cityName.getText().toString()).execute();

            }
        });
        deleteButton = (Button) findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Busca no Banco a cidade e a deleta
            }
        });
    }

    public void addCity(City obj){
        CityDB dbh = new CityDB(this);
        dbh.adicionarCidade(obj);

    }
    public void removeCity(City obj){
        CityDB dbh = new CityDB(this);
        dbh.de(obj);

    }



    class cityThread extends AsyncTask<String, Void, String> {
        private final String cityName;

        cityThread(String cityName) {
            this.cityName = cityName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            /* Showing the ProgressBar, Making the main design GONE */
            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.mainContainer).setVisibility(View.GONE);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }


        @Override
        protected String doInBackground(String... args) {
            return HttpRequest.excuteGet("https://api.openweathermap.org/data/2.5/weather?q=" + cityName + "&units=metric&appid=" + API);
        }

        @Override
        protected void onPostExecute(String result) {
            City cidade = new City();

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONObject sys = jsonObj.getJSONObject("sys");

                cidade.setName(cityName);
                cidade.setCountryCode(sys.getString("country"));



                /* Views populated, Hiding the loader, Showing the main design */
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.mainContainer).setVisibility(View.VISIBLE);

            } catch (JSONException e) {
                findViewById(R.id.loader).setVisibility(View.GONE);
                findViewById(R.id.errorText).setVisibility(View.VISIBLE);
            }

        }
    }
}
