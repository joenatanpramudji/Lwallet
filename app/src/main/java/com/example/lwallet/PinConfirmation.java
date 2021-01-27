package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.speech.RecognizerIntent;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PinConfirmation extends AppCompatActivity {
    private final int REQ_CODE = 100;
    //TextView textView;

    public static final String PREFS_NAME = "HistoryCount";
    public static final String PREF_HISTORYCOUNT = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_confirmation);

        //SharedPreferences pref = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        //int historyCount = pref.getInt(PREF_HISTORY_COUNT, 0);


        TextView pin = (TextView) findViewById(R.id.pinCode);
        Connection cn = new Connection();
        ImageButton nextButton = (ImageButton) findViewById(R.id.nextButtonC);

        Bundle statusIntent = getIntent().getExtras();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText pinCode = (EditText) findViewById(R.id.pinCode);
                if(pinCode.getText().toString().equals(statusIntent.get("Pin").toString()))
                {
                    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "NEED TO SPEAK");

                    startActivityForResult(intent, REQ_CODE);
                    /*try {

                        Log.d("Check status intent", statusIntent.get("Status").toString());
                        if (statusIntent.get("Status").toString().equals("TopUp"))
                        {
                            //Write Data (add)

                            Log.d("Top Up Value is : ", statusIntent.get("TopUp_Value").toString());
                            Log.d("Top Up Value is : ", statusIntent.get("Username").toString());
                            //cn.testTopup();


                        }
                        else if(statusIntent.get("Status").toString().equals("Transfer"))
                        {
                            //Write Data (Min)

                            //cn.transfer((double)statusIntent.get("Transfer_Amount"), statusIntent.get("Username").toString(), statusIntent.get("Destination").toString());

//                            cn.getDestination(statusIntent.get("Username").toString(), statusIntent.get("Destination").toString(), new Connection.DestinationCallback() {
//                                @Override
//                                public void onCallback(String destination) {
//                                    for(int i = 0; i < cn.destinationKey.size(); i++)
//                                    {
//                                        if(statusIntent.get("Destination").toString().equals(destination))
//                                        {
//                                            cn.transfer((double)statusIntent.get("Transfer_Amount"), statusIntent.get("Username").toString(), i);
//                                        }
//                                    }
//                                }
//                            });
                            startActivityForResult(intent, REQ_CODE);


                        }
                        else if(statusIntent.get("Status").toString().equals("Pay"))
                        {

                            //Write Data (Min)
                        }

                    }catch (ActivityNotFoundException a)
                    {
                        Toast.makeText(getApplicationContext(),
                                "Sorry your device not supported",
                                Toast.LENGTH_SHORT).show();
                    }
*/

                }
                else if(pinCode.getText().toString().equals(""))
                {
                    Snackbar alert = Snackbar.make(v, "Please insert pin!", Snackbar.LENGTH_LONG);
                    alert.show();
                }
                else
                {
                    Snackbar alert = Snackbar.make(v, "Wrong pin!", Snackbar.LENGTH_LONG);
                    alert.show();
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
        Bundle statusIntent = getIntent().getExtras();
        Intent intent = new Intent(this, Success.class);
        intent.putExtra("Username", statusIntent.get("Username").toString());

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        int count = settings.getInt("COUNT", 0);



        Log.d("Count is ","" + count);

        if(statusIntent.getString("Status").equals("TopUp"))
        {

            intent.putExtra("Amount", statusIntent.getDouble("TopUp_Value"));
            intent.putExtra("Status", "Top Up");
            intent.putExtra("Date", new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime()));

            new Connection().setHistory(statusIntent.get("Username").toString(),
                    "Top Up", new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime()),
                    statusIntent.getDouble("TopUp_Value"),count);
        }
        else if(statusIntent.getString("Status").equals("Transfer"))
        {

            intent.putExtra("Amount", statusIntent.getDouble("Transfer_Amount"));
            intent.putExtra("Status", "Transfer");
            intent.putExtra("Date", new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime()));

            new Connection().setHistory(statusIntent.get("Username").toString(),
                    "Transfer", new SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault()).format(Calendar.getInstance().getTime()),
                    statusIntent.getDouble("Transfer_Amount"), count, statusIntent.getString("Destination"));
        }
        count += 1;
        editor.putInt("COUNT", count);
        editor.commit();
        //getSharedPreferences(PREF_HISTORYCOUNT, MODE_PRIVATE).edit().putInt(PREF_HISTORYCOUNT, count).commit();
        startActivity(intent);
    }

    public void incrementCounter()
    {

    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent intent = getIntent();
        switch (requestCode) {
            case REQ_CODE: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    Connection cn = new Connection();
                    if(result.get(0).toString().equalsIgnoreCase(intent.getStringExtra("Voice")) && intent.getStringExtra("Status").toString().equals("TopUp"))
                    {

                        cn.topUp((double)intent.getDoubleExtra("TopUp_Value", 0), intent.getStringExtra("Username"), intent.getStringExtra("Card_Number"), intent.getStringExtra("CCV"), intent.getStringExtra("Expiry_Date")/*, true*/);
                        success();
                    }else if(result.get(0).toString().equalsIgnoreCase(intent.getStringExtra("Voice")) && intent.getStringExtra("Status").toString().equals("Transfer"))
                    {
                        cn.readtransfer((double) intent.getDoubleExtra("Transfer_Amount", 0), intent.getStringExtra("Username"), intent.getStringExtra("Destination"), new Connection.TransferCallback() {
                            @Override
                            public void onCallback(double destinationCash) {
                                cn.transfer((double) intent.getDoubleExtra("Transfer_Amount", 0), intent.getStringExtra("Username"), intent.getStringExtra("Destination"), destinationCash);
                            }
                        });
                        success();
                    }
                    else
                    {
                        Snackbar alert = Snackbar.make(findViewById(R.id.pinConfirmID).getRootView(), "Voice mismatch!", Snackbar.LENGTH_LONG);
                        alert.show();
                    }
                    //textView.setText(result.get(0).toString());
                }
                break;
            }
        }
    }
}