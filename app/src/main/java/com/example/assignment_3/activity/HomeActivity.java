package com.example.assignment_3.activity;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment_3.R;
import com.example.assignment_3.adapter.StudentAdapter;
import com.example.assignment_3.model.StudentDetail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity implements StudentAdapter.ItemClicked{
    int a;
    TextView tvStudent;
    ImageButton ivsort,ivSquares;
    RecyclerView recyclerView;
    StudentAdapter myAdapter;
    RecyclerView.LayoutManager layoutManager;
    int isList=2;



    Button addStudent;

    ArrayList<StudentDetail> student=new ArrayList<StudentDetail>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);



        recyclerView = findViewById(R.id.list);
        recyclerView.setHasFixedSize(true);

        addStudent = findViewById(R.id.btn_add_student);
        ivSquares = findViewById(R.id.iv_squares);
        ivsort = findViewById(R.id.iv_sort);
        tvStudent = findViewById(R.id.tv_display_student);


        myAdapter = new StudentAdapter(this, student);


        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);
        ivSquares.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if(isList==2){
                    ivSquares.setBackground(getResources().getDrawable(R.drawable.list));
                    layoutManager=new LinearLayoutManager(HomeActivity.this);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter.notifyDataSetChanged();
                    isList=1;
                }
                else{
                    ivSquares.setBackground(getResources().getDrawable(R.drawable.squares));
                    layoutManager=new StaggeredGridLayoutManager(2, LinearLayout.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    myAdapter.notifyDataSetChanged();
                    isList=2;
                }
            }
        });
        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent send = new Intent(HomeActivity.this, AddStudentActivity.class);
                startActivityForResult(send,2);

            }
        });

//        pop up og list view as name or roll no to arrange through then

        ivsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, ivsort);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.sort_name:
                                Collections.sort(student, new Comparator<StudentDetail>() {
                                    @Override
                                    public int compare(StudentDetail o1, StudentDetail o2) {
                                        return o1.getStudentName().compareToIgnoreCase(o2.getStudentName());
                                    }
                                });
                                myAdapter.notifyDataSetChanged();
                                return true;
                            case R.id.sort_roll_no:
                                Collections.sort(student, new Comparator<StudentDetail>() {
                                    @Override
                                    public int compare(StudentDetail o1, StudentDetail o2) {
                                        String rollNo = String.valueOf(o1.getRollNo());
                                        String rollNo2 = String.valueOf(o2.getRollNo());
                                        return rollNo.compareToIgnoreCase(rollNo2);
                                    }
                                });
                                myAdapter.notifyDataSetChanged();
                                return true;
                            default:
                                return false;


                        }
                    }
                });
                popupMenu.show();
            }
        });
    }

    public void update(){
        if(student.size()==0){
            recyclerView.setVisibility(View.INVISIBLE);
            tvStudent.setVisibility(View.VISIBLE);
        }
        else{
            recyclerView.setVisibility(View.VISIBLE);
            tvStudent.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==2){
            if(resultCode==RESULT_OK){
                StudentDetail st=data.getParcelableExtra("Object");
                student.add(st);
                update();
            }
        }
    }

//custom dialog button on view,update,delete
@Override
    public void onItemClicked(final int position){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        Button btnView = dialog.findViewById(R.id.btn_view);
        Button btnEdit = dialog.findViewById(R.id.btn_edit);
        Button btnDelete = dialog.findViewById(R.id.btn_delete);

    final StudentDetail st=student.get(position);
    btnView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a=2;
            Intent i=new Intent(HomeActivity.this,AddStudentActivity.class);
            i.putExtra("Code",a);
            i.putExtra("Object",st);
            startActivity(i);
            dialog.dismiss();
        }
    });
    btnEdit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            a=3;
            Intent i=new Intent(HomeActivity.this,AddStudentActivity.class);
            i.putExtra("Code",a);
            i.putExtra("Object",st);
            student.remove(position);
            startActivity(i);
            dialog.dismiss();
        }
    });
    btnDelete.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            student.remove(position);
            myAdapter.notifyDataSetChanged();
            dialog.dismiss();
        }
    });
        dialog.show();

}
}