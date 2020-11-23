package com.example.parkmycar;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "database";
    static int version = 1;
    String createTableUser = "CREATE TABLE if not exists \"user\" (\n\t\"id\"\tINTEGER NOT NULL UNIQUE,\n\t\"name\"\tTEXT NOT NULL,\n\t\"email\"\tTEXT NOT NULL UNIQUE,\n\t\"mobno\"\tINTEGER NOT NULL,\n\t\"password\"\tTEXT NOT NULL UNIQUE,\n\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n)";
    String createLocation =  "CREATE TABLE if not exists \"location\" (\n\t\"id\"\tINTEGER NOT NULL UNIQUE,\n\t\"name\"\tTEXT,\n\t\"description\"\tTEXT,\n\t\"capacity\"\tINTEGER,\n\t\"lat\"\tREAL,\n\t\"long\"\tREAL,\n\t\"image\"\tBLOB,\n\t\"opening_time\"\tTEXT,\n\t\"address\"\tTEXT,\n\t\"cost\"\tINTEGER,\n\t\"parking_type\"\tTEXT,\n\t\"contacts\"\tTEXT,\n\t\"parking_code\"\tTEXT,\n\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n)";

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
        Bitmap bitmap = null;
        ArrayList<HashMap<String,String>> locationList = new ArrayList<>();
        String query = "SELECT * FROM location";
        Cursor cursor = db.rawQuery(query, null);
        while (cursor.moveToNext()) {
            HashMap location = new HashMap();
            location.put("name", cursor.getString(cursor.getColumnIndex("name")));
            location.put("cost", cursor.getString(cursor.getColumnIndex("cost")));
            location.put("capacity", cursor.getString(cursor.getColumnIndex("capacity")));
            location.put("description", cursor.getString(cursor.getColumnIndex("description")));
            location.put("opening_time", cursor.getString(cursor.getColumnIndex("opening_time")));
            location.put("address", cursor.getString(cursor.getColumnIndex("address")));
            location.put("parking_type", cursor.getString(cursor.getColumnIndex("parking_type")));
            location.put("contacts", cursor.getString(cursor.getColumnIndex("contacts")));
            location.put("parking_code", cursor.getString(cursor.getColumnIndex("parking_code")));
            /*byte[] blob = cursor.getBlob(cursor.getColumnIndex("image"));
            bitmap = BitmapFactory.decodeByteArray(blob, 0, blob.length);
            location.put("image",bitmap);*/
            locationList.add(location);
        }
        return locationList;
    }
    public Cursor getLocationList(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from location", null);
        return res;
    }
}
