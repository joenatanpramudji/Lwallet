package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Destination extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        TextView destination = (TextView) findViewById(R.id.destinationCode);

        Bundle intent = getIntent().getExtras();


        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonD);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success(destination.getText().toString(), intent.get("Username").toString(), Integer.parseInt(intent.get("Pin").toString()), intent.get("Voice").toString());
            }
        });

        ImageButton backButton = (ImageButton) findViewById(R.id.backButtonD);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ImageButton numberButtons[] = new ImageButton[10];
        numberButtons[0] = (ImageButton) findViewById(R.id.button0_d);
        numberButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(destination.getText() != "0")
                {
                    destination.setText(destination.getText() + "0");
                }
            }
        });
        numberButtons[1] = (ImageButton) findViewById(R.id.button1_d);
        numberButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "1");

            }
        });
        numberButtons[2] = (ImageButton) findViewById(R.id.button2_d);
        numberButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "2");

            }
        });
        numberButtons[3] = (ImageButton) findViewById(R.id.button3_d);
        numberButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "3");
            }
        });
        numberButtons[4] = (ImageButton) findViewById(R.id.button4_d);
        numberButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "4");
            }
        });
        numberButtons[5] = (ImageButton) findViewById(R.id.button5_d);
        numberButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "5");
            }
        });
        numberButtons[6] = (ImageButton) findViewById(R.id.button6_d);
        numberButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "6");
            }
        });
        numberButtons[7] = (ImageButton) findViewById(R.id.button7_d);
        numberButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "7");
            }
        });
        numberButtons[8] = (ImageButton) findViewById(R.id.button8_d);
        numberButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "8");
            }
        });
        numberButtons[9] = (ImageButton) findViewById(R.id.button9_d);
        numberButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destination.setText(destination.getText() + "9");
            }
        });
    }

    private void success(String destination, String username, int pin, String voice) {
        Bundle bundle = new Bundle();
        bundle.putString("Destination", destination);
        bundle.putString("Username", username);
        bundle.putInt("Pin", pin);
        bundle.putString("Voice", voice);
        Intent intent = new Intent(this, EnterAmount.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}