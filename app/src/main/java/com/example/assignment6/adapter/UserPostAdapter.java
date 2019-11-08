package com.example.assignment6.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment6.R;
import com.example.assignment6.model.Users;

import java.util.ArrayList;

public class UserPostAdapter extends RecyclerView.Adapter<UserPostAdapter.ViewHolder> {
    private ArrayList<Users> userPostArrayList;
    private Context context;

    public UserPostAdapter(ArrayList<Users> userPostArrayList, Context context) {
        this.userPostArrayList = userPostArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public UserPostAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View user = layoutInflater.inflate(R.layout.list_post, parent, false);
        UserPostAdapter.ViewHolder viewHolder = new UserPostAdapter.ViewHolder(user);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        holder.tvUserId.setText(userPostArrayList.get(position).getUserId());
        holder.tvTitle.setText(userPostArrayList.get(position).getTitle());




    }

    @Override
    public int getItemCount() {
        return userPostArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUserId,tvTitle;
        LinearLayout linearLayout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUserId=itemView.findViewById(R.id.tv_user_id);
            tvTitle=itemView.findViewById(R.id.tv_title);
            linearLayout=itemView.findViewById(R.id.llMain_post);
        }
    }
}
