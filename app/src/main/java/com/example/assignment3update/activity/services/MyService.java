package com.example.assignment3update.activity.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.assignment3update.activity.database.DataBaseHelper;
import com.example.assignment3update.activity.model.StudentDetail;

import static com.example.assignment3update.activity.fragment.AddUpdateStudentFragment.STUDENT_OBJ;

public class MyService extends Service {
    private StudentDetail studentDetail;
    public static final String ADD_BROADCAST="broadcast add";
    public static final String ADD_BROADCAST_STUDENT_OBJ="broadcast student add";
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            studentDetail = intent.getParcelableExtra(STUDENT_OBJ);
            assert  studentDetail != null;
            if (studentDetail.getType() == 1) {
                boolean result=new DataBaseHelper(getApplicationContext()).insertData(studentDetail.getStudentName(), studentDetail.getClassName(), studentDetail.getRollNo());
                if (result){

                    Intent intent1=new Intent(ADD_BROADCAST);
                    intent1.putExtra(ADD_BROADCAST_STUDENT_OBJ,studentDetail);
                    sendBroadcast(intent1);
                }
            } else if (studentDetail.getType() == 2) {
                boolean result=new DataBaseHelper(getApplicationContext()).updateData(studentDetail);
                if (result){
                    Intent intent1=new Intent(ADD_BROADCAST);
                    intent1.putExtra(ADD_BROADCAST_STUDENT_OBJ,studentDetail);
                    sendBroadcast(intent1);
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return START_STICKY;
    }
}
