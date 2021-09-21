package com.company;

import java.util.concurrent.TransferQueue;

public class BankAccount {
    private double balance;
    private float interestRate;

    public BankAccount(){
        interestRate = 0.02f;
    }
    public BankAccount(double initialBalance, float initialRate){
        balance=initialBalance;
        interestRate = initialRate;

    }

    public void deposit(double amount){
        balance += amount;

    }
    public boolean withdraw(double amount ){
        if(amount > balance){
            return false;
        }
        balance = balance - amount;
                return true;
    }
    public double addInterest(){
        balance += interestRate*balance;
                return balance;
    }
    public double checkBalance(){
        return balance;

    }
}
