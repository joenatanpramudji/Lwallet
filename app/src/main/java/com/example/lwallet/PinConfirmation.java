package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import java.util.ArrayList;

public class PinConfirmation extends AppCompatActivity {
    private final int REQ_CODE = 100;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_confirmation);
        TextView pin = (TextView) findViewById(R.id.pinCode);

        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonC);
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "NEED TO SPEAK");
                try {
                    startActivityForResult(intent, REQ_CODE);
                }catch (ActivityNotFoundException a)
                {
                    Toast.makeText(getApplicationContext(),
                            "Sorry your device not supported",
                            Toast.LENGTH_SHORT).show();
                }


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

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if(result.get(0).toString().equalsIgnoreCase("hello"))
                    {
                        success();
                    }
                    //textView.setText(result.get(0).toString());
                }
                break;
            }
        }
    }
}