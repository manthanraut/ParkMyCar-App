package com.example.parkmycar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "database";
    static int version = 1;
    String createTableUser = "CREATE TABLE if not exists \"user\" (\n\t\"id\"\tINTEGER NOT NULL UNIQUE,\n\t\"name\"\tTEXT NOT NULL,\n\t\"email\"\tTEXT NOT NULL UNIQUE,\n\t\"mobno\"\tINTEGER NOT NULL,\n\t\"password\"\tTEXT NOT NULL UNIQUE,\n\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n)";
    String createLocation = "CREATE TABLE if not exists location (id INTEGER,name TEXT UNIQUE, description TEXT, capacity INTEGER,lat REAL,long REAL, image BLOB,PRIMARY KEY(id AUTOINCREMENT))";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(this.createTableUser);
        getWritableDatabase().execSQL(this.createLocation);
    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
    }

    public void insertLocation(ContentValues contentValues) {
        getWritableDatabase().insert("location", "", contentValues);
    }


    public boolean isLoginValid(String email, String password) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT count(*) FROM user WHERE email='");
        sql.append(email);
        sql.append("' and password='");
        sql.append(password);
        sql.append("'");
        SQLiteStatement stmt = getReadableDatabase().compileStatement(sql.toString());
        long l = stmt.simpleQueryForLong();
        stmt.close();
        if (l == 1) {
            return true;
        }
        return false;
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }

    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }

    public ArrayList<HashMap<String, String>> GetLocation() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<HashMap<String, String>> locationList = new ArrayList<>();
        String query = "SELECT name, description, capacity FROM location";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> location = new HashMap<>();
            location.put("name", cursor.getString(cursor.getColumnIndex("name")));
            location.put("description", cursor.getString(cursor.getColumnIndex("description")));
            location.put("capacity", cursor.getString(cursor.getColumnIndex("capacity")));
            locationList.add(location);
        }
        return locationList;
    }
}
