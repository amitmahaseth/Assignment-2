package com.example.assignment6.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment6.R;
import com.example.assignment6.activity.MainActivity;
import com.example.assignment6.adapter.UserAdapter;
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
public class ListFragment extends Fragment implements UserAdapter.ItemClicked {

    private Context context;
    private Button btnRetry;
    private TextView tvNoInternet;
    private RecyclerView recyclerView;
    private ArrayList<Users>usersArrayList=new ArrayList<>();



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    public ListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       final View view= inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView=view.findViewById(R.id.list_item);
        tvNoInternet=view.findViewById(R.id.tv_no_internet);
        btnRetry=view.findViewById(R.id.btn_retry);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(context);
        recyclerView.setLayoutManager(layoutManager);
        getUsers();

        return view;
    }

    public void getUsers() {
        //retrofit instance of interface
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        //Call the method with parameter in the interface to get data
        Call<ArrayList<Users>> call = apiInterface.getUserList();
        call.enqueue(new Callback<ArrayList<Users>>() {
            @Override
            public void onResponse(Call<ArrayList<Users>> call, Response<ArrayList<Users>> response) {
                tvNoInternet.setVisibility(View.INVISIBLE);
                btnRetry.setVisibility(View.INVISIBLE);
                usersArrayList = response.body();
                recyclerView.setAdapter(new UserAdapter(usersArrayList, ListFragment.this));
                for (Users users : usersArrayList) {
                    Log.d("afd", "onResponse: id" + users.getId());
                    Log.d("afd", "onResponse: name" + users.getuName());
                    Log.d("afd", "onResponse: username" + users.getUserName());
                }

            }

            @Override
            public void onFailure(Call<ArrayList<Users>> call, Throwable t) {
                tvNoInternet.setVisibility(View.VISIBLE);
                btnRetry.setVisibility(View.VISIBLE);
                btnRetry.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getUsers();
                    }
                });
                Toast.makeText(context, "Something went Wrong", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onItemClicked(int position) {
    Users users =usersArrayList.get(position);
        ((MainActivity)context).dataSend(users);
    }
}
