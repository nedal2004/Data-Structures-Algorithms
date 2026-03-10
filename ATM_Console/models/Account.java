
package models;

import lib.BinaryTree;
import models.comparators.AmountComparator;
public abstract class Account {
    String accountNumber;
    String ownerName;
    int pin;
    protected double balance;
    private TransactionHistory history;

BinaryTree<TransactionsEntry> transactionBinaryTree = new BinaryTree<>(); 
AmountComparator amountComparato = new AmountComparator();  
     public boolean checkPIN(int inputPIN) {
        return this.pin == inputPIN;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive!");
        }
        this.balance = this.balance + amount;
        this.history.add("Deposit " + amount + "| new Balance " + this.balance);
       String msg = ("Deposit " + amount + "| new Balance " + this.balance);
        // add to transaction tree
        this.transactionBinaryTree.add
        (new  TransactionsEntry(amount, msg), this.amountComparato);
    }

    public abstract void withdraw(double amount) throws  InsufficientFundsException;

    protected void record(double amount , String message) {
        this.history.add(message);
        this.transactionBinaryTree.add
        (new  TransactionsEntry(amount, message), this.amountComparato);
    }
    public double getBalance() {
        return balance;
    }

    public TransactionHistory getHistory() {
        return history;
    }

    public BinaryTree<TransactionsEntry> getTransactionBinaryTree() {
        return transactionBinaryTree;
    }
    

    public AmountComparator getAmountComparato() {
        return amountComparato;
    }

    public void printLastNTransaction(int n) {
        this.history.printLast(n);
    }
    public String getAccountType(){
        return this.getClass().getSimpleName();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getPin() {
        return pin;
    }

  

    
    public Account(String accountNumber, String ownerName,int pin, double initialBalance) throws IllegalArgumentException {
        if (initialBalance <= 0) {
            throw new IllegalArgumentException("balance must be positive !");
        }
        this.balance = initialBalance;
        this.pin = pin;
        this.ownerName = ownerName;
        this.accountNumber = accountNumber;
        this.history = new TransactionHistory(20);
        this.history.add("Account Is created with balance " + this.balance);

    }
   
}

