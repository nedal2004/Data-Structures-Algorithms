import java.util.LinkedList;
import java.util.Scanner;

public class Problem2Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter the number of Operations On the Train:");
        int n = input.nextInt();
        LinkedList<String> train = new LinkedList<String>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter the operation  type ");
            String operation = input.next();

            if (operation.equalsIgnoreCase("FRONT")) {
                System.out.println("Enter the wagons name ");
                train.addFirst(input.next());
            } else if (operation.equalsIgnoreCase("BACK")) {
                System.out.println("Enter the wagons name ");
                train.addLast(input.next());
            } else if (operation.equalsIgnoreCase("REMOVE_FRONT")) {
                if (!train.isEmpty()) {
                    train.removeFirst();
                } else
                    System.out.println("No wagon to remove at front");

            } else if (operation.equalsIgnoreCase("REMOVE_LAST")) {
                if (!train.isEmpty()) {
                    train.removeLast();
                } else
                    System.out.println("No wagon to remove at last");

            }
        }
        System.out.println("The Train is :");
        for(String wagon :train){
            System.out.print( wagon +"-> ");
        }
        System.out.println("");
    }
}