package com.example.lenovo.ac_instagramclone;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.parse.ParseException;

import com.parse.ParseUser;

import com.parse.SignUpCallback;

public class MainActivity extends AppCompatActivity {

    private EditText edtMail;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnSignUp;
    private Button btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtMail= findViewById(R.id.edtMail);
        edtUsername= findViewById(R.id.edtUsername);
        edtPassword= findViewById(R.id.edtPassword);
        btnSignUp= findViewById(R.id.btnSignUp);
        btnLogin= findViewById(R.id.btnLogin);

        ActionBar actionbar = getSupportActionBar();

// Applies the custom action bar style
        actionbar.setTitle("Sign Up");
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));

        if(ParseUser.getCurrentUser() !=null){
           // ParseUser.getCurrentUser().logOut();
            transactionToSocialMediaActivity();

        }



        btnSignUp.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {

                if(edtMail.getText().toString().equals("") ||
                        edtUsername.getText().toString().equals("") ||
                        edtPassword.getText().toString().equals("")){

                    Toast.makeText(MainActivity.this,
                            "Email,Username and Password is required!",
                            Toast.LENGTH_LONG).show();

                } else{


                final ParseUser appUser = new ParseUser();
                appUser.setUsername(edtUsername.getText().toString());
                appUser.setPassword(edtPassword.getText().toString());
                appUser.setEmail(edtMail.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("Signing up" + edtUsername.getText().toString());
                progressDialog.show();
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e==null){
                            Toast.makeText(MainActivity.this,
                                    appUser.get("username") + " is signed up successfully",
                                    Toast.LENGTH_LONG).show();
                            transactionToSocialMediaActivity();


                        } else{
                            Toast.makeText(MainActivity.this, e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
            }
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void transactionToSocialMediaActivity(){

        Intent intent = new Intent(MainActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }
}
