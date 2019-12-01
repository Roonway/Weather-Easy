package Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.List;

public class CityDB extends SQLiteOpenHelper {


    private static final String BASE_NAME = "WeatherDB";
    private static final int VERSION_DB = 1;


    public CityDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, BASE_NAME, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sqlCreateTableCidade = "CREATE TABLE cidade("
                                     +"id INTEGER PRIMARY KEY,"
                                     +"nome TEXT,"
                                     +"log INTEGER,"
                                     +"lat INTEGER"
                                     +")" ;

        db.execSQL(sqlCreateTableCidade);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sqlDropTableCidade = "DROP TABLE cidade";
        db.execSQL(sqlDropTableCidade);

        onCreate(db);

    }

    public void adicionarCidade (City cidade){

        SQLiteDatabase db = getReadableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("id", cidade.getID());
        cv.put("nome", cidade.getNome());
        cv.put("log", cidade.getLog());
        cv.put("lat", cidade.getLat());

        db.insert("cidade", null, cv);

        db.close();
    }

    public List<City> selectTodasCidades() {

        List<City> listaCidades = new ArrayList<Citys>();

        SQLiteDatabase db = getReadableDatabase();

        String sqlSelectCidades = "SELECT * FROM cidade";

        Cursor c = db.rawQuery(sqlSelectCidades, null);

        if (c.moveToFirst()) {
            do {
                City cidade = new City();
                cidade.setID(c.getInt(0));
                cidade.setNome(c.getString(1));
                cidade.setLog(c.getInt(2));
                cidade.setLat(c.getInt(3));

                listaCidades.add(cidade);
            }while(c.moveToNext());
        }
        db.close();
        return listaCidades;
    }


}
