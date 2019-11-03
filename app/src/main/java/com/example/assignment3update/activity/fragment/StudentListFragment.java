package com.example.assignment3update.activity.fragment;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3update.R;
import com.example.assignment3update.activity.activity.HomeActivity;
import com.example.assignment3update.activity.activity.ViewActivity;
import com.example.assignment3update.activity.adapter.StudentAdapter;
import com.example.assignment3update.activity.database.DataBaseHelper;
import com.example.assignment3update.activity.model.StudentDetail;
import com.example.assignment3update.activity.services.AsyncTaskStudentList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class StudentListFragment extends Fragment implements StudentAdapter.ItemClicked,AsyncTaskStudentList.AsyncTaskback {


    private Button btnAddStudent;
    private RecyclerView recyclerView;
    private StudentAdapter myAdapter;
    private Context context;
    private TextView tvNoRecord;
    private Boolean updateClicked = true;

    private ArrayList<StudentDetail> studentlistCopy = new ArrayList<>();

    public static final String INTENT_KEY = "view";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    public StudentListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_student_list, container, false);
        tvNoRecord = v.findViewById(R.id.tv_display_student);
        btnAddStudent = v.findViewById(R.id.btn_add_student);
        recyclerView = (RecyclerView) v.findViewById(R.id.list);

        myAdapter = new StudentAdapter(this);

        Cursor cursor = new DataBaseHelper(context).getAllData();
        while (cursor.moveToNext()) {
            String name = cursor.getString(0);
            int studentClass = cursor.getInt(1);
            int roll = cursor.getInt(2);
            StudentDetail st = new StudentDetail(name, studentClass, roll, 0);
            studentlistCopy.add(st);
            myAdapter.setAdapterData(studentlistCopy);
            labelVisibility();
        }

        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(myAdapter);

        //btn Add student listener

        btnAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((HomeActivity) getActivity()).switchPager();
            }

        });
        return v;
    }

    //getting object from activity
    public void setData(StudentDetail studentlist) {
        studentlistCopy.add(studentlist);
        myAdapter.setAdapterData(studentlistCopy);
        labelVisibility();

    }

    private void labelVisibility() {
        if (studentlistCopy.size() == 0) {
            recyclerView.setVisibility(View.INVISIBLE);
            tvNoRecord.setVisibility(View.VISIBLE);
            myAdapter.setAdapterData(studentlistCopy);
        } else {
            tvNoRecord.setVisibility(View.INVISIBLE);
            recyclerView.setVisibility(View.VISIBLE);
            myAdapter.setAdapterData(studentlistCopy);
        }
        myAdapter.setAdapterData(studentlistCopy);
    }
    //list grid method

    public void toggleView(final boolean isSquares) {
        if (isSquares) {
            final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(linearLayoutManager);
        } else {
            final GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

    }

    //sorting data as name
    public void sortDetailsName(final boolean mIsSortData) {
        if (mIsSortData) {
            Collections.sort(studentlistCopy, new Comparator<StudentDetail>() {
                @Override
                public int compare(StudentDetail studentDetails3, StudentDetail studentDetails4) {
                    String name1 = String.valueOf(studentDetails3.getStudentName());
                    String name2 = String.valueOf(studentDetails4.getStudentName());
                    return name1.compareToIgnoreCase(name2);
                }
            });
        }
        myAdapter.setAdapterData(studentlistCopy);
    }

    //updated data
    public void updateStudentDetail(final int clickedPosition, StudentDetail studentObject) {
        if (studentObject.getType() == 1) {
            studentlistCopy.add(studentObject);
        } else if (studentObject.getType() == 2) {
            studentlistCopy.set(clickedPosition, studentObject);
        }
        myAdapter.setAdapterData(studentlistCopy);
    }

    //sorting data as roll no
    public void sortDetailsRoll(final boolean mIsSortData) {
        if (mIsSortData) {
            Collections.sort(studentlistCopy, new Comparator<StudentDetail>() {
                @Override
                public int compare(StudentDetail studentDetails3, StudentDetail studentDetails4) {
                    String roll1 = String.valueOf(studentDetails3.getRollNo());
                    String roll2 = String.valueOf(studentDetails4.getRollNo());
                    return roll1.compareToIgnoreCase(roll2);
                }
            });
        }
        myAdapter.setAdapterData(studentlistCopy);
    }

    //dialog box
    @Override
    public void onItemClicked(final int position) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        Button btnView = dialog.findViewById(R.id.btn_view);
        Button btnUpdate = dialog.findViewById(R.id.btn_edit);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StudentDetail studentDetail=studentlistCopy.get(position);
                studentDetail.setType(3);
                new AsyncTaskStudentList(context,StudentListFragment.this).execute(studentDetail);
                studentlistCopy.remove(position);
                myAdapter.setAdapterData(studentlistCopy);
                dialog.dismiss();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                ((HomeActivity) context).dialogClick(position, studentlistCopy.get(position), updateClicked);
                dialog.dismiss();

            }
        });
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ViewActivity.class);
                intent.putExtra(INTENT_KEY, studentlistCopy.get(position));
                startActivity(intent);
                dialog.dismiss();
            }
        });
        dialog.show();
    }


    @Override
    public void asyncClickListner(Boolean aBoolean, StudentDetail studentDetail) {

    }
}