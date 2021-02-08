package com.example.lwallet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Success extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        Intent intent = getIntent();

        if(intent.getStringExtra("Status").equals("Top Up"))
            successNotification(intent.getDoubleExtra("TopUp_Value", 0));
        else
            successNotification(intent.getStringExtra("Destination"), intent.getDoubleExtra("Transfer_Amount", 0));

        ImageButton buttonHome = (ImageButton) findViewById(R.id.buttonHomeSuccess);
        buttonHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                backToHome(intent.getStringExtra("Username"));
            }
        });
    }

    private void successNotification(String destination , double amount) {

        Intent intent = new Intent(this, Success.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
//        NotificationCompat n  = new Notification.Builder(this)
//                .setContentTitle("Transfer Success")
//                .setContentText("Your successfuly transfered " + amount + " to " + destination)
//                .setContentIntent(pIntent)
//                .setAutoCancel(true).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this/*, CHANNEL_ID*/)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Transfer Success")
                .setContentText("Your successfuly transfered " + amount + " to " + destination)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pIntent)
                .setAutoCancel(true);



        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }

    private void successNotification(double amount) {

        Intent intent = new Intent(this, Success.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pIntent = PendingIntent.getActivity(this, (int) System.currentTimeMillis(), intent, 0);
//        Notification n  = new Notification.Builder(this)
//                .setContentTitle("Transfer Success")
//                .setContentText("Your successfuly topped up " + amount + " to " + "your account")
//                .setContentIntent(pIntent)
//                .setAutoCancel(true).build();

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this/*, CHANNEL_ID*/)
                .setSmallIcon(R.drawable.common_google_signin_btn_icon_dark)
                .setContentTitle("Top Up Success")
                .setContentText("Your successfuly topped up " + amount + " to " + "your account")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                // Set the intent that will fire when the user taps the notification
                .setContentIntent(pIntent)
                .setAutoCancel(true);


        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(0, builder.build());
    }

    private void backToHome(String username) {
        Intent intent = new Intent(this, MainMenu.class);
        intent.putExtra("username", username);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}