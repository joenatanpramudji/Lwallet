package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.nfc.Tag;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;


import com.google.android.material.snackbar.Snackbar;


import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class Login extends AppCompatActivity {
    public static final String PREFS_NAME = "LoginProfile";
    public static final String PREF_USERNAME = "username";
    public static final String PREF_PASSWORD = "password";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String username = pref.getString(PREF_USERNAME, null);
        String password = pref.getString(PREF_PASSWORD, null);

        if(username == null || password == null)
        {

            Connection cn = new Connection();
            cn.loginData();

//        ArrayList<String> test = new ArrayList<String>();
//        test.add("FUCK");
//        test.add("YOU");
//        test.add("JOE");

            ImageButton loginButton = (ImageButton) findViewById(R.id.buttonRg);
            EditText inputUsername = (EditText) findViewById(R.id.usernameInputText);
            EditText inputPassword = (EditText) findViewById(R.id.passwordInputtext);
            RadioButton rememberButton = (RadioButton) findViewById(R.id.rememberButton);
            //String correctPassword = cn.readData();
            //Log.d(TAG, "This is the correct password" + cn.correctPassword);
            loginButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Input password is " + inputPassword.getText().toString());


                    for (int i = 0; i < cn.usernameReference.size();i++)
                    {
                        if (inputUsername.getText().toString().equals("") || inputPassword.getText().toString().equals(""))
                        {
                            Snackbar alert = Snackbar.make(v, "Please enter your username or password!", Snackbar.LENGTH_LONG);
                            alert.show();
                        } else if (inputUsername.getText().toString().equals(cn.usernameReference.get(i)) && inputPassword.getText().toString().equals(cn.passwordReference.get(i))) {
                            if(rememberButton.isChecked())
                            {
                                String correctUsername = inputUsername.getText().toString();
                                getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit().putString(PREF_USERNAME, correctUsername).putString(PREF_PASSWORD, inputPassword.getText().toString()).commit();
                                startMainMenuActivity(correctUsername);
                            }
                            else {
                                String correctUsername = inputUsername.getText().toString();
                                startMainMenuActivity(correctUsername);
                            }
                            //cn.username = inputUsername.getText().toString();

                        }
                        else {
                            Snackbar alert = Snackbar.make(v, "Wrong username or password!", Snackbar.LENGTH_LONG);
                            alert.show();
                        }
                    }
                }
            });


        }
        else
        {
            startMainMenuActivity(username);
        }

        ImageButton signupButton = (ImageButton) findViewById(R.id.btnSignIn);
        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }
        });
    }

    private void openRegister() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    private void startMainMenuActivity(String username) {
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("username", username);
        startActivity(intent);
        finish();
    }
}