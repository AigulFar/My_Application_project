package com.example.myapplication_project.ui.home;

import static com.example.myapplication_project.Single.getuser_id;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "MealLibrary.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_library";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MEAL = "meal";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_CALOURIES_COUNT = "calouries_count";


    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MEAL + " TEXT, " +
                        COLUMN_DATA + " TEXT, " +
                        COLUMN_CALOURIES_COUNT + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addMeal(String meal, String data, int calouries_count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_MEAL, meal);
        cv.put(COLUMN_DATA, data);
        cv.put(COLUMN_CALOURIES_COUNT, calouries_count);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Ошибка.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Запись добавлена!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData(){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery("SELECT * FROM my_library", null);
        }
        return cursor;
    }

    void updateData(String row_id, String meal, String data, String calouries_count){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_MEAL, meal);
        cv.put(COLUMN_DATA, data);
        cv.put(COLUMN_CALOURIES_COUNT, calouries_count);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Ошибка.", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Запись добавлена!", Toast.LENGTH_SHORT).show();
        }

    }

    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Ошибка при удалении.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Успешно удалено.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}
