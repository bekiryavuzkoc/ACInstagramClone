package com.example.lenovo.ac_instagramclone;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginEmail;
    private EditText edtLoginPassword;
    private Button btnLoginButton;
    private Button btnLoginSignUp;



    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtLoginEmail=findViewById(R.id.edtLoginEmail);
        edtLoginPassword=findViewById(R.id.edtLoginPassword);
        btnLoginButton=findViewById(R.id.btnLoginButton);
        btnLoginSignUp=findViewById(R.id.btnLoginSignUp);

        if(ParseUser.getCurrentUser() !=null){
            ParseUser.getCurrentUser().logOut();
        }

        ActionBar actionbar = getSupportActionBar();

// Applies the custom action bar style
        actionbar.setTitle("Log In");
        actionbar.setBackgroundDrawable(new ColorDrawable(Color.BLUE));

        btnLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edtLoginEmail.getText().toString().equals("") ||
                        edtLoginPassword.getText().toString().equals("")){

                    Toast.makeText(LoginActivity.this,
                            "Email,Username and Password is required!",
                            Toast.LENGTH_LONG).show();

                } else{

                try {
                    ParseUser.logInInBackground(edtLoginEmail.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {

                            if (user != null && e == null) {
                                Toast.makeText(LoginActivity.this,
                                        "User officially logged in!", Toast.LENGTH_LONG).show();
                                transactionToSocialMediaActivity();
                            } else {
                                Toast.makeText(LoginActivity.this,
                                        e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }catch (Exception e){
                    Toast.makeText(LoginActivity.this,
                            e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
            }
        });

        btnLoginSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void loginLayoutTapped(View view){

        try{
            InputMethodManager inputMethodManager1 =
                    (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager1.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private void transactionToSocialMediaActivity(){

        Intent intent = new Intent(LoginActivity.this,SocialMediaActivity.class);
        startActivity(intent);
    }

}
