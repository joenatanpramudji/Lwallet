package lsever;
import org.mindrot.jbcrypt.*;
import java.util.concurrent.TimeUnit;

public class HashB {
    private String bcryptHashedString;

    public String bcryptHash(String desiredString) {
        //BCrypt hash the desiredString
        //May modify from 10-12 (Hashing performance differs with different log_rounds)
        bcryptHashedString = BCrypt.hashpw(desiredString, BCrypt.gensalt(13));
        return bcryptHashedString;
    }

    public boolean verifyHash(String desiredString, String hashedString){
        boolean result;
        //Cross-check a plain-text string from user input, "desiredString" with the hashed string from DB, hashedString
        if(BCrypt.checkpw(desiredString, hashedString))
            result = true;
        else
            result = false;
        return result;
    }

    public String getBcryptHashedString(){
        return bcryptHashedString;
    }
    
    public static void main (String args[]) {
    	long startTime = System.currentTimeMillis();
    	HashB h = new HashB();
    	String testHashP = h.bcryptHash("password123");
    	long endTime = System.currentTimeMillis();
    	long timeTaken = endTime - startTime;
    	System.out.println(testHashP + " Time taken is: "+timeTaken+"ms");
    	
    	long startTime2 = System.currentTimeMillis();
    	boolean passMatch = BCrypt.checkpw("password123", testHashP);
    	long endTime2 = System.currentTimeMillis();
    	long timeTaken2 = endTime2 - startTime2;
    	//boolean passMatch2 = BCrypt.checkpw("password123", h.bcryptHash("password123"));
    	//System.out.print("Verify password:" + h.verifyHash("password123",testHashP));
    	System.out.print("Verify password:" + passMatch + " Time Taken: "+ timeTaken2+"ms");
    	
    	
    }
}


