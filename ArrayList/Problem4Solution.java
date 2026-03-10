import java.util.Scanner;

public class Problem4Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the patients number(n) :");
        int n = input.nextInt();
        Queue<String> patientsQueue = new LinkedQueue<String>();
        for (int i = 0; i < n; i++) {
            System.out.println("Enter Patient " + (i + 1) + "name :");
            patientsQueue.enqueue(input.next());
        }
        System.out.println("Enter the number of treated patients (k) :");
        int k = input.nextInt();
        // Remove the treated Patients
        for(int i =0; i< k;i++){
            patientsQueue.dequeue();
        }
        // print al the remaining patients
        System.out.println("The remaining patients in the queue are :");
        for(String patient : patientsQueue){
            System.out.print( patient +" -> ");
        }
        System.out.println();
        System.out.println("the turn stop with "+ patientsQueue.first());
    }

}