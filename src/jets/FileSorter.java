//------------------------------------------------------------------
// FileSorter demonstrates using RandomAccessFile to store "records".
// Java does not contain a specific construct like STRUC or RECORD.
// An "inner class" can be used for this purpose.  There is no
// command available to read or write "records" to random access
// files, so these must be programmed, writing one field at a time.
// For more info on inner classes:
// http://www.javaworld.com/javaworld/javaqa/2000-03/02-qa-innerclass.html
//------------------------------------------------------------------
package jets;
import java.io.*;   // contains all file-oriented classes and methods
import ibio.IBIO;
 
public class FileSorter
{
    public static void main(String[] args) throws IOException
    {
        new FileSorter();
    }
 
    public FileSorter() throws IOException
    {
        RandomAccessFile ranFile = new RandomAccessFile("Items.dat","rw");
        create(ranFile);
        System.out.println("--- Records before sorting ---");
        display(ranFile);
        sort(ranFile);
        System.out.println("--- Records after sorting ---");
        display(ranFile);
        ranFile.close();
    }
 
    class Item   //----- inner class simulates "records" -----------
    {
        int id;                  // Item class contains 3 data fields
        String name;             //  which will be written into and
        double price;            //  read from the random access file

        final static int NAMELENGTH = 20;
        final static int RECORDSIZE = NAMELENGTH*2 + 12;
                         // constants used to calculate SEEK values

        //-----------------------------------------------------------
        // Reads one record from ranFile, which must already be open
        // Reads each field - id, price, name; use TRIM to remove
        // padding spaces. IOExceptions are detected and reported
        //-----------------------------------------------------------
        void readFromFile(RandomAccessFile ranFile, long recordNum)
        {
            try
            {
                ranFile.seek( recordNum * RECORDSIZE);
                id = ranFile.readInt();
                price = ranFile.readDouble();

                StringBuffer nameBuffer = new StringBuffer(Item.NAMELENGTH);
                nameBuffer.setLength(NAMELENGTH);

                for (int c = 0; c < NAMELENGTH; c++)
                {
                    nameBuffer.setCharAt(c, ranFile.readChar());
                }
                name = nameBuffer.toString().trim();
            }
            catch(IOException exc)
            {
                System.out.println("While reading record # " + recordNum);
                System.out.println(exc.toString());
            }
        }

        //-----------------------------------------------------------
        // Writes one record into ranFile, which must already be open
        // IOExceptions are detected and reported
        //-----------------------------------------------------------
        void writeToFile(RandomAccessFile ranFile, long recordNum)
        {
            try
            {
                ranFile.seek( recordNum * RECORDSIZE);
                ranFile.writeInt(id);
                ranFile.writeDouble(price);
                ranFile.writeChars(setLength(name,NAMELENGTH));
            }
            catch(IOException exc)
            {
                System.out.println("While writing " + exc.toString());
            }
        }

        //-----------------------------------------------------------
        // Forces length of string to a specific value
        // Necessary before writing into a random-access file
        //-----------------------------------------------------------
        String setLength(String s,int len)
        {
            StringBuffer sb = new StringBuffer(s);
            sb.setLength(len);
            return sb.toString();
        }
    }  //---- end of Item class -----------------

    //-----------------------------------------------------------------
    // Puts records into ranFile, which must already be open
    //-----------------------------------------------------------------
    void create(RandomAccessFile ranFile) throws IOException
    {
        Item thisRec = new Item();

        for (int c=0; c < 2; c++)
        {
            thisRec.id = IBIO.inputInt();
            thisRec.name = IBIO.input();
            thisRec.price = IBIO.inputDouble();
            thisRec.writeToFile(ranFile,c);
        }
    }

    //-----------------------------------------------------------------
    // Reads all records from ranFile and prints the fields
    //-----------------------------------------------------------------
    void display(RandomAccessFile ranFile)
    {
        try
        {
            long recordCount = ranFile.length() / Item.RECORDSIZE;
            Item thisRec = new Item();

            for (int c=0; c < recordCount; c++)
            {
                thisRec.readFromFile(ranFile, c);
                System.out.println(thisRec.id + ":" + thisRec.name
                            + "=" + thisRec.price);      
            }
        }
        catch (IOException exc)
        {
            System.out.println(exc.toString());
        }
    }

    //-----------------------------------------------------------------
    // Bubble sort ranFile, sorting name fields in ascending order
    //-----------------------------------------------------------------
    void sort(RandomAccessFile ranFile)
    {
        try
        {
            long recordCount = ranFile.length() / Item.RECORDSIZE;
            Item thisRec = new Item();
            Item nextRec = new Item();

            for (int pass = 0; pass < recordCount; pass++)
            {
                for (int pos = 0; pos < recordCount-1; pos++)
                {
                    thisRec.readFromFile(ranFile,pos);
                    nextRec.readFromFile(ranFile,pos+1);
                    if (thisRec.name.compareTo(nextRec.name)>0)
                    {
                        nextRec.writeToFile(ranFile,pos);
                        thisRec.writeToFile(ranFile,pos+1);
                    }
                }
            }
        }
        catch (IOException exc)
        {
            System.out.println(exc.toString());
        }
    } 
 }
