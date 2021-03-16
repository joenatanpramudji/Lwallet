package lsever;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import com.google.firebase.database.*;

public class Test {
	
	public static void main (String args[]) throws FileNotFoundException {
		System.out.println("Test Retrieval");	
		Logger.getRootLogger().setLevel(Level.OFF);
		Thread t=new Thread(new RetrieveFirebase3());
        t.run();
        try {
            Thread.sleep(900000000); //Duration to run
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
		
}



