//-----------------------------------------------------------------
// WORKDAYS sample algorithm - inputs two dates, counts the number
//   of workdays between the two dates, including the ends.
//   It uses the jets.Calendar class.
//-----------------------------------------------------------------
package jets;
import ibio.IBIO;

public class WorkDays
{
    public static void main(String[] args)
    {
        new WorkDays();
    }
 
    public WorkDays()
    {
        String first,last,temp,savedFirst;
        int between;
        IBIO.output( "This algorithm counts the workdays between two dates.");

        // Get the first date from user
        first = "";
        while (first.equals(""))        // bad date returns empty string
        {
            IBIO.output("Type in the first date:");   // Loop until good date
            first = IBIO.input();
            first = Calendar.normalDate(first);
        }

        // Get the second date from the user
        last = "";
        while (last.equals(""))         // bad date returns empty string
        {
            IBIO.output("Type in the last date");     // loops until good date
            last = IBIO.input();
            last = Calendar.normalDate(last);
        }

        // The second date should later than the first date.
        between = Calendar.daysBetween(first,last);
        if (between < 0)
        {
            temp = first;         // Swap FIRST and LAST
            first = last;
            last = temp;
        }
        savedFirst = first;     // save for output message

        // Calculate the number of days
        between = Calendar.isWorkDay(first);
        IBIO.output(first);
        while (!first.equals(last))  // Don't compare Strings with ==
        {
            first = Calendar.nextDay(first);
            between = between + Calendar.isWorkDay(first);
            IBIO.output(first);
        }
        IBIO.output(between + " workdays between " + savedFirst + " and " + last );
    }
}
 