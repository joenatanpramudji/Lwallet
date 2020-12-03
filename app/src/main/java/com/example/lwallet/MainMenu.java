package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {
   // private Toolbar maintoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        //maintoolbar = findViewById(R.id.mainToolbar);
        //setSupportActionBar(maintoolbar);
        ImageButton buttonTopup = (ImageButton) findViewById(R.id.buttonTopup);
        buttonTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopupActivity();
            }
        });

        ImageButton btnTransfer = (ImageButton) findViewById(R.id.buttonTransfer);
        btnTransfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDestination();
            }
        });

        ImageButton btnHistory = (ImageButton) findViewById(R.id.buttonHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });

        ImageButton btnProfile = (ImageButton) findViewById(R.id.buttonProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });
    }

    private void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    private void openHistory() {
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }

    private void openDestination() {
        Intent intent = new Intent(this, Destination.class);
        startActivity(intent);
    }

    private void openTopupActivity() {
        Intent intent = new Intent(this, TopUp.class);
        startActivity(intent);
    }
}