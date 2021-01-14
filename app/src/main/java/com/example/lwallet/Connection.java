package com.example.lwallet;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static android.content.ContentValues.TAG;

public class Connection {
    //String correctPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference accRef = database.getReference("Accounts");
    DatabaseReference userRef;
    DatabaseReference destinationRef;
   // DatabaseReference cashRef;

    ArrayList<String> usernameReference = new ArrayList<>();
    ArrayList<String> passwordReference = new ArrayList<>();

    ArrayList<String> destinationValue = new ArrayList<>();

    public static double cash;
    public static double points;
    public static int pin;
    public static String voice;
    public static double destinationCash;
        public void loginData()
        {

            accRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //value[0] = dataSnapshot.getValue(String.class);
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {
                        Map<String, Object> map = (Map<String, Object>) postSnapshot.getValue();
//                       String docRef = postSnapshot.getRef();
//                       passwordReference.add();
                        String docRef = postSnapshot.getKey();
                        usernameReference.add("" + docRef);
                        passwordReference.add("" + map.get("Password"));
                        /*Log.d(TAG, "Name is " + docRef);
                        Log.d(TAG, "Value is " + map);
                        Log.d(TAG, "Username Array value is "+ usernameReference.get(0));
                        Log.d(TAG, "Password Array value is" + passwordReference.get(0));*/

                    }

                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException());
                }
            });

        }

        public void readData(String username, ReadCallback readCallBack)
        {
           userRef = accRef.child(username);
           userRef.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                   cash = Double.parseDouble(map.get("Cash").toString());
                   points = Double.parseDouble(map.get("Points").toString());
                   pin = Integer.parseInt(map.get("Pin").toString());
                   voice = map.get("Voice").toString();
                   readCallBack.onCallback(cash, points);
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                   Log.w(TAG, "Failed to read value.", error.toException());
               }
           });
           Log.d(TAG, "Cash is : " + cash);
           Log.d(TAG, "Points is : " + points);

        }

       public interface ReadCallback
       {
           void onCallback(double valueCash, double valueDouble);
       }

       public interface TransferCallback
       {
           void onCallback(double destinationCash);
       }

       public void topUp(double value, String username, String cardNumber, String ccv, String expDate/*, TopupCallBack topupCallBack*/)
       {
           userRef = accRef.child(username);
           Log.d(TAG, userRef.child("Cash").get().toString());
           userRef.child("Cash").setValue(value + cash);
           userRef.child("Credit Card").child("Number").setValue(cardNumber);
           userRef.child("Credit Card").child("CCV").setValue(ccv);
           userRef.child("Credit Card").child("ExpDate").setValue(expDate);
       }
       ArrayList<String> destinationKey = new ArrayList<>();

//       public interface DestinationCallback
//       {
//           void onCallback(String destination);
//       }

        public void transfer(double value, String username, String destination, double destinationCash)
        {
//            readtransfer(value, username, destination, new TransferCallback() {
//                @Override
//                public void onCallback(double destinationCash) {
//
//                }
//            });

            accRef.child(destination).child("Cash").setValue(destinationCash + value); //Still got error
        }

       public void readtransfer(double value, String username, String destination , TransferCallback transferCallback)
       {
           userRef = accRef.child(username);
           userRef.child("Cash").setValue(cash - value);
           destinationRef = accRef.child(destination);
           destinationRef.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                   destinationCash = Double.parseDouble(map.get("Cash").toString());
                   transferCallback.onCallback(destinationCash);
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }

           });


//           accRef.addListenerForSingleValueEvent(new ValueEventListener() { //There is something wrong here
//               @Override
//               public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                   if(snapshot.va)
//
//                   for(DataSnapshot postSnapshot : snapshot.getChildren())
//                   {
//                       Map<String, Object> map = (Map<String, Object>) postSnapshot.getValue();
//                       destinationKey.add(postSnapshot.getKey());
//                       destinationValue.add("" + map.get("Destination"));
////                       destinationCallback.onCallback(destinationReference);
//                   }
//               }
//               @Override
//               public void onCancelled(@NonNull DatabaseError error) {
//
//               }
//           });
//
//           for(int i = 0; i < destinationKey.size();i++)//And here
//           {
//
//               if(destination.equals(destinationValue.get(i).toString()))
//               {
//                   destinationRef = accRef.child(destinationKey.get(i));
//                   destinationRef.child("Cash").setValue(cash + value);
//                   Log.d(TAG, "TRANSFER SUCCESSFUL");
//               }
//           }
           Log.d("Destination value is ", destinationValue.toString());
           Log.d("Destination key is ", destinationKey.toString());
       }

       public void register(String username, String email, String password, String voice)
       {
            accRef.child(username).child("Email").setValue(email);
            accRef.child(username).child("Password").setValue(password);
            accRef.child(username).child("Key").setValue(new Random().nextInt(90000) + 10000);
            accRef.child(username).child("Cash").setValue(0);
            accRef.child(username).child("Points").setValue(500);
            accRef.child(username).child("Pin").setValue(new Random().nextInt(9000) + 1000);
            accRef.child(username).child("Credit Card").child("CCV").setValue(0);
            accRef.child(username).child("Credit Card").child("ExpDate").setValue(0);
            accRef.child(username).child("Credit Card").child("Number").setValue(0);

       }

       public void register(String username, String voice)
       {
           accRef.child(username).child("Voice").setValue(voice);
       }

}
