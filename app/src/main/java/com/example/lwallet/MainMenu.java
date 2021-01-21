package com.example.lwallet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import com.google.common.util.concurrent.ListenableFuture;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static android.content.ContentValues.TAG;

public class MainMenu extends AppCompatActivity {
   // private Toolbar maintoolbar;

//    TextView txtResultBody;
//
//    private BarcodeDetector detector;
//    private Uri imageUri;
//    private static final int REQUEST_CAMERA_PERMISSION = 200;
//    private static final int CAMERA_REQUEST = 101;
//    private static final String TAG = "API123";
//    private static final String SAVED_INSTANCE_URI = "uri";
//    private static final String SAVED_INSTANCE_RESULT = "result";


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


//        cn.getHistory(intent.getStringExtra("username"));
//        Log.d("OUTER ARRAY IS ", cn.oArrr + "");

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
                openDestination(intent.getStringExtra("username"), cn.pin, cn.voice);
            }
        });

        ImageButton btnHistory = (ImageButton) findViewById(R.id.buttonHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory(intent.getStringExtra("username"));
            }
        });

        ImageButton btnProfile = (ImageButton) findViewById(R.id.buttonProfile);
        btnProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openProfile();
            }
        });

        ImageButton buttonLogout = (ImageButton) findViewById(R.id.buttonWater);
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });


//        if (savedInstanceState != null) {
//            if (imageUri != null) {
//                imageUri = Uri.parse(savedInstanceState.getString(SAVED_INSTANCE_URI));
//                txtResultBody.setText(savedInstanceState.getString(SAVED_INSTANCE_RESULT));
//            }
//        }

//        detector = new BarcodeDetector.Builder(getApplicationContext())
//                .setBarcodeFormats(Barcode.DATA_MATRIX | Barcode.QR_CODE)
//                .build();
//
//        if (!detector.isOperational()) {
//            txtResultBody.setText("Detector initialisation failed");
//            return;
//        }



        ImageButton btnScan = (ImageButton) findViewById(R.id.scanButton);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ActivityCompat.requestPermissions(PictureBarcodeActivity.this, new
//                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA}, REQUEST_CAMERA_PERMISSION);
                scan();
            }
        });
    }

    private void scan() {
        startActivity(new Intent(this, Scan.class));
    }

    private void logout() {
        finish();
        Login lg = new Login();
        SharedPreferences pref = getSharedPreferences(lg.PREFS_NAME, MODE_PRIVATE);
        pref.edit().clear().commit();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void openProfile() {
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }

    private void openHistory(String username) {
        Intent intent = new Intent(this, History.class);
        intent.putExtra("Username", username);
        startActivity(intent);
    }

    private void openDestination(String username, int pin, String voice) {
        Bundle bundle = new Bundle();
        bundle.putString("Username", username);
        bundle.putInt("Pin", pin);
        bundle.putString("Voice", voice);
        Intent intent = new Intent(this, Destination.class);
        intent.putExtras(bundle);
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