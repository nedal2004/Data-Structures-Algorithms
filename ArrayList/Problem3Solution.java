
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Stack;
public class Problem3Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the number of visit");
        int n = input.nextInt();
        Stack<String> browserHistory = new Stack<String>();
        for(int i = 0 ; i < n ; i++){
            System.out.println("Page "+ (i+1)+ "  :");
            browserHistory.push(input.next());


        }
        System.out.println("Enter the number of back bottons pressed:");
        int k = input.nextInt();
        for(int i=0 ; i <k ;i++){
            browserHistory.pop();

        }
        System.out.println("The current Page is :  "+ browserHistory.peek());
    }
}