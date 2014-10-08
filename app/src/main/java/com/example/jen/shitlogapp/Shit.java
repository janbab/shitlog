package com.example.jen.shitlogapp;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.TimeZone;

/**
 * Created by jpizzurro on 2014/10/05.
 */
public class Shit {

    private int _id;
    private Calendar _dateTime;
    private int _type;
    private int _color;
    private int _size;
    private int _pain;
    private int _sickBefore;
    private int _sickAfter;

    public Shit() {}

    public Shit(int id, Calendar dateTime, int type, int color, int size, int pain, int sickBefore, int sickAfter) {
        this._id = id;
        this._dateTime = dateTime;
        this._type = type;
        this._color = color;
        this._size = size;
        this._pain = pain;
        this._sickBefore = sickBefore;
        this._sickAfter = sickAfter;
    }

    public Shit(Calendar dateTime, int type, int color, int size, int pain, int sickBefore, int sickAfter) {
        this._dateTime = dateTime;
        this._type = type;
        this._color = color;
        this._size = size;
        this._pain = pain;
        this._sickBefore = sickBefore;
        this._sickAfter = sickAfter;
    }

    public int getId() {
        return this._id;
    }
    public void setId(int id) {
        this._id = id;
    }

    public Calendar getDateTime() {
        return this._dateTime;
    }
    public void setDateTime(Calendar dateTime) {
        this._dateTime = dateTime;
    }

    public int getType() {
        return this._type;
    }
    public void setType(int type) {
        this._type = type;
    }

    public int getColor() {
        return this._color;
    }
    public void setColor(int color) {
        this._color = color;
    }

    public int getSize() {
        return this._size;
    }
    public void setSize(int size) {
        this._size = size;
    }

    public static final int PAIN_TYPE_STABBING = 0x01;
    public static final int PAIN_TYPE_BURNING = 0x02;
    public static final int PAIN_TYPE_EXPLOSIVE = 0x04;
    public static final int PAIN_TYPE_STRAINING = 0x08;
    public int getPain() {
        return this._pain;
    }
    public void setPain(int pain) {
        this._pain = pain;
    }

    public int getSickBefore() {
        return this._sickBefore;
    }
    public void setSickBefore(int sickBefore) {
        this._sickBefore = sickBefore;
    }

    public int getSickAfter() {
        return this._sickAfter;
    }
    public void setSickAfter(int sickAfter) {
        this._sickAfter = sickAfter;
    }

    @Override
    public String toString() {
        String string =
                "id = " + getId() + "; " +
                "dateTime = " + getDateTime().getTimeInMillis() + "; " +
                "type = " + getType() + "; " +
                "color = " + getColor() + "; " +
                "size = " + getSize() + "; " +
                "pain = " + getPain() + "; " +
                "sickBefore = " + getSickBefore() + "; " +
                "sickAfter = " + getSickAfter();
        return string;
    }

}
