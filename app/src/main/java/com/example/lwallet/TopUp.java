package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;

public class TopUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);
        Intent intent = getIntent();
        Log.d("Topup Username", intent.getStringExtra("Username"));

        EditText cardNumber = (EditText) findViewById(R.id.creditCardNumber);
        EditText ccvNumber = (EditText) findViewById(R.id.CCVNumber);
        EditText expiryDate = (EditText) findViewById(R.id.expiryDate);



        ImageButton buttonHundred = (ImageButton) findViewById(R.id.buttonhundred);
        buttonHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumber.getText().toString().equals("") || ccvNumber.getText().toString().equals("") || expiryDate.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    proceedtoConfirmation(100, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"), cardNumber.getText().toString(), ccvNumber.getText().toString(), expiryDate.getText().toString());
                }
            }
        });

        ImageButton buttontwoHundred = (ImageButton) findViewById(R.id.buttonTwoHundred);
        buttontwoHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumber.getText().toString().equals("") || ccvNumber.getText().toString().equals("") || expiryDate.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    proceedtoConfirmation(200, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"), cardNumber.getText().toString(), ccvNumber.getText().toString(), expiryDate.getText().toString());
                }
            }
        });

        ImageButton buttonthreeHundred = (ImageButton) findViewById(R.id.buttonThreehundred);
        buttonthreeHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumber.getText().toString().equals("") || ccvNumber.getText().toString().equals("") || expiryDate.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    proceedtoConfirmation(300, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"), cardNumber.getText().toString(), ccvNumber.getText().toString(), expiryDate.getText().toString());
                }
            }
        });

        ImageButton buttonfourHundred = (ImageButton) findViewById(R.id.buttonFourhundred);
        buttonfourHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumber.getText().toString().equals("") || ccvNumber.getText().toString().equals("") || expiryDate.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    proceedtoConfirmation(400, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"), cardNumber.getText().toString(), ccvNumber.getText().toString(), expiryDate.getText().toString());
                }
            }
        });

        ImageButton buttonfiveHundred = (ImageButton) findViewById(R.id.buttonFiveHundred);
        buttonfiveHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumber.getText().toString().equals("") || ccvNumber.getText().toString().equals("") || expiryDate.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    proceedtoConfirmation(500, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"), cardNumber.getText().toString(), ccvNumber.getText().toString(), expiryDate.getText().toString());
                }
            }
        });

        ImageButton buttonSixHundred = (ImageButton) findViewById(R.id.buttonSixHundred);
        buttonSixHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cardNumber.getText().toString().equals("") || ccvNumber.getText().toString().equals("") || expiryDate.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    proceedtoConfirmation(600, intent.getStringExtra("Username"), intent.getIntExtra("Pin", 0), intent.getStringExtra("Voice"), cardNumber.getText().toString(), ccvNumber.getText().toString(), expiryDate.getText().toString());
                }
            }
        });
    }




    private void proceedtoConfirmation(double topupValue, String username, int pin, String voice, String cardNumber, String ccv, String expiryDate) {
        Intent intent = new Intent(this, PinConfirmation.class);
        Bundle b = new Bundle();
        b.putString("Status", "TopUp");
        b.putString("Username", username);
        b.putDouble("TopUp_Value", topupValue);
        b.putString("Voice", voice);
        b.putString("Card_Number", cardNumber);
        b.putString("CCV", ccv);
        b.putString("Expiry_Date", expiryDate);
        b.putInt("Pin", pin);
        intent.putExtras(b);
        startActivity(intent);
    }
}