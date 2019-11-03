package com.example.assignment3update.activity.services;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.assignment3update.activity.database.DataBaseHelper;
import com.example.assignment3update.activity.model.StudentDetail;

public class AsyncTaskStudentList extends AsyncTask<StudentDetail,Void,Boolean> {

    private Context context;
    private AsyncTaskback listner;
    private StudentDetail studentDetail;


    public AsyncTaskStudentList(Context context, AsyncTaskback listner) {
        this.context = context;
        this.listner=listner;
    }

    @Override
    protected Boolean doInBackground(StudentDetail... studentDetails) {
       studentDetail= studentDetails[0];

        if (studentDetail.getType()==1) {
            return new DataBaseHelper(context).insertData(studentDetail.getStudentName(), studentDetail.getClassName(), studentDetail.getRollNo());
        }else if (studentDetail.getType()==2){
                return new DataBaseHelper(context).updateData(studentDetail);
        }else if (studentDetail.getType()==3){
            return new DataBaseHelper(context).deleteData(studentDetail.getRollNo());
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        listner.asyncClickListner(aBoolean,studentDetail);
    }

    public interface AsyncTaskback{
        void asyncClickListner(Boolean aBoolean, StudentDetail studentDetail);
    }
}


