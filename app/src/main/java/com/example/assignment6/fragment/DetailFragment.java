package com.example.assignment6.fragment;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.assignment6.R;
import com.example.assignment6.adapter.UserAdapter;
import com.example.assignment6.adapter.UserPostAdapter;
import com.example.assignment6.model.Users;
import com.example.assignment6.network.ApiClient;
import com.example.assignment6.network.ApiInterface;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {
    private TextView tvUname, tvUserName;
    private Button btnShow;
    private ImageButton btnDismiss;
    private Context context;
    private RecyclerView recyclerView;
    private ArrayList<Users> postUsersArrayList = new ArrayList<>();


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);


        tvUname = view.findViewById(R.id.tv_name_detail);
        tvUserName = view.findViewById(R.id.tv_user_name_detail);

        btnShow = view.findViewById(R.id.btn_show);

        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                showUser();

            }
        });

        return view;
    }

    private void showUser() {
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<ArrayList<Users>> call = apiInterface.getUserPosts();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                postUsersArrayList = response.body();
                recyclerView.setAdapter(new UserPostAdapter(postUsersArrayList, context));

            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {

            }
        });
    }

    public void showDialog() {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        btnDismiss = dialog.findViewById(R.id.ib_dismiss);


        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        recyclerView = dialog.findViewById(R.id.list_item_post);


        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new UserPostAdapter(postUsersArrayList, context));

        dialog.show();


        btnDismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });


    }


    public void displayData(Users users) {
        tvUname.setText("Name: " + users.getuName());
        tvUserName.setText("User Name: " + users.getUserName());
    }


}
