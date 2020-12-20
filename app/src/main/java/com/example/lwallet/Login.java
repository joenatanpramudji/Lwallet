package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;


import com.google.android.material.snackbar.Snackbar;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ImageButton loginButton = (ImageButton) findViewById(R.id.buttonRg);
        EditText inputUsername = (EditText) findViewById(R.id.usernameInputText);
        EditText inputPassword = (EditText) findViewById(R.id.passwordInputtext);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(inputUsername.getText().toString().equals("") || inputPassword.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please enter your username or password!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                   startMainMenuActivity();
                }
            }
        });

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

    private void startMainMenuActivity() {
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
}