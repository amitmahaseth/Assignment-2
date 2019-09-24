package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import static com.example.assignment2.LoginActivity.userList;

public class RegistrationActivity extends AppCompatActivity {
        ImageButton back;
        ImageView userimage;
        EditText edtname,edtEmail,edtpassword,edtgender,edtdob;
        Button btnContinue;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

            back = findViewById(R.id.back);
            userimage = findViewById(R.id.userimage);
            edtname = findViewById(R.id.edtname);
            edtEmail = findViewById(R.id.edtEmail);
            edtpassword = findViewById(R.id.edtpassword);
            edtgender = findViewById(R.id.edtgender);
            edtdob = findViewById(R.id.edtdob);
            btnContinue = findViewById(R.id.btnContinue);


            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(RegistrationActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            });

            btnContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String name =edtname.getText().toString();
                    String email =edtEmail.getText().toString();
                    String password = edtpassword.getText().toString();
                    String dob = edtdob.getText().toString();
                    String gender = edtgender.getText().toString().trim().toLowerCase();


                    /**
                     *  String validation using there pattern
                     *
                     */


                    String namePattern =("^[a-zA-Z\\s]*$");

                    String emailpattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                    String passwordpattern ="^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";



                    if (email.isEmpty()) {
                        Snackbar.make(view,"Plese enter email",Snackbar.LENGTH_SHORT)
                                .setAction("Action",null).show();
                    } else if (!email.matches(emailpattern)) {
                        Snackbar.make(view,"Enter valid email",Snackbar.LENGTH_SHORT)
                                .setAction("Action",null).show();

                    } else if (password.isEmpty()) {
                        Snackbar.make(view,"Plese enter password",Snackbar.LENGTH_SHORT)
                                .setAction("Action",null).show();

                    } else if (!password.matches(passwordpattern)) {
                        Snackbar.make(view,"Enter valid Password",Snackbar.LENGTH_SHORT)
                                .setAction("Action",null).show();
                    } else if (dob.isEmpty()) {
                        Snackbar.make(view, "Plese enter date", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }else if (name.isEmpty()){
                        Snackbar.make(view,"Please enter name",Snackbar.LENGTH_SHORT)
                                .setAction("Action",null).show();
                    }else if (!name.matches(namePattern)) {
                        Snackbar.make(view, "Please enter Valid Name", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }else if (gender.isEmpty()){
                        Snackbar.make(view,"Please enter Gender",Snackbar.LENGTH_SHORT)
                                .setAction("Action",null).show();
                    }else if (!gender.equals("male")&& (!gender.equals("female"))) {
                        Snackbar.make(view, "Please enter Valid Gender", Snackbar.LENGTH_SHORT)
                                .setAction("Action", null).show();
                    }
                    else {
                        userList.add(new User(email,password));
                        Intent intent1 = new Intent(RegistrationActivity.this, OtpActivity.class);
                        startActivity(intent1);

                    }

                }
            });

        }
}
