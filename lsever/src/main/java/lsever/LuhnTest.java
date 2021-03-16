package lsever;


public class LuhnTest
{
    private static String cardNum;
    private static String expiryDate;
    private static String cardVerification;
    public LuhnTest()
    {
        // Initialize instance variables
        //setCardDetails("4234173647084180", "03/20", "737"); //valid
        setCardDetails("488438843884305", "03/20", "737"); //invalid
    }

    public void setCardDetails(String cNum, String eDate, String cVerification){
        cardNum = cNum;
        expiryDate = eDate;
        cardVerification = cVerification;
    }
    
    public void setCardDetails(String cNum){
        cardNum = cNum;
    }
    
    public static boolean validateCard(String cardNum){
        int sum = 0;
        boolean second = false;
        boolean result = false;
        for(int i = cardNum.length() - 1; i >= 0; i--){
         int digit = Integer.parseInt(cardNum.substring(i, i+1));
         if(second){
            digit *= 2;
            if(digit > 9)
                digit = (digit % 10) + 1; // Example, 10 mod 10 to become 1
         }
         sum += digit;
         second = !second;
        }
        if(sum % 10 == 0)
            result = true;
        return result;
    }
    
    public String getCard(){
        return cardNum;
    }
    
    public static void main (String args[]){
        LuhnTest l = new LuhnTest();
        if(l.validateCard(cardNum))
            System.out.println("Card: "+cardNum+" is a valid card");
        else
            System.out.println("Card: "+cardNum+" is an invalid card");
    }
}
