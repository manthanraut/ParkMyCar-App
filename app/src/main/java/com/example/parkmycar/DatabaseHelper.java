package com.example.parkmycar;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseHelper extends SQLiteOpenHelper {
    static String name = "database";
    static int version = 1;
    String createTableUser = "CREATE TABLE if not exists \"user\" (\n\t\"id\"\tINTEGER NOT NULL UNIQUE,\n\t\"name\"\tTEXT NOT NULL,\n\t\"email\"\tTEXT NOT NULL UNIQUE,\n\t\"mobno\"\tINTEGER NOT NULL,\n\t\"password\"\tTEXT NOT NULL UNIQUE,\n\tPRIMARY KEY(\"id\" AUTOINCREMENT)\n)";

    public DatabaseHelper(Context context) {
        super(context, name, null, version);
        getWritableDatabase().execSQL(this.createTableUser);
    }

    public void insertUser(ContentValues contentValues) {
        getWritableDatabase().insert("user", "", contentValues);
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
}
