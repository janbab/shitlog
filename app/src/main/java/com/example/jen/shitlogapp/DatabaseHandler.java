package com.example.jen.shitlogapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by jpizzurro on 2014/10/05.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shitlog";
    private static final String TABLE_SHIT = "shits";

    private static final String KEY_ID = "id";
    private static final String KEY_DATETIME = "date_time";
    private static final String KEY_TYPE = "type";
    private static final String KEY_COLOR = "color";
    private static final String KEY_SIZE = "size";
    private static final String KEY_PAIN = "pain";
    private static final String KEY_SICKBEFORE = "sick_before";
    private static final String KEY_SICKAFTER = "sick_after";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String CREATE_SHITS_TABLE =
                "CREATE TABLE " + TABLE_SHIT + "("
                + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_DATETIME + " INTEGER,"
                + KEY_TYPE + " INTEGER,"
                + KEY_COLOR + " INTEGER,"
                + KEY_SIZE + " INTEGER,"
                + KEY_PAIN + " INTEGER,"
                + KEY_SICKBEFORE + " INTEGER,"
                + KEY_SICKAFTER + " INTEGER,"
                + ")";
        db.execSQL(CREATE_SHITS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SHIT);
        onCreate(db);
    }

    public void addShit(Shit shit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATETIME, shit.getDateTime().getTimeInMillis() / 1000L);
        values.put(KEY_TYPE, shit.getType());
        values.put(KEY_COLOR, shit.getColor());
        values.put(KEY_SIZE, shit.getSize());
        values.put(KEY_PAIN, shit.getPain());
        values.put(KEY_SICKBEFORE, shit.getSickBefore());
        values.put(KEY_SICKAFTER, shit.getSickAfter());

        db.insert(TABLE_SHIT, null, values);
        db.close();
    }

    public Shit getShit(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SHIT,
                new String[] { KEY_ID, KEY_DATETIME, KEY_TYPE, KEY_COLOR, KEY_SIZE, KEY_PAIN, KEY_SICKBEFORE, KEY_SICKAFTER }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }

        Calendar dateTime = Calendar.getInstance(TimeZone.getDefault());
        dateTime.setTimeInMillis(Long.parseLong(cursor.getString(1)) * 1000L);

        Shit shit = new Shit(
                Integer.parseInt(cursor.getString(0)),
                dateTime,
                Integer.parseInt(cursor.getString(2)),
                Integer.parseInt(cursor.getString(3)),
                Integer.parseInt(cursor.getString(4)),
                Integer.parseInt(cursor.getString(5)),
                Integer.parseInt(cursor.getString(6)),
                Integer.parseInt(cursor.getString(7)));
        return shit;
    }

    public List<Shit> getAllShits() {
        List<Shit> shitList = new ArrayList<Shit>();

        final String selectQuery = "SELECT  * FROM " + TABLE_SHIT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Shit shit = new Shit();
                shit.setId(Integer.parseInt(cursor.getString(0)));

                Calendar dateTime = Calendar.getInstance(TimeZone.getDefault());
                dateTime.setTimeInMillis(Long.parseLong(cursor.getString(1)) * 1000L);
                shit.setDateTime(dateTime);

                shit.setType(Integer.parseInt(cursor.getString(2)));
                shit.setColor(Integer.parseInt(cursor.getString(3)));
                shit.setSize(Integer.parseInt(cursor.getString(4)));
                shit.setPain(Integer.parseInt(cursor.getString(5)));
                shit.setSickBefore(Integer.parseInt(cursor.getString(6)));
                shit.setSickAfter(Integer.parseInt(cursor.getString(7)));

                shitList.add(shit);
            } while (cursor.moveToNext());
        }

        return shitList;
    }

    public int updateShit(Shit shit) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_DATETIME, shit.getDateTime().getTimeInMillis() / 1000L);
        values.put(KEY_TYPE, shit.getType());
        values.put(KEY_COLOR, shit.getColor());
        values.put(KEY_SIZE, shit.getSize());
        values.put(KEY_PAIN, shit.getPain());
        values.put(KEY_SICKBEFORE, shit.getSickBefore());
        values.put(KEY_SICKAFTER, shit.getSickAfter());

        // updating row
        return db.update(TABLE_SHIT, values, KEY_ID + " = ?",
                new String[] { String.valueOf(shit.getId()) });
    }

    public void deleteShit(Shit shit) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SHIT, KEY_ID + " = ?",
                new String[] { String.valueOf(shit.getId()) });
        db.close();
    }

    public int getShitCount() {
        final String countQuery = "SELECT  * FROM " + TABLE_SHIT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }

}
