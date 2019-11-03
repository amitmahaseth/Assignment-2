package com.example.assignment3update.activity.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.SyncStateContract;
import android.util.Log;

import com.example.assignment3update.activity.model.StudentDetail;

import static androidx.constraintlayout.widget.Constraints.TAG;


public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "CLASS";
    public static final String COL_3 = "ROLL";
    private SQLiteDatabase sqLiteDatabase;


    public DataBaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);
        Log.d(TAG, "DatabaseHelper: database created");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        //creating table
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(" + COL_1 + " text," + COL_2 + " integer," + COL_3 + " integer PRIMARY KEY);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    //insert student details to database
    public boolean insertData(String name,int classname,int roll) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,name);
        contentValues.put(COL_2,classname);
        contentValues.put(COL_3,roll);


        long result = sqLiteDatabase.insert(TABLE_NAME,null ,contentValues);
        return result!= -1;
    }

    //update student detail
    public Boolean updateData(StudentDetail studentDetail) {
        sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, studentDetail.getStudentName());
        contentValues.put(COL_2, studentDetail.getClassName());
        long result = sqLiteDatabase.update(TABLE_NAME, contentValues, COL_3 + "=" + studentDetail.getRollNo(), null);

        return result != -1;
    }

    //get all student details
    public Cursor getAllData() {
        sqLiteDatabase = this.getWritableDatabase();
        String[] columns = {COL_1, COL_2,COL_3};
        return sqLiteDatabase.query(TABLE_NAME, columns, null, null, null, null, null);

    }

    //delete data from database
    public boolean deleteData (int roll) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        long result=  sqLiteDatabase.delete(TABLE_NAME,COL_3+"="+roll ,null);
        return result!=-1;
    }

}