package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.Image;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import static android.content.ContentValues.TAG;

public class MainMenu extends AppCompatActivity {
   // private Toolbar maintoolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();

        Connection cn = new Connection();
        TextView displayCash = (TextView) findViewById(R.id.displayCash);

        TextView displayPoints = (TextView) findViewById(R.id.displayPoints);

        cn.readData(intent.getStringExtra("username"), new Connection.ReadCallback() {
            @Override
            public void onCallback(double valueCash, double valueDouble) {
                displayCash.setText("RM " + Integer.toString((int)valueCash));
                displayPoints.setText(Integer.toString((int)valueDouble) + " pts");
            }
        });


        Log.d("Correct Username", "" + intent.getStringExtra("username"));
        Log.d(TAG, "Main Cash is : " + cn.cash);
        Log.d(TAG, "Main Points is : " + cn.points);

        //maintoolbar = findViewById(R.id.mainToolbar);
        //setSupportActionBar(maintoolbar);
        ImageButton buttonTopup = (ImageButton) findViewById(R.id.buttonTopup);
        buttonTopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTopupActivity(intent.getStringExtra("username"), cn.pin, cn.voice);
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

    private void openTopupActivity(String username, int pin, String voice) {
        Bundle bundle = new Bundle();
        bundle.putString("Username", username);
        bundle.putInt("Pin", pin);
        bundle.putString("Voice", voice);
        Intent intent = new Intent(this, TopUp.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}