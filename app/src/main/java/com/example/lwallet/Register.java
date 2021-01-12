package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Register extends AppCompatActivity {
    private final int REQ_CODE = 100;
    String registerVoice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ImageButton buttonSignIn = (ImageButton) findViewById(R.id.btnSignIn);
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignInActivity();
            }
        });

        EditText username = (EditText) findViewById(R.id.registerUsernameText);
        EditText email = (EditText) findViewById(R.id.registerEmailInputText);
        EditText password = (EditText) findViewById(R.id.registerpasswordInputText);

        Connection cn = new Connection();

        ImageButton btnRg = (ImageButton) findViewById(R.id.buttonRg);
        btnRg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please register your voice!");
                if(username.getText().toString().equals("") || email.getText().toString().equals("") || password.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please complete the form!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    try {
                        startActivityForResult(intent, REQ_CODE);
                        Snackbar alert = Snackbar.make(v, "Registered!", Snackbar.LENGTH_LONG);
                        alert.show();


                        cn.register(username.getText().toString(), email.getText().toString(), password.getText().toString(), registerVoice);
                        password.setText("");
                        email.setText("");
                    }catch (ActivityNotFoundException a)
                    {
                        Toast.makeText(getApplicationContext(),
                                "Sorry your device not supported",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void startSignInActivity() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText username = (EditText) findViewById(R.id.registerUsernameText);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    registerVoice = result.get(0).toString();
                    new Connection().register(username.getText().toString(), registerVoice);
                    username.setText("");
                }
                break;
            }
        }
    }
}