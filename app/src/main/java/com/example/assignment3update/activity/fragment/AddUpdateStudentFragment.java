package com.example.assignment3update.activity.fragment;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

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

import java.util.ArrayList;


public class AddUpdateStudentFragment extends Fragment {

   private TextView tvTitle;
   private EditText etStudentName, etClass, etRollNo;
   private Button btnAdd;
   private ImageButton ivsort, ivSquares;
    private Context context;
    private int code;
    private   ArrayList<StudentDetail> student = new ArrayList<StudentDetail>();


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
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
//        ivSquares.setVisibility(View.INVISIBLE);
//        ivsort.setVisibility(View.INVISIBLE);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addDetails();
            }


        });
        return v;
    }

    //update data
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void updateStudentDetail(final int clickedPosiition, final StudentDetail studentObj, Boolean updateIsClicked) {

        if (updateIsClicked){

            etRollNo.setInputType(InputType.TYPE_NULL);
            etRollNo.setBackground(getResources().getDrawable(R.drawable.view_data));
            btnAdd.setText(getResources().getString(R.string.label_update));

            etStudentName.setText(studentObj.getStudentName());
            etRollNo.setText(String.valueOf(studentObj.getRollNo()));
            etClass.setText(String.valueOf(studentObj.getClassName()));

            code=2;

            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String etUpdatedName=etStudentName.getText().toString().trim();
                    final int etUpdatedClass=Integer.parseInt(etClass.getText().toString().trim());
                    int etUpdatedRoll=studentObj.getRollNo();


                    final StudentDetail updatedStudentObj=new StudentDetail(etUpdatedName,etUpdatedClass,etUpdatedRoll);

                    ((HomeActivity)context).onDataUpdated(clickedPosiition,updatedStudentObj);

                    ((HomeActivity)context).switchPager();

                }
            });

        }

    }

    // Add data
    private void addDetails() {
        String etAddName = etStudentName.getText().toString().trim();
        String etAddClass = etClass.getText().toString().trim();
        String etAddRollNo = etRollNo.getText().toString().trim();
        if (isValidate()) {
            StudentDetail studentDetail = new StudentDetail(etAddName, Integer.parseInt(etAddClass), Integer.parseInt(etAddRollNo));
            ((HomeActivity) context).addStudentData(studentDetail);
            ((HomeActivity) context).switchPager();
            etStudentName.setText("");
            etRollNo.setText("");
            etClass.setText("");
        }

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
}
