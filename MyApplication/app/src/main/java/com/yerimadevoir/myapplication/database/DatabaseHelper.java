package com.yerimadevoir.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "notes.db";
    private static final int DATABASE_VERSION = 1;

    // Définissez les noms de table et les colonnes nécessaires
    private static final String TABLE_NOTES = "notes";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_NOTE = "note";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créez la table des notes
        String createTableQuery = "CREATE TABLE " + TABLE_NOTES + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_NOTE + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Mettez à jour la base de données si nécessaire
        if (oldVersion < newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NOTES);
            onCreate(db);
        }
    }

    // Méthode pour ajouter une note à la base de données
    public void addNote(String note) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE, note);
        db.insert(TABLE_NOTES, null, values);
        db.close();
    }

    // Méthode pour récupérer toutes les notes de la base de données
    public List<String> getAllNotes() {
        List<String> notesList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NOTES, null);
        if (cursor.moveToFirst()) {
            do {
                String note = cursor.getString(cursor.getColumnIndex(COLUMN_NOTE));
                notesList.add(note);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return notesList;
    }
}