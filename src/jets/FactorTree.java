//-------------------------------------------------------------------
//  FactorTree sample algorithm - generates a prime-factor tree
//  This algorithm is for HL candidates only, as binary trees 
//  do not appear in the SL syllabus.
//  Uses an inner class called Node.
//  For more info on inner classes:
//  http://www.javaworld.com/javaworld/javaqa/2000-03/02-qa-innerclass.html
//-------------------------------------------------------------------
package jets;
import ibio.IBIO;

public class FactorTree 
{
    public static void main(String[] args)
    { new FactorTree();
    }
 
    class Node                      // Use an "inner class" as a
    {
        int data;                     // Data-Structure similar to a
        Node leftChild;               // RECORD or STRUC in
        Node rightChild;              // traditional HL languages
    }
 
    public FactorTree()
    {
        int number;
        Node root = null;

        number = IBIO.inputInt("Type an integer:");
        if (number > 2)
        {
            root = makeTree(number);
            IBIO.output("The prime factors are");
            showFactors(root);
        }
        IBIO.output("-----------------");
        outline(root,"");
        IBIO.input("");
    }
 
    Node makeTree(int number)    // Recursively create factor tree
    {
        Node temp = new Node();    // creates a Node (allocates memory)
        temp.leftChild = null;
        temp.rightChild = null;
        temp.data = number;
        int count = 1;
        int fac = 0;

        while (count*count <= number)
        {
            if ( (number % count) == 0 )
            {
                fac = count; }
                count = count + 1;
            }
            if (fac > 1)
            {
                temp.leftChild = makeTree(fac);
                temp.rightChild = makeTree(number / fac);
            }
        return temp;
    }
 
    void showFactors(Node here)
    {
        if (here == null)
        {
            IBIO.output("null");
            return;
        }
        if ( (here.leftChild == null) && (here.rightChild == null))
        {
            IBIO.output(here.data);
        }
        else
        {
            showFactors(here.leftChild);
            showFactors(here.rightChild);
        }
    }
 
    void outline(Node here,String indent)   // Pre-order traversal prints
    {
        IBIO.output(indent + here.data);        // tree in "outline" format
        if (here.leftChild != null)
        {
            outline(here.leftChild, indent + "   ");
        }
        if (here.rightChild != null)
        {
            outline(here.rightChild, indent + "   ");
        }
    }
}