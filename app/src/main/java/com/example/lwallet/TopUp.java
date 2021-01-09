package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class TopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        Intent intent = getIntent();
        Log.d("Topup Username", intent.getStringExtra("Username"));
        ImageButton buttonHundred = (ImageButton) findViewById(R.id.buttonhundred);
        buttonHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                proceedtoConfirmation(100, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"));
            }
        });

        ImageButton buttontwoHundred = (ImageButton) findViewById(R.id.buttonTwoHundred);
        buttontwoHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtoConfirmation(200, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"));
            }
        });

        ImageButton buttonthreeHundred = (ImageButton) findViewById(R.id.buttonThreehundred);
        buttonthreeHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtoConfirmation(300, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"));
            }
        });

        ImageButton buttonfourHundred = (ImageButton) findViewById(R.id.buttonFourhundred);
        buttonfourHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtoConfirmation(400, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"));
            }
        });

        ImageButton buttonfiveHundred = (ImageButton) findViewById(R.id.buttonFiveHundred);
        buttonfiveHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtoConfirmation(500, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"));
            }
        });

        ImageButton buttonSixHundred = (ImageButton) findViewById(R.id.buttonSixHundred);
        buttonSixHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtoConfirmation(600, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"));
            }
        });
    }




    private void proceedtoConfirmation(double topupValue, String username, int pin, String voice) {
        Intent intent = new Intent(this, PinConfirmation.class);
        Bundle b = new Bundle();
        b.putString("Status", "TopUp");
        b.putString("Username", username);
        b.putDouble("TopUp_Value", topupValue);
        b.putString("Voice", voice);
        b.putInt("Pin", pin);
        intent.putExtras(b);
        startActivity(intent);
    }
}