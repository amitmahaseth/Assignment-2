package com.example.assignment6.adapter;

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

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private ArrayList<Users> usersArrayList;
    private ItemClicked clickActivity;

    public UserAdapter(ArrayList<Users> usersArrayList, ItemClicked clickActivity) {
        this.usersArrayList = usersArrayList;
        this.clickActivity = clickActivity;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View user = layoutInflater.inflate(R.layout.list, parent, false);
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(user);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        Users users = usersArrayList.get(position);
        holder.itemView.setTag(usersArrayList.get(position));
        holder.tvId.setText(users.getId());
        holder.tvName.setText(users.getuName());
        holder.tvUserName.setText(users.getUserName());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickActivity.onItemClicked(holder.getAdapterPosition());
            }
        });
    }


    @Override
    public int getItemCount() {
        return usersArrayList == null ? 0 : usersArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName,tvUserName;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvUserName=itemView.findViewById(R.id.tv_user_name);
            linearLayout = itemView.findViewById(R.id.llMain);

        }
    }

    public interface ItemClicked {
        void onItemClicked(int position);
    }
}
