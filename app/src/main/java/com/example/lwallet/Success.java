package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        ImageButton buttonHome = (ImageButton) findViewById(R.id.buttonHomeSuccess);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                backToHome(intent.getStringExtra("Username"));
            }
        });
    }

    private void backToHome(String username) {
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("username", username);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}