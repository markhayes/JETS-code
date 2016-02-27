package jets;

import ibio.IBIO;

public class Hello { 
    public static void main(String[] args) { 
        new Hello();
    }
    public Hello() { 
        String name = IBIO.input("What is your name?");
        int age = IBIO.inputInt("How old are you?");
        int later = age + 7;
        double increase = (((double)later / age - 1) * 100);
        
        IBIO.output("Hello " + name);
        IBIO.output("In 2010, you will be " + later);
        IBIO.output("That is an increase of " + increase + "%");
        IBIO.input();                           // waiting for [enter] key
    }
}
