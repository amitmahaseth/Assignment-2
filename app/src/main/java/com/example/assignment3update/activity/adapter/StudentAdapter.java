package com.example.assignment3update.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3update.R;
import com.example.assignment3update.activity.model.StudentDetail;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.ViewHolder> {
    private ArrayList<StudentDetail> studentList;
    private ItemClicked clickActivity;

    public StudentAdapter(ItemClicked clickActivity) {
        this.clickActivity = clickActivity;

    }

    public void setAdapterData(ArrayList<StudentDetail> student) {
        this.studentList = student;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View studentDetails = layoutInflater.inflate(R.layout.list_details, parent, false);
        StudentAdapter.ViewHolder viewHolder = new StudentAdapter.ViewHolder(studentDetails);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        StudentDetail studentDetail = studentList.get(position);
        holder.itemView.setTag(studentList.get(position));
        holder.tvDisplayName.setText(studentDetail.getStudentName());
        holder.tvDisplayClass.setText(String.valueOf(studentDetail.getClassName()));
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickActivity.onItemClicked(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return studentList == null ? 0 : studentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvDisplayName, tvDisplayClass;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDisplayName = itemView.findViewById(R.id.tv_student);
            tvDisplayClass = itemView.findViewById(R.id.tv_class);
            linearLayout = itemView.findViewById(R.id.llMain);

        }
    }

    public interface ItemClicked {
        void onItemClicked(int position);
    }
}