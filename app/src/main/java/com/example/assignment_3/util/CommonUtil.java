package com.example.assignment_3.util;

import android.content.Context;
import android.widget.Toast;

public class CommonUtil {
    public static void showSnackBar(Context context, String msg){
        Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
    }
}
