package com.example.assignment3update.activity.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.assignment3update.R;
import com.example.assignment3update.activity.model.StudentDetail;

import static com.example.assignment3update.activity.fragment.StudentListFragment.INTENT_KEY;

public class ViewActivity extends AppCompatActivity {
    private ImageButton ibSort,ibGrid;
    private TextView tvLabelHome;
    private EditText etViewName,etViewClass,etViewRoll;
    private Button btnViewBack;
    private StudentDetail studentDetails;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        init();

        studentDetails=getIntent().getParcelableExtra(INTENT_KEY);

        viewDetails();

        btnViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void viewDetails() {
        ibGrid.setVisibility(View.INVISIBLE);
        ibSort.setVisibility(View.INVISIBLE);
        tvLabelHome.setText(getResources().getString(R.string.btn_name_view));

        etViewName.setBackground(getResources().getDrawable(R.drawable.view_data));
        etViewClass.setBackground(getResources().getDrawable(R.drawable.view_data));
        etViewRoll.setBackground(getResources().getDrawable(R.drawable.view_data));

        etViewName.setInputType(InputType.TYPE_NULL);
        etViewClass.setInputType(InputType.TYPE_NULL);
        etViewRoll.setInputType(InputType.TYPE_NULL);
        btnViewBack.setText(R.string.label_back);

        etViewName.setText(studentDetails.getStudentName());
        etViewRoll.setText(String.valueOf(studentDetails.getRollNo()));
        etViewClass.setText(String.valueOf(studentDetails.getClassName()));
    }

    private void init() {
        ibSort=findViewById(R.id.iv_sort);
        ibGrid=findViewById(R.id.iv_squares);
        tvLabelHome=findViewById(R.id.tv_home);
        etViewName=findViewById(R.id.et_student_name);
        etViewRoll=findViewById(R.id.et_roll_no);
        etViewClass=findViewById(R.id.et_class);
        btnViewBack=findViewById(R.id.btn_submit);
    }
}
