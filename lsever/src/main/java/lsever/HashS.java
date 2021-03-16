package lsever;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;

import org.bouncycastle.crypto.generators.SCrypt;

public class HashS {
	public String hashScrypt(String desiredText, int computeCost) {
		//Typically, compute cost = 8 
		int costPara = computeCost; //CPU difficulty factor
		int blockSize = computeCost; //Memory difficulty factor
		int parallelization = computeCost; //Parallel difficulty factor
		int passLength = desiredText.length(); //Output length
		
		//Generate salt with SecureRandom
		SecureRandom r = new SecureRandom();
		byte genSalt[] = r.generateSeed(8);
		String salt = Arrays.toString(genSalt);
		
		byte sCryptHashedByte[] = SCrypt.generate(desiredText.getBytes(), salt.getBytes(), costPara, blockSize, parallelization, passLength);
		
		String sCryptHashedString = new String(sCryptHashedByte, StandardCharsets.UTF_8);
		
		return sCryptHashedString;
	}
	
	public static void main(String args[]) {
		
		HashS h = new HashS();
		System.out.print(h.hashScrypt("password123", 8));
	}
}
