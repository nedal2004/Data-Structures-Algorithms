
import java.util.Scanner;

import models.Account;
import models.CheckingAccount;
import models.SavingAccount;
import lib.MyArrayList;

public class SimpleATMSystem {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        MyArrayList<Account> accounts = new MyArrayList<>();
        int count = 0;

        System.out.println("Welcome to the ATM System");

        while (true) {
            printMenu();
            int choice = input.nextInt();
            input.nextLine(); // تنظيف البافر

            switch (choice) {

                case 1:
                    Account newAccount = createAccount();
                    if (newAccount != null) {
                        // accounts[count++] = newAccount;
                        accounts.add(newAccount);
                        System.out.println("Account created successfully");
                    }
                    break;

                case 2:
                    depositToAccount(accounts);
                    break;

                case 3:
                    withdrawFromAccount(accounts);
                    break;

                case 4:
                    showLastNTransaction(accounts);
                    break;

                case 5:
                    showAccountSummary(accounts);
                    break;

                case 6:
                TransactionExplorer(accounts);
                    // transactionsBinaryTree.inOrderTraversal();
                case 0:
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Wrong Input");
            }
        }
    }

    // ================= MENU =================
    public static void printMenu() {
        System.out.println("\n1. Create new Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Show the last N Transactions");
        System.out.println("5. Show Account Summary");
        System.out.println("Show the transaction Explorer tree    ");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    // ================= CREATE ACCOUNT =================
    public static Account createAccount() {

        System.out.println("\n1. Saving Account");
        System.out.println("2. Checking Account");
        System.out.print("Choose account type: ");
        int type = input.nextInt();
        input.nextLine();

        System.out.print("Enter Account Number: ");
        String accountNumber = input.nextLine();

        System.out.print("Enter Owner Name: ");
        String ownerName = input.nextLine();

        System.out.print("Enter PIN: ");
        int pin = input.nextInt();

        System.out.print("Enter Initial Balance: ");
        double balance = input.nextDouble();
        input.nextLine(); // مهم

        if (type == 1) {
            System.out.print("Enter Interest Rate: ");
            double rate = input.nextDouble();
            input.nextLine();
            return new SavingAccount(accountNumber, ownerName, pin, balance, rate);

        } else if (type == 2) {
            System.out.print("Enter Transaction Fee: ");
            double fee = input.nextDouble();
            input.nextLine();
            return new CheckingAccount(accountNumber, ownerName, pin, balance, fee);

        } else {
            System.out.println("Invalid Account Type");
            return null;
        }
    }

    // ================= AUTH =================
    public static Account authAccount(MyArrayList<Account> accounts) {

        System.out.print("Enter Account Number: ");
        String accNum = input.nextLine();

        System.out.print("Enter PIN: ");
        int pin = input.nextInt();
        input.nextLine();

        for (Account acc : accounts) {
            if (acc == null)
                continue;

            if (acc.getAccountNumber().equals(accNum)) {
                if (acc.checkPIN(pin)) {
                    return acc;
                } else {
                    return null;
                }
            }
        }
        return null;
    }

    // ================= DEPOSIT =================
    public static void depositToAccount(MyArrayList<Account> accounts) {

        System.out.println("\n=== Deposit ===");
        Account acc = authAccount(accounts);

        if (acc == null) {
            System.out.println("Invalid Account or PIN");
            return;
        }

        System.out.print("Enter the deposit amount: ");
        double amount = input.nextDouble();
        input.nextLine();

        acc.deposit(amount);
        System.out.println("Deposit successful. Balance: " + acc.getBalance());
    }

    // ================= WITHDRAW =================
    public static void withdrawFromAccount(MyArrayList<Account> accounts) {

        System.out.println("\n=== Withdraw ===");
        Account acc = authAccount(accounts);

        if (acc == null) {
            System.out.println("Invalid Account or PIN");
            return;
        }

        System.out.print("Enter the withdrawal amount: ");
        double amount = input.nextDouble();
        input.nextLine();

        try {
            acc.withdraw(amount);
            System.out.println("Withdraw successful. Balance: " + acc.getBalance());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // ================= LAST N TRANSACTIONS =================
    public static void showLastNTransaction(MyArrayList<Account> accounts) {

        System.out.println("\n=== Last Transactions ===");
        Account acc = authAccount(accounts);

        if (acc == null) {
            System.out.println("Invalid Account or PIN");
            return;
        }

        System.out.print("Enter N: ");
        int n = input.nextInt();
        input.nextLine(); // ⭐ حل مشكلة اللوب

        acc.printLastNTransaction(n);
    }

    // ================= SUMMARY =================
    public static void showAccountSummary(MyArrayList<Account> accounts) {

        System.out.println("\n=== Account Summary ===");
        Account acc = authAccount(accounts);

        if (acc == null) {
            System.out.println("Invalid Account or PIN");
            return;
        }

        System.out.println("Account Number: " + acc.getAccountNumber());
        System.out.println("Owner Name: " + acc.getOwnerName());
        System.out.println("Type: " + acc.getAccountType());
        System.out.println("Balance: " + acc.getBalance());
    
    }
   

 public static void TransactionExplorer(MyArrayList<Account> accounts   ) {
  System.out.println("========Transactions Explorer============");
       System.out.println("\n=== Account Summary ===");
        Account acc = authAccount(accounts);

        if (acc == null) {
            System.out.println("Invalid Account or PIN");
            return;
        }
        System.out.println("Transaction Explorer Menu: ");
        //1- show all of the authenticated account transactions in order by amount
       System.out.println("1- show all of the authenticated account transactions in order by amount\r\n" );
        //2- show all the  transactions of authenticated account in range  in max and min amount
       System.out.println("2- show all the  transactions of authenticated account in range  in max and min amount");
        // 3- show transaction tree height
        System.out.println("3- show transaction tree height");
        // 4- Back
        System.out.println("4 - Show Break first travirsal ");
        System.out.println("0- Back");
        Scanner input = new Scanner(System.in);
        int choise = input.nextInt();
        input.nextLine(); // تنظيف البافر
        switch (choise) {
            case 1:
                System.out.println("Transactions in order by amount:");
               acc.getTransactionBinaryTree().inOrder();
                break;
             case 2:
                System.out.print("Enter the  min amount: ");
                double min = input.nextDouble();
                System.out.print("Enter max amount: ");
                double max = input.nextDouble();
                System.out.println("Transactions in range [" + min + ", " + max + "]:");
                MyArrayList transactionsInRange = acc.getTransactionBinaryTree().rangeSearch(min, max, acc.getAmountComparato());
                for (Object transaction : transactionsInRange) {
                    System.out.println(transaction);
                }
                break; 
        }

    }

}