//-------------------------------------------------------------------
//	NameSaver sample algorithm - input a list of names into an array.
//	  "XXX" ends the input, then the list is stored in a sequential file.
//	This class does not attempt to handle the possible IOExceptions,
//	  (e.g. locked file or full disk drive) but simple "throws" them.
//-------------------------------------------------------------------
package jets; 
import java.io.*;
import ibio.IBIO;
 
public class NameSaver
{
    public static void main(String[] args) throws IOException
    {
        new NameSaver();
    }
 
    String names[] = new String[1000];
    int namesCount = 0;
 
    public NameSaver() throws IOException
    {
        inputNames();
        saveNames();
    }
 
    void inputNames()
    {
        String thisName = "";
        namesCount = 0;
        do
        {
            IBIO.output("Type a name");
            thisName = IBIO.input();
            if (!thisName.equals("XXX"))
            {
                names[namesCount] = thisName;
                namesCount = namesCount + 1;
            }
        } while (!thisName.equals("XXX") && (namesCount < 1000));
    }
 
    void saveNames() throws IOException
    {
        PrintWriter outFile = new PrintWriter(new FileWriter("namelist.txt"));
        for (int c = 0; c < namesCount; c++)
        {
            outFile.println(names[c]);
        }
        outFile.close();
    }
 }
