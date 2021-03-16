package lsever;
import java.io.IOException;

import com.google.firebase.database.*;

public class RetrieveFirebase3 implements Runnable{
	
	private String cardV;
	private String cardExp;
	private String cardNum;
	private String userName= "Joenatan";
	private boolean matchStatus = false;
	FireBaseService2 fbs = null;
	
	public RetrieveFirebase3() {
		try {
            fbs = new FireBaseService2();
        } catch (IOException e) {
            e.printStackTrace();
        }


		
	}
	
	public String getCardExp() {
		return cardExp;
	}
	
	public String getCardV() {
		return cardV;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public boolean getMatchStatus() {
		return matchStatus;
	}
	

	
	
	
	public void setCredential(String cv, String ce, String cn) {
		cardV = cv;
		cardExp = ce;
		cardNum = cn;
		
	}
	
	public void setMatchStatus(boolean ms) {
		matchStatus = ms;	
	}
	
	public void setCredential(String cn) {
		cardNum = cn;	
	}
	
	
	
	
	public void run() {
        
    

      //  DatabaseReference ref = fbs.getDb()
       //         .getReference("/");
        
        //FirebaseDatabase database = FirebaseDatabase.getInstance();
		DatabaseReference userRef;
		DatabaseReference accRef = fbs.getDb().getReference("Accounts");
		userRef = accRef.child(userName);

		DatabaseReference buserRef;
		DatabaseReference bankRef = fbs.getDb().getReference("Bank");
		buserRef = bankRef.child(userName);
		//HashS h = new HashS();
		//HashB b = new HashB();
		
		 /*buserRef.addListenerForSingleValueEvent(new ValueEventListener() {
	     		//4234173647084180
	     	
	     	public void onDataChange(DataSnapshot dataSnapshot) {
	     		Object data1 = dataSnapshot.child("CVV").getValue();
	     		Object data2 = dataSnapshot.child("ExpDate").getValue();
	     		Object data3 = dataSnapshot.child("Number").getValue();
	     		
	  			setCredential(data1.toString(), data2.toString(), data3.toString());
	  			System.out.println("single value:" +  getCardNum());
	     						
	     	}

	     	public void onCancelled(DatabaseError error) {
	     		System.out.print("Error: " + error.getMessage());
	         }
	     	
	     	
	     });  */
		
		buserRef.addValueEventListener(new ValueEventListener() {
     		//4234173647084180
     	
     	public void onDataChange(DataSnapshot dataSnapshot) {
     		Object data1 = dataSnapshot.child("CVV").getValue();
     		Object data2 = dataSnapshot.child("ExpDate").getValue();
     		Object data3 = dataSnapshot.child("Number").getValue();
     		Object data4 = dataSnapshot.child("Verified").getValue();
     		
     		Object dataR1 = dataSnapshot.child("CVVReq").getValue();
     		Object dataR2 = dataSnapshot.child("ExpDateReq").getValue();
     		Object dataR3 = dataSnapshot.child("NumberReq").getValue();
     		DatabaseReference buserRef;
    		DatabaseReference bankRef = fbs.getDb().getReference("Bank");
    		buserRef = bankRef.child(userName);
    		
    		boolean veri = (Boolean) data4;
     		
     		if(data1.toString().equals(dataR1.toString()) && data2.toString().equals(dataR2.toString()) && data3.toString().equals(dataR3.toString()) )
     			buserRef.child("Verified").setValueAsync(true); //setMatchStatus(true);
     		else
     			buserRef.child("Verified").setValueAsync(false); //setMatchStatus(false);
     			
  			setCredential(data1.toString(), data2.toString(), data3.toString());
  			//System.out.println("single value:" +  getMatchStatus());
  			//System.out.println("single value:" +  getCardNum());
     						
     	}

     	public void onCancelled(DatabaseError error) {
     		System.out.print("Error: " + error.getMessage());
         }
     	
     	
     });  
		
		userRef.child("Credit Card").child("Token").addValueEventListener(new ValueEventListener() {
        	
        	public void onDataChange(DataSnapshot dataSnapshot) {
        		Object data1 = dataSnapshot.getValue();
        		DatabaseReference buserRef;
        		DatabaseReference bankRef = fbs.getDb().getReference("Bank");
        		buserRef = bankRef.child(userName);
        		buserRef.child("Vault").child("TokenRequest").setValueAsync(data1.toString());
        		
        	}
        	

        	public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
        	
        	
        }); 
		
       /* buserRef.child("Number").addValueEventListener(new ValueEventListener() {
        		//4234173647084180
        	
        	public void onDataChange(DataSnapshot dataSnapshot) {
        		Object data1 = dataSnapshot.getValue();
        		
     			System.out.println(data1.toString());
        		System.out.println("Requesting Tokenization Service...");
        		Token t = new Token();
        		t.genToken(data1.toString());
        		DatabaseReference buserRef;
        		DatabaseReference bankRef = fbs.getDb().getReference("Bank");
        		buserRef = bankRef.child(userName);
        		
        		buserRef.child("Vault").child("Token").setValueAsync(t.getGenTok());
        						
        	}

        	public void onCancelled(DatabaseError error) {
        		System.out.print("Error: " + error.getMessage());
            }
        	
        	
        });  */
		

		/*
		 buserRef.child("NumberReq").addValueEventListener(new ValueEventListener() {
     		//4234173647084180
     	
     	public void onDataChange(DataSnapshot dataSnapshot) {
     		Object data1 = dataSnapshot.getValue();
     		
     		System.out.println("value event listener:"+getCardNum());
  			System.out.println(data1.toString());
  				

     		if (data1.toString().equals(getCardNum())) {	
				System.out.println("Requesting Tokenization Service...");
				Token t = new Token();
				t.genToken(data1.toString());
				DatabaseReference buserRef;
				DatabaseReference bankRef = fbs.getDb().getReference("Bank");
				buserRef = bankRef.child(userName);
				buserRef.child("Vault").child("Token").setValueAsync(t.getGenTok());
			} else
				System.out.println("Invalid card");
     						
     	}

     	public void onCancelled(DatabaseError error) {
     		System.out.print("Error: " + error.getMessage());
         }
     	
     	
     });  
     */
		
		 buserRef.child("Verified").addValueEventListener(new ValueEventListener() {
	     		//4234173647084180
	     	
	     	public void onDataChange(DataSnapshot dataSnapshot) {
	     		Object data1 = dataSnapshot.getValue();
	     		boolean veri = (Boolean) data1;
	     		//System.out.println("value event listener:"+getCardNum());
	  			//System.out.println(data1.toString());
	  				

	     		if (veri) {	
					System.out.println("Requesting Tokenization Service...");
					Token t = new Token();
					t.genToken(getCardNum());
					DatabaseReference buserRef;
					DatabaseReference bankRef = fbs.getDb().getReference("Bank");
					buserRef = bankRef.child(userName);
					buserRef.child("Vault").child("Token").setValueAsync(t.getGenTok());
					
				} else
					System.out.println("");
	     						
	     	}

	     	public void onCancelled(DatabaseError error) {
	     		System.out.print("Error: " + error.getMessage());
	         }
	     	
	     	
	     });  
		 
 

        
        buserRef.child("Vault").addValueEventListener(new ValueEventListener() {
    	
    	
    	public void onDataChange(DataSnapshot dataSnapshot) {
    		Object data1 = dataSnapshot.child("Token").getValue();
    		Object data2 = dataSnapshot.child("TokenRequest").getValue();
    		
 			//System.out.println(data1.toString());
    		//System.out.println("Verifying token...");
    		
    		DatabaseReference userRef;
    		DatabaseReference accRef = fbs.getDb().getReference("Accounts");
    		userRef = accRef.child(userName);
    		
    		DatabaseReference buserRef;
			DatabaseReference bankRef = fbs.getDb().getReference("Bank");
			buserRef = bankRef.child(userName);
    		buserRef.child("NumberReq").setValueAsync("Cleared");
    		
    		if(data1.toString().equals(data2.toString()))
    			userRef.child("Credit Card").child("TokenApproval").setValueAsync(true);
    		else
    			userRef.child("Credit Card").child("TokenApproval").setValueAsync(false);
    			
    						
    	}

    	public void onCancelled(DatabaseError error) {
    		System.out.print("Error: " + error.getMessage());
        }
    	
    	
    });  
        
		
        
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
