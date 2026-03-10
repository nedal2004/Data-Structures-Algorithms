import java.util.ArrayList;
import java.util.Scanner;
public class ProblemSolution {
public static void main(String[] args) {
  Scanner input = new Scanner(System.in);
  System.out.println("Enter the number of Students:");
  int n = input.nextInt();
  ArrayList<String> student = new ArrayList<>();
  if(n <= 0) {
    System.out.println("Invalid number of students.");
    return;
  }
  for(int i = 0 ; i < n;i++){
    System.out.println("Enter the name of student " + (i+1) + ":");
    student.add(input.next());
  }
    System.out.println("Students List is :");
  for(String stu :student){
    System.out.print( stu +" ");
  }
  System.out.println("");
  System.out.println("the first Student is : " + student.get(0));
  System.out.println("the Last Student is : " + student.get(student.size() - 1));


}}