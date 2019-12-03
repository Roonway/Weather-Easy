package com.example.weathereasy.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.weathereasy.R;
import com.example.weathereasy.models.City;

import java.util.List;

public class WeatherAdapterCustom extends BaseAdapter {

    private final List<City> list;
    private final Activity act;

    public WeatherAdapterCustom(List<City> cityList, Activity act) {
        this.list = cityList;
        this.act = act;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       View view = act.getLayoutInflater().inflate(R.layout.item_list, parent, false);
       City cidade = list.get(position);

        //pegando as referências das Views
        TextView name = (TextView) view.findViewById(R.id.lista_personalizada_nome);
        TextView description = (TextView) view.findViewById(R.id.lista_personalizada_description);

        //populando as Views
        name.setText(cidade.toString());
        description.setText(cidade.getMain().toString());

       return view;
    }

    public void clear(){
        list.clear();
    }
}
