package com.example.assignment3update.activity.fragment;


import android.app.Dialog;
import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3update.R;
import com.example.assignment3update.activity.activity.HomeActivity;
import com.example.assignment3update.activity.activity.MainActivity;
import com.example.assignment3update.activity.model.StudentDetail;
import com.example.assignment3update.activity.services.AsyncTaskStudentList;
import com.example.assignment3update.activity.services.IntentServiceStudent;
import com.example.assignment3update.activity.services.MyService;

import static com.example.assignment3update.activity.services.IntentServiceStudent.INTENT_ADD_BROADCAST;
import static com.example.assignment3update.activity.services.IntentServiceStudent.INTENT_ADD_BROADCAST_STUDENT_OBJ;
import static com.example.assignment3update.activity.services.MyService.ADD_BROADCAST;
import static com.example.assignment3update.activity.services.MyService.ADD_BROADCAST_STUDENT_OBJ;

public class AddUpdateStudentFragment extends Fragment implements AsyncTaskStudentList.AsyncTaskback {

    private TextView tvTitle;
    private EditText etStudentName, etClass, etRollNo;
    private Button btnAdd;
    private ImageButton ivsort, ivSquares;
    private Context context;
    private int code, updatClickedPosition;
    private boolean isAddClicked = true;
    private StudentDetail studentDetail;
    private boolean isAddUpdateFrag = false;
    public static final String STUDENT_OBJ = "student object";


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public AddUpdateStudentFragment(boolean isAddUpdateFrag) {
        this.isAddUpdateFrag = isAddUpdateFrag;
    }

    public AddUpdateStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_update_student, container, false);

        tvTitle = v.findViewById(R.id.tv_home);
        ivsort = v.findViewById(R.id.iv_sort);
        ivSquares = v.findViewById(R.id.iv_squares);
        etStudentName = v.findViewById(R.id.et_student_name);
        etClass = v.findViewById(R.id.et_class);
        etRollNo = v.findViewById(R.id.et_roll_no);
        btnAdd = v.findViewById(R.id.btn_submit);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAddUpdateClicked();
            }


        });
        return v;
    }

    //opens dialog
    private void onAddUpdateClicked() {
        if (isValidate()) {
            final Dialog dialog = new Dialog(context);
            dialog.setContentView(R.layout.dialog);

            Button btnAsyncTask = dialog.findViewById(R.id.btn_view);
            Button btnService = dialog.findViewById(R.id.btn_edit);
            Button btnIntentService = dialog.findViewById(R.id.btn_delete);

            btnAsyncTask.setText(R.string.asynctask);
            btnService.setText(R.string.service);
            btnIntentService.setText(R.string.intent_service);

            btnAsyncTask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAddClicked) {
                        addStudentDetailServices(1);
                        dialog.dismiss();
                    } else {
                        isAddClicked = true;
                        updateStudentDetailServices(1);
                        dialog.dismiss();
                    }
                }
            });

            btnService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAddClicked) {
                        addStudentDetailServices(2);
                        dialog.dismiss();
                    } else {
                        isAddClicked = true;
                        updateStudentDetailServices(2);
                        dialog.dismiss();
                    }
                }
            });

            btnIntentService.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isAddClicked) {
                        addStudentDetailServices(3);
                        dialog.dismiss();
                    } else {
                        isAddClicked = true;
                        updateStudentDetailServices(3);
                        dialog.dismiss();
                    }
                }
            });
            dialog.show();
        }
    }

    //add data through services
    private void addStudentDetailServices(int service) {
        String etAddName = etStudentName.getText().toString().trim();
        String etAddClass = etClass.getText().toString().trim();
        String etAddRollNo = etRollNo.getText().toString().trim();
        StudentDetail studentDetail = new StudentDetail(etAddName, Integer.parseInt(etAddClass), Integer.parseInt(etAddRollNo), 1);

        if (service == 1) {
            new AsyncTaskStudentList(context, this).execute(studentDetail);
            Toast.makeText(context, "add async", Toast.LENGTH_SHORT).show();
        } else if (service == 2) {
            Intent intent = new Intent(context, MyService.class);
            intent.putExtra(STUDENT_OBJ, studentDetail);
            context.startService(intent);
            Toast.makeText(context, "add service", Toast.LENGTH_SHORT).show();

        } else if (service == 3) {
            Intent intent = new Intent(context, IntentServiceStudent.class);
            intent.putExtra(STUDENT_OBJ, studentDetail);
            ((HomeActivity) context).startService(intent);

        }
    }

    //update data
    private void updateStudentDetailServices(int service) {
        String etUpdatedName = etStudentName.getText().toString().trim();
        final int etUpdatedClass = Integer.parseInt(etClass.getText().toString().trim());
        int etUpdatedRoll = studentDetail.getRollNo();
        final StudentDetail updatedStudentObj = new StudentDetail(etUpdatedName, etUpdatedClass, etUpdatedRoll, 2);

        if (service == 1) {
            new AsyncTaskStudentList(context, this).execute(updatedStudentObj);
            Toast.makeText(context, "update async", Toast.LENGTH_SHORT).show();

        } else if (service == 2) {
            Intent intent = new Intent(context, MyService.class);
            intent.putExtra(STUDENT_OBJ, updatedStudentObj);
            context.startService(intent);
            Toast.makeText(context, "update service", Toast.LENGTH_SHORT).show();
        } else if (service == 3) {
            Intent intent = new Intent(context, IntentServiceStudent.class);
            intent.putExtra(STUDENT_OBJ, updatedStudentObj);
            context.startService(intent);

            Toast.makeText(context, "update intent service", Toast.LENGTH_SHORT).show();

        }
    }

    //update data
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void updateStudentDetail(final int clickedPosiition, final StudentDetail studentObj, Boolean updateIsClicked) {

        this.updatClickedPosition = clickedPosiition;
        this.studentDetail = studentObj;
        isAddClicked = false;
        etRollNo.setInputType(InputType.TYPE_NULL);
        etRollNo.setBackground(getResources().getDrawable(R.drawable.view_data));
        btnAdd.setText(getResources().getString(R.string.label_update));

        etStudentName.setText(studentObj.getStudentName());
        etRollNo.setText(String.valueOf(studentObj.getRollNo()));
        etClass.setText(String.valueOf(studentObj.getClassName()));

    }


    //clear all et's
    private void clearEt() {
        etStudentName.setText("");
        etRollNo.setText("");
        etClass.setText("");
    }

    private void uiUpdateClicked() {
        etRollNo.setInputType(InputType.TYPE_CLASS_NUMBER);
        btnAdd.setText("Add");

    }

    //validation
    private boolean isValidate() {
        String studentName = etStudentName.getText().toString().trim();
        String className = etClass.getText().toString().trim();
        String rollNo = etRollNo.getText().toString().trim();
        String namePattern = ("^[a-zA-Z\\s]*$");
        if (studentName.isEmpty()) {
            etStudentName.setError(getResources().getString(R.string.label_empty_field));
            return false;
        } else if (studentName.equals(namePattern)) {
            etStudentName.setError(getResources().getString(R.string.error_valid_name));
            return false;
        } else if (className.isEmpty()) {
            etClass.setError(getResources().getString(R.string.label_empty_field_class));
            return false;
        } else if (rollNo.isEmpty()) {
            etRollNo.setError(getResources().getString(R.string.label_empty_field_roll_no));
            return false;
        } else if (Integer.parseInt(className) > 12) {
            etClass.setError(getResources().getString(R.string.error_wrong_class));
            return false;
        } else if (Integer.parseInt(className) < 0) {
            etClass.setError(getResources().getString(R.string.error_wrong_class));
            return false;
        } else if (Integer.parseInt(rollNo) < 0) {
            etRollNo.setError(getResources().getString(R.string.error_roll));
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void asyncClickListner(Boolean aBoolean, StudentDetail studentDetail) {

        if (aBoolean) {
            ((HomeActivity) context).onDataUpdated(updatClickedPosition, studentDetail);
            ((HomeActivity) context).switchPager();
            clearEt();
            uiUpdateClicked();
        }

    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            StudentDetail studentDetail = intent.getParcelableExtra(ADD_BROADCAST_STUDENT_OBJ);
            ((HomeActivity) context).onDataUpdated(updatClickedPosition, studentDetail);
            ((HomeActivity) context).switchPager();
            clearEt();
            uiUpdateClicked();

        }
    };

//    @Override
//    public void onStart() {
//        super.onStart();
//
//        if (isAddUpdateFrag) {
//            intentServiceReceiver();
//        }
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//
//    }


    private void intentServiceReceiver() {
        IntentServiceBroadcastReceiver intentServiceBroadcastReceiver = new IntentServiceBroadcastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(INTENT_ADD_BROADCAST);

        LocalBroadcastManager.getInstance(context).registerReceiver(intentServiceBroadcastReceiver, intentFilter);

    }

    //broadcast receiver for intent service






    public class IntentServiceBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            StudentDetail studentDetail = intent.getParcelableExtra(INTENT_ADD_BROADCAST_STUDENT_OBJ);

            ((HomeActivity)context).onDataUpdated(updatClickedPosition,studentDetail);

            clearEt();
            uiUpdateClicked();

        }
    }


    @Override
    public void onResume() {
        super.onResume();

        if (isAddUpdateFrag) {
            ((HomeActivity) context).registerReceiver(broadcastReceiver, new IntentFilter(ADD_BROADCAST));
        }
    }


    @Override
    public void onPause() {
        super.onPause();
        if (isAddUpdateFrag) {
            context.unregisterReceiver(broadcastReceiver);
        }

    }
}