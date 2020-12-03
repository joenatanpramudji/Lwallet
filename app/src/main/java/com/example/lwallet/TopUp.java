package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class TopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        ImageButton buttonHundred = (ImageButton) findViewById(R.id.buttonhundred);
        buttonHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                proceedtoConfirmation();
            }
        });
    }

    private void proceedtoConfirmation() {
        Intent intent = new Intent(this, PinConfirmation.class);
        startActivity(intent);
    }
}