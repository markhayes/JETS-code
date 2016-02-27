package jets;
//------------------------------------------------------------------
// This Calendar class is used by a company for scheduling meetings,
// deadlines, deliveries, etc.  All functions will accept dates in
// a variety of formats ("December 25, 2002" or "25 Dec 02" or 
//                         "12/25/2002")
// but results are always returned in the format "dd MMM yyyy EEE",
// e.g.  "01 Jul 1998 Wed".  This is also accepted for parameters.
//------------------------------------------------------------------

//------------------------------------------------------------------
// By providing PUBLIC STATIC Methods, another class can use
// these methods without instantiating an object, providing similar
// functionality to traditional library procedures.  Reusability
// and reliability are improved by careful exception handling.
// In an exam question, only the method headers and comments need be
// provided; candidates do not need to know HOW the methods work.
//------------------------------------------------------------------
 
import java.util.*;
import java.text.*;
 
public class Calendar 
{
    private static final long ONE_DAY = (long)24*60*60*1000;
 
    private static final SimpleDateFormat DATE_FORMATTER =
            new SimpleDateFormat("dd MMM yyyy EEE");
 
    private static final String[] HOLIDAYS =
            {"01 Jan","01 Apr","01 May","23 Aug","25 Dec","xxxxxx"};

    //--------------------------------------------------------------
    // Determines the day of the week (Mon, Tue, Wed, ...) and
    // returns DATE in the standard format dd MMM yyyy EEE
    // For example, normalDate("4/1/2003") --> "01 Apr 2003 Tue"
    // Returns an empty string "" if DATE is not valid.
    //--------------------------------------------------------------
    public static String normalDate(String date)
    {
        try {
            //Date df = new Date(date);  deprecated
            Date df = DateFormat.getInstance().parse(date);
            return normalDate(df);
        }
        catch(ParseException e) {
            return "";
        }
    }
 
    private static String normalDate(Date df)
    {
        try {
            return DATE_FORMATTER.format(df);
        }
        catch(Exception e) {
            return "";
        }
    }
 
    //--------------------------------------------------------------
    // Calls NORMALDATE, to produce dd MMM yyyy EEE.  If WWW is
    // "Sat" or "Sun", the function returns 0 (false).
    // Otherwise, it consults a calendar file to check for
    // HOLIDAYS, returning 1 for a workday, 0 for a holiday or
    // weekend, and error code -1 if CHECK is not a valid Date.
    //--------------------------------------------------------------
    public static int isWorkDay(String check)
    {
        String d;

        try {
            d = normalDate(check);
        }
        catch (Exception e) {
            return -1;
        }
 
        String target = d.substring(0,6);
        String weekday = d.substring(12,15);
        int workday = 1;

        if (weekday.equals("Sat") || weekday.equals("Sun"))
        {
            workday = 0;
        }
        else
        {
            int c = 0;
            while (c < 5)
            {
                if (target.equals(HOLIDAYS[c]))
                {
                    workday = 0;
                }
                c = c + 1;
            }
        }
        return workday;
    }

    /**
     *  Accepts DATE in various formats, returns the next date in
     *  standard format dd MMM yyyy EEE. Returns an empty string
     *  if DATE is not valid (e.g. 1998.37.58)
     *  This correctly accounts for end of the month, end of year,
     *  leap years, etc. For example:
     *  NEXTDAY("28 Feb 1998 Sat") ----> "01 Mar 1998 Sun"
     *
     * @param date
     * @return An empty string if DATE is not valid.
     */
    public static String nextDay(String date)
    {
        Date dateObj;
        
        try {
            dateObj = DateFormat.getInstance().parse(date);
        }
        catch(ParseException e) {
            return("");
        }
        dateObj.setTime(dateObj.getTime() + ONE_DAY);
        return normalDate(dateObj);
        
        // return normalDate(new Date(new Date(date).getTime() + ONE_DAY));  deprecated
    }

    //--------------------------------------------------------------
    // Counts the number of days between two dates, including the
    // ends.  If FIRST is after SECOND, returns a negative number.
    // IF FIRST and SECOND are the same date, returns 1.
    // If FIRST or SECOND are not valid, returns error code 0
    //--------------------------------------------------------------
    public static int daysBetween(String first,String second)
    {
        try
        {
            // Date d1 = new Date(first);    deprecated
            // Date d2 = new Date(second);
            
            Date d1 = DateFormat.getInstance().parse(first);
            Date d2 = DateFormat.getInstance().parse(second);
            
            return (int)( (long)(d2.getTime() - d1.getTime()) / ONE_DAY);
        }
        catch(Exception exc)
        {
            return 0;
        }
    }
 
    //--------------------------------------------------------------
    // Returns today's date in standard format dd MMM yyyy EEE
    //--------------------------------------------------------------
    public static String today()
    {
        try
        {
            Date now = new Date();
            return(normalDate(now));
            
            // deprecated
            // return normalDate(new Date(now.getYear(),now.getMonth(),now.getDate()));
        }
        catch (Exception exc)
        {
            return "";
        }
    }
}
 
 