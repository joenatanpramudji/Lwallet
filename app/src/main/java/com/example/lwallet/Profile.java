package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        TextView profileCash = (TextView) findViewById(R.id.profileCash);
        TextView profilePoints = (TextView) findViewById(R.id.profilePoints);
        TextView accountUsername = (TextView) findViewById(R.id.accountUsername);
        accountUsername.setText("Username : " + getIntent().getStringExtra("Username"));
        Connection cn = new Connection();
        cn.readData(getIntent().getStringExtra("Username"), new Connection.ReadCallback() {
            @Override
            public void onCallback(double valueCash, double valueDouble) {
                profileCash.setText("RM " + valueCash);
                profilePoints.setText(valueDouble + " pts");

            }
        });
    }
}