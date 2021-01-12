package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class EnterAmount extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_amount);

        TextView amount = (TextView) findViewById(R.id.amount);

        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonE);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Bundle bundle = getIntent().getExtras();
                success(bundle.get("Username").toString(), Double.parseDouble(amount.getText().toString()), bundle.get("Destination").toString(), Integer.parseInt(bundle.get("Pin").toString()), bundle.get("Voice").toString());
            }
        });

        ImageButton numberButtons[] = new ImageButton[10];
        numberButtons[0] = (ImageButton) findViewById(R.id.button0_e);
        numberButtons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(amount.getText() != "0")
                {
                    amount.setText(amount.getText() + "0");
                }
            }
        });
        numberButtons[1] = (ImageButton) findViewById(R.id.button1_e);
        numberButtons[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText(amount.getText() + "1");

            }
        });
        numberButtons[2] = (ImageButton) findViewById(R.id.button2_e);
        numberButtons[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText(amount.getText() + "2");

            }
        });
        numberButtons[3] = (ImageButton) findViewById(R.id.button3_e);
        numberButtons[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText(amount.getText() + "3");
            }
        });
        numberButtons[4] = (ImageButton) findViewById(R.id.button4_e);
        numberButtons[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText( amount.getText() + "4");
            }
        });
        numberButtons[5] = (ImageButton) findViewById(R.id.button5_e);
        numberButtons[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText( amount.getText() + "5");
            }
        });
        numberButtons[6] = (ImageButton) findViewById(R.id.button6_e);
        numberButtons[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText( amount.getText() + "6");
            }
        });
        numberButtons[7] = (ImageButton) findViewById(R.id.button7_e);
        numberButtons[7].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText( amount.getText() + "7");
            }
        });
        numberButtons[8] = (ImageButton) findViewById(R.id.button8_e);
        numberButtons[8].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText( amount.getText() + "8");
            }
        });
        numberButtons[9] = (ImageButton) findViewById(R.id.button9_e);
        numberButtons[9].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                amount.setText( amount.getText() + "9");
            }
        });



    }

    private void success(String username, double transferAmount, String destination, int pin, String voice) {
        Intent intent = new Intent(this, PinConfirmation.class);
        intent.putExtra("Username", username);
        intent.putExtra("Transfer_Amount", transferAmount);
        intent.putExtra("Status", "Transfer");
        intent.putExtra("Destination", destination);
        intent.putExtra("Voice", voice);
        intent.putExtra("Pin", pin);
        startActivity(intent);
    }
}