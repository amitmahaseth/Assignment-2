package com.example.assignment2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class OtpActivity extends AppCompatActivity {

    ImageButton back;
    EditText edt1,edt2,edt3,edt4;
    Button resend,submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        back = findViewById(R.id.back);
        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);
        edt4 = findViewById(R.id.edt4);
        resend = findViewById(R.id.resend);
        submit = findViewById(R.id.submit);

        /**
         * submit button for conform registration
         *
         */


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Toast.makeText(OtpActivity.this,"Successful Register",Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(OtpActivity.this,LoginActivity.class);
                startActivity(intent);

         }
        });

        /**
         * resend button is used to send otp again and its working off there function
         * from (edt1)
         *
         *
         * getText.clear is used to clear there enterd numbered through there fuctionality
         *
         */

        resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edt1.getText().clear();
                edt2.getText().clear();
                edt3.getText().clear();
                edt4.getText().clear();

                Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), "OTP has been send", Snackbar.LENGTH_LONG);
                snackbar.show();
            }
        });

        //back onclick listener defines there to go on login activity



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent intent = new Intent(OtpActivity.this,LoginActivity.class);
            startActivity(intent);
            }
        });


        /**
         * edittext (edt) is used to show there sequence of enter there no for one to an
         * another edittext
         *
         * its working to type one number for 1 (edt)
         *
         *
         */


        edt1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edt2.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edt3.requestFocus();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            edt4.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        edt4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()==1)
                {
                    edt4.requestFocus();
                }
                else if(s.length()==0)
                {
                    edt1.requestFocus();
                }
            }
        });


    }
}
