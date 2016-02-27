//-------------------------------------------------------------------
//  ENCRYPT - Encrypts a string by counting the length, adding that
//  number to the ASCII code of each CAPITAL letter.  Then the result
//  is printed backward.  Only CAPITAL LETTERS get changed.
//    "HOT2Day" --> adding 7 --> "OVA2Kay" --> "yaK2OVA"
//-------------------------------------------------------------------
package jets;
import ibio.IBIO;

public class Encrypt
{
    public static void main(String[] args)
    {
        new Encrypt();
    }
 
    public Encrypt()
    {
        String message, coded;

        IBIO.output("Type a message");
        message = IBIO.input();
        coded = encrypt(message);
        IBIO.output(reverse(coded));
        IBIO.input("---- press [enter] ----");
    }
 
    String encrypt(String message)     // Strings are immutable, so
    {
        int p,num;                     // use a StringBuffer to
        char codeChar;                 // allow changing single chars
        StringBuffer text = new StringBuffer(message);
    
        num = text.length();
        for(p = 0; p < num; p++)
        {
            codeChar = addCode( text.charAt(p), num );
            text.setCharAt(p,codeChar);
        }
        return text.toString();
    }
 
    char addCode(char letter,int change)
    {
        if ((letter >= 'A') && (letter <= 'Z'))   // chars behave like
        {
            char oldCode = (char)(letter - 'A') ;   // ints, arithmetic
                                              // can be performed
            char newCode = (char)((oldCode + change) % 26);

            return (char)('A' + newCode);    // (char) cast required to
        }                                  //  avoid warning messages
        else
        {
            return letter;
        }
    }
 
    String reverse(String message)
    {
        String backward = "";
        for (int c = message.length() - 1; c >= 0; c = c-1)
        {
            backward = backward + message.charAt(c);
        }
        return backward;
    }
}