package com.yerimadevoir.myapplication.auth;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.yerimadevoir.myapplication.database.DatabaseHelper;

public class AuthenticationManager {

    private DatabaseHelper databaseHelper;

    public AuthenticationManager(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public boolean registerUser(String email, String password, String role) {
        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email", email);
        values.put("password", password);
        values.put("role", role);

        long result = db.insert("users", null, values);
        db.close();

        return result != -1;
    }

    public String loginUser(String email, String password) {
        String role = null;

        SQLiteDatabase db = databaseHelper.getReadableDatabase();

        String selectQuery = "SELECT role FROM users WHERE email = ? AND password = ?";
        Cursor cursor = db.rawQuery(selectQuery, new String[]{email, password});

        if (cursor.moveToFirst()) {
            role = cursor.getString(cursor.getColumnIndex("role"));
        }

        cursor.close();
        db.close();

        return role;
    }
}
