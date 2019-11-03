package com.example.assignment3update.activity.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.assignment3update.R;
import com.example.assignment3update.activity.adapter.StudentAdapter;
import com.example.assignment3update.activity.adapter.ViewPagerAdapter;
import com.example.assignment3update.activity.fragment.AddUpdateStudentFragment;
import com.example.assignment3update.activity.fragment.StudentListFragment;
import com.example.assignment3update.activity.model.StudentDetail;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HomeActivity extends AppCompatActivity{
    private StudentListFragment studentListFragment;
    private AddUpdateStudentFragment addUpdateStudentFragment;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageButton ivsort,ivSquares;
    private boolean isSquares= true;
    public  boolean isSortData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        ivSquares = findViewById(R.id.iv_squares);
        ivsort = findViewById(R.id.iv_sort);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);
        setupViewPager();
        tabLayout.setupWithViewPager(viewPager);

        ivSquares.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                if (isSquares) {
                    isSquares = false;
                    ivSquares.setBackground(getResources().getDrawable(R.drawable.list));

                } else {
                    ivSquares.setBackground(getResources().getDrawable(R.drawable.squares));
                    isSquares = true;
                }
                studentListFragment.toggleView(isSquares);
            }
        });

        ivsort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(HomeActivity.this, ivsort);
                popupMenu.getMenuInflater().inflate(R.menu.pop_up, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.sort_name:
                                isSortData= true;
                                studentListFragment.sortDetailsName(isSortData);
                                return true;
                            case R.id.sort_roll_no:
                                isSortData = true;
                                studentListFragment.sortDetailsRoll(isSortData);
                                return true;
                            default:
                                isSortData=false;
                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }

    private void setupViewPager() {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        studentListFragment = new StudentListFragment();
        addUpdateStudentFragment = new AddUpdateStudentFragment(true);

        adapter.sam(studentListFragment,getString(R.string.student_list));
        adapter.sam(addUpdateStudentFragment, getString(R.string.add_update_student));
        viewPager.setAdapter(adapter);
    }
//click listener on add student button on fragment

    public void switchPager(){
        if (viewPager.getCurrentItem()==0){
            viewPager.setCurrentItem(1,true);
        }
        else {
            viewPager.setCurrentItem(0,true);
        }
    }

    //getting data from fragment send to student list
    public void addStudentData(StudentDetail studentDetail) {
        if(studentListFragment instanceof StudentListFragment){
            studentListFragment.setData(studentDetail);
        }


    }

    //dialog on click opens fragment
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void dialogClick(final int clickedPosition, StudentDetail studentObj, boolean mUpdateClicked){
        viewPager.setCurrentItem(1,true);
        addUpdateStudentFragment.updateStudentDetail(clickedPosition,studentObj,mUpdateClicked);

    }

    public void onDataUpdated(final int clickedPosition,StudentDetail studentObj){
        studentListFragment.updateStudentDetail(clickedPosition,studentObj);
    }

}

