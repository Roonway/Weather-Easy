package com.example.weathereasy;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weathereasy.adapter.WeatherAdapterCustom;
import com.example.weathereasy.helpers.DBHelper;
import com.example.weathereasy.models.City;
import com.example.weathereasy.settings.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.widget.Toast.LENGTH_LONG;

public class MainActivity extends AppCompatActivity {
    String API = "e010e3bc6198cb9359ac35e4fc68dd10";
    String UNIT = "metric";

    private List<City> cities = new ArrayList<>();
    private WeatherAdapterCustom weatherAdapter;

    Button addButton, deleteButton;
    EditText cityName;
    ListView listCityWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityName = findViewById(R.id.editText);
        listCityWeather = findViewById(R.id.listCityWeather);
        addButton = (Button) findViewById(R.id.addButton);
        deleteButton = (Button) findViewById(R.id.deleteButton);

        weatherAdapter = new WeatherAdapterCustom(cities, this);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Buscar e Adicionar cidade a lista
                addCity(new City(cityName.getText().toString()));
                weatherAdapter.clear();
                new UpdateThread().execute();

            }
        });
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Busca no Banco a cidade e a deleta
                removeCity(cityName.getText().toString());
                weatherAdapter.clear();
                new UpdateThread().execute();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        weatherAdapter.clear();
        new UpdateThread().execute();
        listCityWeather.setAdapter(weatherAdapter);

    }

    public void addCity(City obj) {
        try {
            DBHelper dbh = new DBHelper(this);
            dbh.adicionarCidade(obj);

        } catch (SQLiteConstraintException e) {
            Toast toast = Toast.makeText(getApplicationContext(), "Cidade já Existe em sua Lista", LENGTH_LONG);
            toast.show();
        }

    }

    public void removeCity(String string) {
        DBHelper dbh = new DBHelper(this);
        dbh.removerCidade(string);


    }

    class UpdateThread extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            /* Showing the ProgressBar, Making the main design GONE */
            findViewById(R.id.loader).setVisibility(View.VISIBLE);
            findViewById(R.id.addButton).setEnabled(false);
            findViewById(R.id.deleteButton).setEnabled(false);
            findViewById(R.id.editText).setEnabled(false);
            findViewById(R.id.errorText).setVisibility(View.GONE);
        }


        @Override
        protected Void doInBackground(String... args) {
            DBHelper db = new DBHelper(getApplicationContext());
            List<City> listCites = db.selectTodasCidades();

            for (City city : listCites) {

                Call<City> call = new RetrofitConfig().getWeatherService().getCityWeather(city.getName(), UNIT, API);
                call.enqueue(new Callback<City>() {
                    @Override
                    public void onResponse(Call<City> call, Response<City> response) {
                        City cidade = response.body();
                        cities.add(cidade);
                        weatherAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<City> call, Throwable t) {
                        Log.e("WeatherService   ", "Erro ao buscar o Clima:" + t.getMessage());
                        Toast toast = Toast.makeText(getApplicationContext(), "Erro ao buscar o Clima:" + t.getMessage(), LENGTH_LONG);
                        toast.show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            /* Views populated, Hiding the loader, Showing the main design */
            findViewById(R.id.addButton).setEnabled(true);
            findViewById(R.id.deleteButton).setEnabled(true);
            findViewById(R.id.editText).setEnabled(true);
            findViewById(R.id.loader).setVisibility(View.GONE);
        }
    }
}
