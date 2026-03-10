
package models;
import java.util.LinkedList;

public class TransactionHistory {
   // String[] transaction;
   // int index;
   // int count;

   private int capacity;
   private LinkedList<String> transactionList;

   public TransactionHistory(int capacity) throws IllegalArgumentException {
       if (capacity <= 0) {
           throw new IllegalArgumentException("Capacity must be positive!");
       }

       this.capacity = capacity;
       transactionList = new LinkedList<>();

       // هذا الشرط كان في مكان خاطئ (القائمة فاضية هنا)
       // تم إبقاؤه كما هو لكن لن يؤثر
       if (this.transactionList.size() > capacity) {
           this.transactionList.removeLast();
       }

       // transaction = new String[capacity];
       // index = 0;
       // count = 0;
   }

   public void add(String description) {
       this.transactionList.addFirst(description);

       // تصحيح الخطأ الحقيقي: تطبيق capacity عند الإضافة
       if (this.transactionList.size() > capacity) {
           this.transactionList.removeLast();
       }

       // this.transaction[index] = description;
       // this.index = (index + 1) % this.transaction.length;
       // if (count < this.transaction.length) {
       //     count++;
       // }
   }

   public void printLast(int n) {
       if (this.transactionList.isEmpty()) {
           System.out.println("no transaction yet");
           return; // تصحيح: لازم نوقف هنا
       }

       if (n > this.transactionList.size()) {
           n = this.transactionList.size();
       }

       System.out.println("Last " + n + " transaction: ");
       int printedTransactionCount = 0;

       for (String transaction : this.transactionList) {
           System.out.println("_ " + transaction);
           printedTransactionCount++;
           if (printedTransactionCount == n) {
               break;
           }
      
       }

       /*
       System.out.println("Last " + n + "transaction: ");
       int printedTransaction = 0;
       int current = ((index - 1) + this.transaction.length) % this.transaction.length;
       while (printedTransaction < n) {
           System.out.println("_ " + this.transaction[current]);
           current = ((current - 1) + transaction.length) % this.transaction.length;
       }
       */
   }
}
