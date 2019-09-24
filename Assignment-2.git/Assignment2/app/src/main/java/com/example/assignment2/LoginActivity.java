package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    EditText editTextEmail, editTextPassword;
    TextView tvRegister;
    Button login;
    public static List<User> userList = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        tvRegister = findViewById(R.id.tvRegister);
        login = findViewById(R.id.login);

        String textRegister = getResources().getString(R.string.label_don_t_have_an_account_register);


//Intializing a clickabel and spannable span


        SpannableString ss = new SpannableString(textRegister);
        ClickableSpan cs = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View view) {
                Intent intent = new Intent(LoginActivity.this, com.example.assignment2.RegistrationActivity.class);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
            }
        };
        ForegroundColorSpan fcsBlue = new ForegroundColorSpan(getResources().getColor((android.R.color.holo_blue_dark)));
        ss.setSpan(cs, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(fcsBlue, 23, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        tvRegister.setText(ss);
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());

        userList.add(new User("amit@gmail.com", "amit"));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 *
                 * for login validation
                 *
                 */
                String username = editTextEmail.getText().toString().trim();
                String userpassword = editTextPassword.getText().toString().trim();
                isValidate();
                for (int i = 0; i < userList.size(); i++) {
                    String un = userList.get(i).getUsername();
                    String pass = userList.get(i).getUserpassword();
                    if ((username.equals(un) && (userpassword.equals(pass)))) {
                        Intent intent = new Intent(LoginActivity.this, WelcomeActivity.class);
                        startActivity(intent);
                    }
                    if ((!username.equals(un) || (!userpassword.equals(pass)))) {
                        Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "Invalid EmailId", Snackbar.LENGTH_LONG);
                        snackbar.show();
                    }


                }
            }
        });

    }

    /**
     *
     * boolean is used to there empty email or passsword
     *
     */
    private boolean isValidate() {
        if (editTextEmail.getText().toString().trim().isEmpty()) {
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "Enter EmailId", Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;

        }
        if (editTextPassword.getText().toString().trim().isEmpty()) {
            Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "Enter Password", Snackbar.LENGTH_LONG);
            snackbar.show();
            return false;
        }
        return true;
    }


}
