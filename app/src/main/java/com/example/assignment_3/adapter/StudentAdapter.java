package com.example.assignment_3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_3.R;
import com.example.assignment_3.model.StudentDetail;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {

    private ArrayList<StudentDetail>student;
    ItemClicked activity;

    public StudentAdapter(Context context, ArrayList<StudentDetail>list)
    {
        student = list;
        activity=(ItemClicked)context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvStudentName,tvClass,tvRoll;
        private LinearLayout llMain;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvStudentName = itemView.findViewById(R.id.tv_student);
            tvClass = itemView.findViewById(R.id.tv_class);
            tvRoll = itemView.findViewById(R.id.tv_Roll_no);
            llMain = itemView.findViewById(R.id.llMain);

        }

    }
    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_details,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdapter.ViewHolder holder, int position) {

        holder.itemView.setTag(student.get(position));
        holder.tvStudentName.setText(String.format("Student Name: %s", student.get(position).getStudentName()));
        holder.tvClass.setText(String.format("Student Class: %s", String.valueOf(student.get(position).getClassName())));
        holder.tvRoll.setText(String.format("Roll No: %s", String.valueOf(student.get(position).getRollNo())));
        holder.llMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.onItemClicked(student.indexOf((StudentDetail)view.getTag()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return student.size();
    }
    public interface ItemClicked{
        void onItemClicked(int position);
    }

}
