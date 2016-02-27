
package jets;

public class Tracer {
    
    public static void main(String[] args) {
    
        System.out.println("Sum = " + sum(5));
    }
    
    public static int sum(int n) {
        
        System.out.println("sum(" + n + ")");
        if (n == 0) {
            System.out.println("n = 0; return from recursive calls.");
            return 0;
        } 
        else {
            System.out.println("going down");
            int result = n + sum(n - 1);    // recursive call
            System.out.println("sum(" + n + ") --> " + result);
            return result;
        }
    }
}
