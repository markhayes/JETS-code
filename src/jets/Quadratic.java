//-------------------------------------------------------------
// QUADRATIC finds roots of a quadratic polynomial
//-------------------------------------------------------------
package jets;
import ibio.IBIO;

public class Quadratic
{
    public static void main(String[] args)
    { new Quadratic();
    }

    public Quadratic()
    {
        int a = IBIO.inputInt("A? ");
        int b = IBIO.inputInt("B? ");
        int c = IBIO.inputInt("C? ");

        if (isSolvable(a,b,c))
        {
            IBIO.output("x1 = " + bigRoot(a,b,c));
            IBIO.output("x2 = " + smallRoot(a,b,c));
        }
        else
        {
            IBIO.output("No roots");
        }
        IBIO.input("--- press [enter] ---");
    }
 
    boolean isSolvable(int a, int b, int c)
    {
        if ((a != 0) && (discriminant(a,b,c) < 0))
        { return false;
        }
        else
        { return true;
        }
    }
 
    double discriminant(int a, int b, int c)
    { return b*b - 4*a*c;
    }
 
    double smallRoot(int a, int b, int c)
    { return (-b - Math.pow(discriminant(a,b,c),0.5) ) / (2*a);
    }
 
    double bigRoot(int a, int b, int c)
    { return (-b + Math.pow(discriminant(a,b,c),0.5) ) / (2*a);
    }
}
 