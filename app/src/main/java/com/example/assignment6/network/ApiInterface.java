package com.example.assignment6.network;

import com.example.assignment6.model.Users;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
    @GET("users")
    Call<ArrayList<Users>>getUserList();

    @GET("posts?userId=1")
    Call<ArrayList<Users>>getUserPosts();

}
