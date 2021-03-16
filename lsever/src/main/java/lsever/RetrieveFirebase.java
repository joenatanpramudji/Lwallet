package lsever;
import java.io.IOException;

import com.google.firebase.database.*;
//NOT USED, ONLY USED AS OLDER REF BACKUP
public class RetrieveFirebase implements Runnable{
	
	private String cardV;
	private String cardExp;
	private String cardNum;
	private String userName= "Joe";
	FireBaseService fbs = null;
	
	public RetrieveFirebase() {
		try {
            fbs = new FireBaseService();
        } catch (IOException e) {
            e.printStackTrace();
        }

		/*DatabaseReference accRef = fbs.getDb().getReference("Accounts");
		DatabaseReference userRef = accRef.child("Joe");
		userRef.child("Credit Card").addListenerForSingleValueEvent(new ValueEventListener() {
			
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object data1 = dataSnapshot.child("CVV").getValue();
				Object data2 = dataSnapshot.child("ExpDate").getValue();
				Object data3 = dataSnapshot.child("Number").getValue();
				
				//cardV = data1.toString();
				//cardExp = data2.toString();
				//cardNum = data3.toString(); 
				
				
				setCredential(data1.toString(), data2.toString(), data3.toString());
				
				System.out.println("If this: "+cardV);	
			}
			
			public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
		}); */
		
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public void setCredential(String cv, String ce, String cn) {
		cardV = cv;
		cardExp = ce;
		cardNum = cn;
		
	}
	
	public void setCredential(String cn) {
		cardNum = cn;	
	}
	
	
	public void run() {
        
       /* try {
            fbs = new FireBaseService();
        } catch (IOException e) {
            e.printStackTrace();
        } */

        DatabaseReference ref = fbs.getDb()
                .getReference("/");
        
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference userRef;
		DatabaseReference accRef = fbs.getDb().getReference("Accounts");
		userRef = accRef.child(userName);
		//userRef.child("Token").setValueAsync(115);

		HashS h = new HashS();
		HashB b = new HashB();
		//userRef.child("Password").setValueAsync(b.bcryptHash("jp123456"));
		
		
	/*	userRef.child("Credit Card").addListenerForSingleValueEvent(new ValueEventListener() {
			
			public void onDataChange(DataSnapshot dataSnapshot) {
				Object data1 = dataSnapshot.child("CVV").getValue();
				Object data2 = dataSnapshot.child("ExpDate").getValue();
				Object data3 = dataSnapshot.child("Number").getValue();
				
				//cardV = data1.toString();
				//cardExp = data2.toString();
				//cardNum = data3.toString(); 
				
				
				setCredential(data1.toString(), data2.toString(), data3.toString());
				
				System.out.println(cardV);
				System.out.println(cardExp);
				System.out.println(cardNum);
				
			}
			
			public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
		}); */
		//addListenerForSingleValueEvent
		//addValueEventListener
		//addValueEventListener
        userRef.child("Credit Card").addValueEventListener(new ValueEventListener() {
        	
        	public void onDataChange(DataSnapshot dataSnapshot) {
        		Object data1 = dataSnapshot.child("Number").getValue();
        		Object data2 = dataSnapshot.child("TokenRequest").getValue();
        		
        		boolean tReq = (Boolean) data2;
        		
        		System.out.println("testing cc change but req false");
        		if(tReq){
        			System.out.println("Token Request: "+tReq);
        			System.out.println("Requesting Tokenization Service...");
        			Token t = new Token();
        			t.genToken(data1.toString());
        			DatabaseReference userRef;
        			DatabaseReference accRef = fbs.getDb().getReference("Accounts");
        			userRef = accRef.child(userName);
        			
        			userRef.child("Credit Card").removeEventListener(this);
        			userRef.child("Credit Card").child("Token").setValueAsync(t.getGenTok());
        			userRef.child("Credit Card").child("TokenRequest").setValueAsync(false);
        			
        			try {
        	            Thread.sleep(5000);
        	        } catch (InterruptedException e) {
        	            e.printStackTrace();
        	        }
        		}
        		else{
        			System.out.println("Token Request: "+tReq);
        		}

        		//4234173647084180
        	}
        	

        	public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
        	
        	
        }); 
        
		
		/*  userRef.child("Credit Card").addValueEventListener(new ValueEventListener() {
        	
        	public void onDataChange(DataSnapshot dataSnapshot) {
        		DatabaseReference userRef;
    			DatabaseReference accRef = fbs.getDb().getReference("Accounts");
    			userRef = accRef.child("Joe");
        		
        		Object data1 = dataSnapshot.child("Number").getValue();
        		String dataStr = data1.toString();
        		setCredential(dataStr);
        		
        		
        		userRef.child("Credit Card").child("Token Request").addListenerForSingleValueEvent(new ValueEventListener(){
        			
        			
        			public void onDataChange(DataSnapshot dataSnapshot) {
        				DatabaseReference userRef;
            			DatabaseReference accRef = fbs.getDb().getReference("Accounts");
            			userRef = accRef.child("Joe");
        				
        				Object data2 = dataSnapshot.child("TokenRequest").getValue();
        				boolean tReq = (Boolean) data2;
	        		if(tReq){
	        			System.out.println("Token Request: "+tReq);
	        			System.out.println("Requesting Tokenization Service...");
	        			Token t = new Token();
	        			t.genToken(getCardNum());
	        			
	        			userRef.child("Credit Card").child("Token").setValueAsync(t.getGenTok());
	        			userRef.child("Credit Card").child("TokenRequest").setValueAsync(false);
	        			try {
	        	            Thread.sleep(5000);
	        	        } catch (InterruptedException e) {
	        	            e.printStackTrace();
	        	        }
	        		}
	        		else{
	        			//System.out.println("Token Request: "+tReq);
	        		}
	        	}
        		public void onCancelled(DatabaseError error) {
            		System.out.print("Error: " + error.getMessage());
                }
        		
        		});
        		
        	}

        	public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
        	
        	
        }); */
        
        //BCrypt
        
       /* userRef.child("Password").addValueEventListener(new ValueEventListener() {

        	public void onDataChange(DataSnapshot dataSnapshot) {
        		Object data1 = dataSnapshot.getValue();
        		String testHash = data1.toString();
        		//System.out.println(document);
        		System.out.println(testHash);
        		HashB b = new HashB();
        		System.out.println("Verifying password: "+b.verifyHash("jp123456", testHash));
        		
        		
        	}

        	public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
        	
        }); */
        
	}
	

		
}
