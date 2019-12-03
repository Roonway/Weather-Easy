package com.example.weathereasy.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.weathereasy.models.City;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    private static final String BASE_NAME = "weatherDB";
    private static final int VERSION_DB = 7;


    public DBHelper(Context context) {
        super(context, BASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateTableCidade = "CREATE TABLE cidade("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "nome VARCHAR(250) NOT NULL UNIQUE"
                + ")";

        db.execSQL(sqlCreateTableCidade);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTableCidade = "DROP TABLE cidade";
        db.execSQL(sqlDropTableCidade);

        onCreate(db);

    }

    public void adicionarCidade(City cidade) {

        SQLiteDatabase db = getReadableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("nome", cidade.getName());

        db.insert("cidade", null, cv);

        db.close();
    }



    public List<City> selectTodasCidades() {

        List<City> listaCidades = new ArrayList<City>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectCidades = "SELECT * FROM cidade";

        Cursor c = db.rawQuery(sqlSelectCidades, null);

        if (c.moveToFirst()) {
            do {
                City cidade = new City();
                cidade.setName(c.getString(1));

                listaCidades.add(cidade);
            } while (c.moveToNext());
        }
        db.close();
        return listaCidades;
    }

    public void removerCidade (String name) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete("cidade", "nome" + "=" + name, null);
        db.close();
    }
}
