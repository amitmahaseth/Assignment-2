package com.example.assignment3update.activity.services;

import android.app.IntentService;
import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.assignment3update.activity.database.DataBaseHelper;
import com.example.assignment3update.activity.model.StudentDetail;

import static com.example.assignment3update.activity.fragment.AddUpdateStudentFragment.STUDENT_OBJ;

public class IntentServiceStudent extends IntentService {

    private StudentDetail studentDetail;
    public static final String INTENT_ADD_BROADCAST="intent broadcast add";
    public static final String INTENT_ADD_BROADCAST_STUDENT_OBJ="intent broadcast student add";

    public IntentServiceStudent() {
        super("Intent");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        studentDetail = intent.getParcelableExtra(STUDENT_OBJ);

        assert  studentDetail != null;
        if (studentDetail.getType() == 1) {
            boolean result=new DataBaseHelper(getApplicationContext()).insertData(studentDetail.getStudentName(), studentDetail.getClassName(), studentDetail.getRollNo());
            if (result){
                intent.setAction(INTENT_ADD_BROADCAST);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra(INTENT_ADD_BROADCAST_STUDENT_OBJ,studentDetail));
            }
        } else if (studentDetail.getType() == 2) {
            boolean result=new DataBaseHelper(getApplicationContext()).updateData(studentDetail);
            if (result){
                intent.setAction(INTENT_ADD_BROADCAST);
                LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent.putExtra(INTENT_ADD_BROADCAST_STUDENT_OBJ,studentDetail));
            }

        }

    }
}
