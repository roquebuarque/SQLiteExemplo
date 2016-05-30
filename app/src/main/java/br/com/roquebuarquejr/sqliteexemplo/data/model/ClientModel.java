package br.com.roquebuarquejr.sqliteexemplo.data.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.roquebuarquejr.sqliteexemplo.data.DataBaseHelper;

/**
 * Created by roquebuarque on 29/05/16.
 */
public class ClientModel {

    private SQLiteDatabase db;
    private DataBaseHelper helper;

    public ClientModel(Context c) {
        helper = new DataBaseHelper(c);
    }

    public String insertClient(String name, String email) {
        ContentValues values;
        long result;
        db = helper.getWritableDatabase();
        values = new ContentValues();
        values.put(DataBaseHelper.NAME, name);
        values.put(DataBaseHelper.EMAIL, email);

        result = db.insert(DataBaseHelper.TALBE, null, values);

        if (result == -1)
            return "Error";
        else
            return "Success";


    }

    public Cursor listAllClient() {
        Cursor cursor;
        String[] campos = {DataBaseHelper.ID, DataBaseHelper.NAME, DataBaseHelper.EMAIL};
        db = helper.getReadableDatabase();
        cursor = db.query(DataBaseHelper.TALBE, campos, null, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public  Cursor loadClientById(int id){
        Cursor cursor;
        String[] campos = {DataBaseHelper.ID, DataBaseHelper.NAME, DataBaseHelper.EMAIL};
        db = helper.getReadableDatabase();
        String where = DataBaseHelper.ID +"="+ id;
        cursor = db.query(DataBaseHelper.TALBE, campos, where, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        db.close();
        return cursor;
    }

    public String delete(int id){
        String where = DataBaseHelper.ID + "="  +id;
        db = helper.getReadableDatabase();
        int result = db.delete(DataBaseHelper.TALBE, where, null);
        if(result == -1)
            return "Error";
        else
            return "Success";

    }

    public String udate(int id, String name, String email){

        ContentValues values;
        String where = DataBaseHelper.ID +"="+ id;

        db= helper.getWritableDatabase();
        values = new ContentValues();
        values.put(DataBaseHelper.NAME, name);
        values.put(DataBaseHelper.EMAIL, email);
        int result = db.update(DataBaseHelper.TALBE, values, where, null);

        if(result == -1)
            return "Error";
        else
            return "Success";

    }
}
