package com.company;

public class Main {

    public static void main(String[] args) {
	    var myAccounts = new BankAccount();
        var yourAccount = new BankAccount(2000,0.05f);

        myAccounts.deposit(1000);
        var newBalance = myAccounts.addInterest();
        var yourBalance = yourAccount.addInterest();
        System.out.println("your account has "+ yourBalance + "after adding the interest.");


        var succeeded = myAccounts.withdraw(2000);
        if(succeeded)
            System.out.println("you managed to withdraw successfully");
        else
            System.out.println("you tried to withdraw money from your account. Your balance is " + myAccounts.checkBalance());
    }
}
