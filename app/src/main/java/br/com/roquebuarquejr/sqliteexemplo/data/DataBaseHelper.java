package br.com.roquebuarquejr.sqliteexemplo.data;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by roquebuarque on 29/05/16.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String BD_NAME = "sqlite_exemplo.db";
    public static final String TALBE = "client";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final int VERSION = 1;

    public DataBaseHelper(Context c) {
        super(c, BD_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createClitent(db);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TALBE);
        onCreate(db);

    }

    private void createClitent(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TALBE + "("
                + ID + " integer primary key autoincrement," +
                NAME + " text,"
                + EMAIL + " text)";

        db.execSQL(sql);

    }
}
