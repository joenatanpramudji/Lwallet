package com.example.lwallet;

import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.widget.ArrayAdapter;

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

public class Connection { //Class for connection with Firebase
    //String correctPassword;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference userRef;
    DatabaseReference accRef = database.getReference("Accounts" /*This string is a reference to the firebase key*/); // Initializing the firebase reference
    DatabaseReference bankRef = database.getReference("Bank" /*This string is a reference to the bank firebase key*/); // Initializing the firebase reference

    DatabaseReference cardRef;
    DatabaseReference destinationRef;
    DatabaseReference historyCounting;
    DatabaseReference historyRef;
   // DatabaseReference cashRef;

    ArrayList<String> usernameReference = new ArrayList<>();
    ArrayList<String> passwordReference = new ArrayList<>();

    ArrayList<String> destinationValue = new ArrayList<>();

    public static double cash;
    public static double points;
    public static int pin;
    public static String voice;
    public static int securityLevel;
    public static boolean activeStatus = true;
    public static double destinationCash;

    public static boolean token;


    ArrayList<String[]> oArrr = new ArrayList<>();

//    public void HistoryArray(String amount, String date, String status) //Something is wrong here
//    {
//        inArrr.add(amount);
//        inArrr.add(date);
//        inArrr.add(status);
//        oArrr.add(inArrr);
//    }
        public void loginData() // This function is used for Login
        {

            accRef.addValueEventListener(new ValueEventListener() { //The reference needs a listener to listen to data changes
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //value[0] = dataSnapshot.getValue(String.class);
                    for(DataSnapshot postSnapshot : dataSnapshot.getChildren())
                    {
                        Map<String, Object> map = (Map<String, Object>) postSnapshot.getValue(); //The datasnapshot will be mapped like this in order to be retrieved
//                       String docRef = postSnapshot.getRef();
//                       passwordReference.add();
                        String docRef = postSnapshot.getKey(); //Getkey is used to get the Key from the reference. Example Key = "Account"
                        usernameReference.add("" + docRef);
                        passwordReference.add("" + map.get("Password")); //Adding the username and password into a list for checking
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

        public void readData(String username, ReadCallback readCallBack) // THis function is to read the data stored in firebase, essentially it is similar like the login function
        {
           userRef = accRef.child(username); // THe username parameter will be used for the Key
           userRef.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                   cash = Double.parseDouble(map.get("Cash").toString());
                   points = Double.parseDouble(map.get("Points").toString());
                   //pin = Integer.parseInt(map.get("Pin").toString());
                   voice = map.get("Voice").toString();
                   securityLevel = Integer.parseInt(map.get("Security Level").toString());
                   if(map.get("Status").toString().equals("Active"))
                   {
                       activeStatus = true;
                   }
                   else
                   {
                       activeStatus = false;
                   }


                   readCallBack.onCallback(cash, points, securityLevel); // An interface is used since onDataChange is an asynchronous function. The value may only be retrieved after the onCreate method. So we use a callback function
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                   Log.w(TAG, "Failed to read value.", error.toException());
               }
           });

        userRef.child("Credit Card").child("Token").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Object obj = snapshot.getValue();
                pin = Integer.parseInt(obj.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


//           historyCounting.addValueEventListener(new ValueEventListener() {
//               @Override
//               public void onDataChange(@NonNull DataSnapshot snapshot) {
//                   for(int i = 0; i < snapshot.getChildrenCount(); i++)
//                   {
//                       historyRef = historyCounting.child("" + i);
//                       historyRef.addValueEventListener(new ValueEventListener() {
//                           @Override
//                           public void onDataChange(@NonNull DataSnapshot snapshot) {
//                               Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
//                               //HistoryArray(map.get("Amount").toString(), map.get("Date").toString(), map.get("Status").toString());
//                               inArrr.add(map.get("Amount").toString());
//                               inArrr.add(map.get("Date").toString());
//                               inArrr.add(map.get("Status").toString()); //GOT ERROR
//                               oArrr.add(inArrr);
//                           }
//
//                           @Override
//                           public void onCancelled(@NonNull DatabaseError error) {
//
//                           }
//                       });
//                   }
//               }
//
//               @Override
//               public void onCancelled(@NonNull DatabaseError error) {
//
//               }
//           });

           Log.d(TAG, "Cash is : " + cash);
           Log.d(TAG, "Points is : " + points);

        }

       public interface ReadCallback
       {
           void onCallback(double valueCash, double valueDouble, int securityLevel);
       }

       public interface TransferCallback
       {
           void onCallback(double destinationCash);
       }

       public void topUp(double value, String username, String cardNumber, String ccv, String expDate/*, boolean tokenRequest*//*, TopupCallBack topupCallBack*/, String token) // This function is used for Top Up
       {
           userRef = accRef.child(username);
          // cardRef = bankRef.child("Joenatan");
           Log.d(TAG, userRef.child("Cash").get().toString());
           userRef.child("Cash").setValue(value + cash);
          // userRef.child("Credit Card").child("Token").setValue(token);
           // Setvalue is to set the value of the database according to the key
           //userRef.child("Credit Card").child("TokenRequest").setValue(tokenRequest);
       }

       public void updatePin(String pin)
       {
           userRef = accRef.child("Joenatan");
           userRef.child("Credit Card").child("Token").setValue(pin);
       }

       public void saveCard(String cardNumber, String ccv, String expDate)
       {
           cardRef = bankRef.child("Joenatan");
           cardRef.child("NumberReq").setValue(cardNumber);
           cardRef.child("CVVReq").setValue(ccv);
           cardRef.child("ExpDateReq").setValue(expDate);
       }
       ArrayList<String> destinationKey = new ArrayList<>();

//       public interface DestinationCallback
//       {
//           void onCallback(String destination);
//       }

        public void transfer(double value, String username, String destination, double destinationCash) // Function to transfer funds from one account to another
        {
//            readtransfer(value, username, destination, new TransferCallback() {
//                @Override
//                public void onCallback(double destinationCash) {
//
//                }
//            });

            accRef.child(destination).child("Cash").setValue(destinationCash + value); //THis is used to add the cash into the destination account
        }

       public void readtransfer(double value, String username, String destination , TransferCallback transferCallback) //This is a method used together with transfer method to listen to the cash value in firebase
       {
           userRef = accRef.child(username);
           userRef.child("Cash").setValue(cash - value);
           destinationRef = accRef.child(destination);
           destinationRef.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
                   destinationCash = Double.parseDouble(map.get("Cash").toString());
                   transferCallback.onCallback(destinationCash); // Another callback function
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

       public void register(String username, String email, String password, String voice) //Registration function
       {
           HashB hasb = new HashB();
            accRef.child(username).child("Email").setValue(email);
            //Encryption function
            accRef.child(username).child("Password").setValue(hasb.bcryptHash(password));
            accRef.child(username).child("Key").setValue(new Random().nextInt(90000) + 10000);
            accRef.child(username).child("Cash").setValue(0);
            accRef.child(username).child("Points").setValue(500);
            accRef.child(username).child("Pin").setValue(new Random().nextInt(9000) + 1000);
            accRef.child(username).child("Credit Card").child("CCV").setValue(0);
            accRef.child(username).child("Credit Card").child("ExpDate").setValue(0);
            accRef.child(username).child("Credit Card").child("Number").setValue(0);

       }

       public void register(String username, String voice) // This is to register the voice
       {
           accRef.child(username).child("Voice").setValue(voice);
       }

       public void setHistory(String username, String status, String date, double amount, int count, String destination) // THis is to set the history for transfer
       {
           accRef.child(username).child("History").child(count + "").child("Status").setValue(status);
           accRef.child(username).child("History").child(count + "").child("Date").setValue(date);
           accRef.child(username).child("History").child(count + "").child("Amount").setValue(amount);
           accRef.child(username).child("History").child(count + "").child("Destination").setValue(destination);
       }

    public void setHistory(String username, String status, String date, double amount, int count)// THis is to set the history for for topup
    {
        accRef.child(username).child("History").child(count + "").child("Status").setValue(status);
        accRef.child(username).child("History").child(count + "").child("Date").setValue(date);
        accRef.child(username).child("History").child(count + "").child("Amount").setValue(amount);
    }

    public void setStatus(String username)
    {
        accRef.child(username).child("Status").setValue("Blocked");
    }

    public interface HistoryCallback
    {
        void onCallback(ArrayList<String[]> oArr);
    }
    String[] inArrr = new String[3];
       public void getHistory(String username, HistoryCallback historyCallback) // THis function is to get the history
       {
           userRef = accRef.child(username);
           historyCounting = userRef.child("History");
           historyCounting.addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {

                   for(DataSnapshot postSnapshot : snapshot.getChildren())
                   {

                       Map<String, Object> map = (Map<String, Object>) postSnapshot.getValue();
                       inArrr[0] = "" + map.get("Amount");
                       inArrr[1] = "" + map.get("Date");
                       inArrr[2] = "" + map.get("Status");
                       oArrr.add(inArrr);
                       historyCallback.onCallback(oArrr);
//                       Log.d("OUTER ARRAY IS ", oArrr.toString());
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }

//       public void setTokenRequest(String username) //This method is to set the token (Tokenization)
//       {
//            userRef = accRef.child(username);
////            userRef.addValueEventListener(new ValueEventListener() {
////                @Override
////                public void onDataChange(@NonNull DataSnapshot snapshot) {
////                    Map<String, Object> map = (Map<String, Object>) snapshot.getValue();
////                    String token = map.get("Key").toString();
////                }
////
////                @Override
////                public void onCancelled(@NonNull DatabaseError error) {
////
////                }
////            });
//           userRef.child("Credit Card").child("TokenRequest").setValue(true);
//       }



       public void tokenApproval()
       {

           accRef.child("Joenatan").child("Credit Card").child("TokenApproval").addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {

                    Object data1 = snapshot.getValue();
                    token = (Boolean) data1;
                    Log.d("TOKEN STATUS", token + "");
                   if(token == true)
                   {

                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
       }

}
