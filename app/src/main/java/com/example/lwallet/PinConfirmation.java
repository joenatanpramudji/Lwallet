package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class PinConfirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_confirmation);
        TextView pin = (TextView) findViewById(R.id.pinCode);

        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonC);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                success();
            }
        });

        ImageButton numberButtons[] = new ImageButton[10];
        numberButtons[0] = (ImageButton) findViewById(R.id.button0_c);
        numberButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pin.getText() != "0")
                {
                    pin.setText(pin.getText() + "0");
                }
            }
        });
        numberButtons[1] = (ImageButton) findViewById(R.id.button1_c);
        numberButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin.setText(pin.getText() + "1");

            }
        });
        numberButtons[2] = (ImageButton) findViewById(R.id.button2_c);
        numberButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin.setText(pin.getText() + "2");

            }
        });
        numberButtons[3] = (ImageButton) findViewById(R.id.button3_c);
        numberButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin.setText(pin.getText() + "3");
            }
        });
        numberButtons[4] = (ImageButton) findViewById(R.id.button4_c);
        numberButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin.setText(pin.getText() + "4");
            }
        });
        numberButtons[5] = (ImageButton) findViewById(R.id.button5_c);
        numberButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin.setText(pin.getText() + "5");
            }
        });
        numberButtons[6] = (ImageButton) findViewById(R.id.button6_c);
        numberButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin.setText(pin.getText() + "6");
            }
        });
        numberButtons[7] = (ImageButton) findViewById(R.id.button7_c);
        numberButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin.setText(pin.getText() + "7");
            }
        });
        numberButtons[8] = (ImageButton) findViewById(R.id.button8_c);
        numberButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin.setText(pin.getText() + "8");
            }
        });
        numberButtons[9] = (ImageButton) findViewById(R.id.button9_c);
        numberButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    pin.setText(pin.getText() + "9");
            }
        });
    }

    private void success() {
        Intent intent = new Intent(this, Success.class);
        startActivity(intent);
    }
}