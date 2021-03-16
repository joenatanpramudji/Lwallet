package lsever;
import java.io.IOException;

import com.google.firebase.database.*;
//NOT USED, ONLY AS OLDER REF BACKUP
public class RetrieveFirebase2 implements Runnable{
	
	public void run() {
        FireBaseService fbs = null;
        try {
            fbs = new FireBaseService();
        } catch (IOException e) {
            e.printStackTrace();
        }

        DatabaseReference ref = fbs.getDb()
                .getReference("/");
        
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
		
		DatabaseReference accRef = fbs.getDb().getReference("Accounts");
		DatabaseReference userRef;
		userRef = accRef.child("Joe");
		userRef.child("Token").setValueAsync(111);
        
        /*userRef.child("Token").addChildEventListener(new ChildEventListener(){
        	@Override
        	public void onChildChanged(DataSnapshot dataSnapshot, String s) {
        		
        	} 


        	public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
        }); */
	}
}
