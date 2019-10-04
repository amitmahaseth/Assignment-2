package com.example.assignment_3.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.assignment_3.R;
import com.example.assignment_3.model.StudentDetail;
import com.example.assignment_3.util.CommonUtil;

import java.util.ArrayList;

public class AddStudentActivity extends AppCompatActivity {
    TextView tvTitle;
    EditText etStudentName, etClass, etRollNo;
    Button btnSubmit;
    ImageButton ivsort, ivSquares;
    ArrayList<StudentDetail> student = new ArrayList<StudentDetail>();

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        //Initialising the ui components.
        init();
        int code = getIntent().getIntExtra("Code", 0);
        final StudentDetail st = getIntent().getParcelableExtra("Object");
        if (code == 2) {
            tvTitle.setText(R.string.view_student);
            etStudentName.setText(st.getStudentName());
            etClass.setText(String.valueOf(st.getClassName()));
            etRollNo.setText(String.valueOf(st.getRollNo()));
            etStudentName.setBackground(getResources().getDrawable(R.drawable.et_view));
            etClass.setBackground(getResources().getDrawable(R.drawable.et_view));
            etRollNo.setBackground(getResources().getDrawable(R.drawable.et_view));
            etStudentName.setInputType(InputType.TYPE_NULL);
            etClass.setInputType(InputType.TYPE_NULL);
            etRollNo.setInputType(InputType.TYPE_NULL);
            btnSubmit.setText(R.string.go_back_btn);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        } else if (code == 3) {
            tvTitle.setText(R.string.update_student);
            etStudentName.requestFocus();
            etRollNo.setText(String.valueOf(st.getRollNo()));
            etRollNo.setInputType(InputType.TYPE_NULL);

//Submit button event.
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String studentName = etStudentName.getText().toString();
                    int studentClass = Integer.parseInt(etClass.getText().toString());
                    int studentRollNo = st.getRollNo();
                    StudentDetail student = new StudentDetail(studentName, studentClass, studentRollNo);
                    Intent intent = new Intent(AddStudentActivity.this, HomeActivity.class);
                    intent.putExtra("Object", student);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            });
        } else {
            tvTitle.setText(R.string.add_student);
            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onSubmit();
                }
            });

        }
    }
//    //Onclick switch case.
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.
//        }
//
//    }

    //Ui initialiser function.
    public void init() {
        tvTitle = findViewById(R.id.tv_home);
        ivsort = findViewById(R.id.iv_sort);
        ivSquares = findViewById(R.id.iv_squares);
        etStudentName = findViewById(R.id.et_student_name);
        etClass = findViewById(R.id.et_class);
        etRollNo = findViewById(R.id.et_roll_no);
        btnSubmit = findViewById(R.id.btn_submit);
        ivSquares.setVisibility(View.INVISIBLE);
        ivsort.setVisibility(View.INVISIBLE);
    }


    //Onsubmit function.
    public void onSubmit() {
        String studentName = etStudentName.getText().toString().trim();
        String studentClass = etClass.getText().toString();
        String studentRollNo = etRollNo.getText().toString();

//validation on Add Student

        if (isValidate()) {
            StudentDetail student = new StudentDetail(studentName, Integer.parseInt(studentClass), Integer.parseInt(studentRollNo));
            Intent intent = new Intent(AddStudentActivity.this, HomeActivity.class);
            intent.putExtra("Object", student);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    private boolean isValidate() {
        String studentName = etStudentName.getText().toString().trim();
        String studentClass = etClass.getText().toString().trim();
        String studentRollNo = etRollNo.getText().toString().trim();

        String namePattern = ("^[a-zA-Z\\s]*$");
        if (studentName.isEmpty()) {
            CommonUtil.showSnackBar(AddStudentActivity.this, getResources().getString(R.string.label_enter_name));
            return false;

        } else if (studentName.equals(namePattern)) {
            CommonUtil.showSnackBar(AddStudentActivity.this, getResources().getString(R.string.label_invalid_name));
            return false;
        } else if (studentClass.isEmpty()) {
            CommonUtil.showSnackBar(AddStudentActivity.this, getResources().getString(R.string.enter_class));
            return false;
        } else if (studentRollNo.isEmpty()) {
            CommonUtil.showSnackBar(AddStudentActivity.this, getResources().getString(R.string.enter_roll));
            return false;
        }
        else if (Integer.parseInt(studentClass)>12 && Integer.parseInt((studentClass))<0){
            CommonUtil.showSnackBar(AddStudentActivity.this, getResources().getString(R.string.label_invalid_class));
            return false;
        } else if (Integer.parseInt(studentRollNo)<0) {
            CommonUtil.showSnackBar(AddStudentActivity.this, getResources().getString(R.string.label_invalid_roll));
            return false;
        }
        else {
            return true;
        }
    }
}

