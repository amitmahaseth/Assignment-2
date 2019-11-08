package com.example.assignment6.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.assignment6.R;
import com.example.assignment6.fragment.DetailFragment;
import com.example.assignment6.fragment.ListFragment;
import com.example.assignment6.model.Users;

public class MainActivity extends AppCompatActivity {

    private ListFragment listFragment;
    private DetailFragment detailFragment;
    private LinearLayout linearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout=findViewById(R.id.linearout);

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        listFragment = new ListFragment();
        detailFragment = new DetailFragment();
        transaction.add(R.id.fragment, listFragment);
        transaction.add(R.id.fragment2, detailFragment);
        transaction.commit();

    }

    public void dataSend(Users users) {
        if(detailFragment instanceof DetailFragment){
            detailFragment.displayData(users);
        }
    }



    @Override
    public void onConfigurationChanged(@NonNull Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

//        if(newConfig.orientation==Configuration.ORIENTATION_PORTRAIT){
//            setContentView(R.layout.activity_main);
//        }else{
//            setContentView(R.layout.activity_main_land);
//        }
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE){
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        }
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT){
            linearLayout.setOrientation(LinearLayout.VERTICAL);
        }

    }
}
