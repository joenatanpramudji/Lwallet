package lsever;
import java.util.Random;
import java.util.concurrent.TimeUnit;
public class Token
{
    private String cardNum;
    private String genTok="";
    private int tok [];
    public Token()
    {
        LuhnTest l = new LuhnTest();
        //cardNum = l.getCard();
        tok = new int [6];
        //final int length = cardNum.length();
        //Token token = new Token(length);
        
    }
    
    public String getGenTok() {
    	return genTok;
    }
    
    public void genToken(String cNum){
        Random r = new Random();
        int x;
        int y;
        
        for(int i = 0; i <= 5; i++){
            x = r.nextInt(10); //From 0..9
            y = r.nextInt(10);
            int digit = Integer.parseInt(cNum.substring(i, i+1));
            //System.out.println("Digit: "+ digit);
            if(digit == 0)
                digit +=1;
            tok[i] = (x*digit + y)% digit;
        }
        System.out.println("Generating token...");
        System.out.print("Token: ");
        for(int i = 0; i <= 5; i++){
            System.out.print(tok[i]);
            genTok += String.valueOf(tok[i]);
        }
          System.out.println();
        //System.out.println("\nGenTok: "+ genTok);
    }
    
    public static void main(String args[]){
    	long startTime = System.currentTimeMillis();
        Token t = new Token();
        System.out.println("Card number: "+t.cardNum);
        //t.genToken(t.cardNum);
        t.genToken("4234173647084180");
        long endTime = System.currentTimeMillis();
        long timeTaken = endTime - startTime;
        System.out.println("Time taken: "+timeTaken+"ms");
        /*
        for(int i = 0; i < 20; i++)
            t.genToken(t.cardNum);
        */
        
    }
}
