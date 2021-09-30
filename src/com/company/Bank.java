package com.company;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Bank {
    private ArrayList<BankAccount>allAccounts;
    private ArrayList<Customer>allCustomers;

    public Bank(){
        allAccounts = new ArrayList<BankAccount>();
        allCustomers = new ArrayList<Customer>();


    }
    public void doBanking(){
        var menuReader = new Scanner(System.in);
        while(true){
            printMenu();
            var userChoice = menuReader.nextInt();
            switch (userChoice){
                case 1:
                    System.exit(0);
                case 2:
                    addCustomer(menuReader);
                    break;
                case 3:
                    Optional<Customer> current = selectCustomer(menuReader);
                    if(current.isPresent())
                        doCustomerMenu(menuReader, current.get());
                    else
                        System.out.println("No customer exists with that ID");
                    break;
                default:
                    System.out.println("Please choose one of the menu options");
            }

        }

    }

    private void doCustomerMenu(Scanner menuReader, Customer currentCustomer) {

        while(true){
            printCustomerMenu();
            var customerChoice = menuReader.nextInt();
            switch (customerChoice){
                case 1:
                    openCustomerAccount(menuReader, currentCustomer);
                    break;
                case 2:
                    closeCustomerAccount(menuReader, currentCustomer);
                    break;
                case 3:
                    return;
                case 4:
                    doYearlyMaintenance();
                    break;
                default:
                    System.out.println("invalid Input");

            }
        }
    }

    private void doYearlyMaintenance() {
        //for each account - call addInterest and then print account info
        for (var currentAccount: allAccounts){
            currentAccount.addInterest();
            System.out.println("Account ID "+currentAccount.getAccountID()+" has a balance of "+currentAccount.checkBalance());

        }
    }

    private void closeCustomerAccount(Scanner menuReader, Customer currentCustomer) {
        //ask the user what account number to close
        System.out.println("What account number is being closed:");
        var accountNumber = menuReader.nextInt();
        // call close account on te customer passing that number
        Optional<BankAccount> accountToClose = currentCustomer.closeAccount(accountNumber);
        // if the close succeeded remove the account from all accounts
        if (accountToClose.isPresent()){
            allAccounts.remove(accountToClose.get());

        }
    }

    private void openCustomerAccount(Scanner menuReader, Customer currentCustomer){
        //Ask user how much money the initial deposit is
        System.out.println("Creating new account...");
        System.out.println("How much would you like the deposit?");
        var initialDeposit = menuReader.nextDouble();
        //call open account on customer
        var newAccount = currentCustomer.openAccount(initialDeposit);
        //add new account to all accounts
        allAccounts.add(newAccount);


    }
    private void printCustomerMenu(){
        System.out.println("**************************************");
        System.out.println("What do you want to do with this customer?");
        System.out.println("[1] Open an account");
        System.out.println("[2] Close an account");
        System.out.println("[3] Return to main menu");
        System.out.println("[4] Do the yearly maintenance and show all accounts");
        System.out.println("**************************");
        System.out.println("Enter Choice:");


    }

    private Optional<Customer> selectCustomer(Scanner reader) {
        System.out.println("Customer ID of customer to select");
        var idToFind = reader.nextInt();
        for (var currentCustomer: allCustomers){
            if(currentCustomer.getID() == idToFind)
                return Optional.of(currentCustomer);

        }
        return Optional.empty();

    }

    private void addCustomer(Scanner inputReader) {
        System.out.println("What is the new Customer's name:");
        inputReader.nextLine(); //eat the orphan newline from previous nextInt call
        var customerName = inputReader.nextLine();
        System.out.println("What is the new Customer's Tax ID(SSN):");
        var taxID = inputReader.nextInt();
        var newCustomer = new Customer(customerName, taxID);
        allCustomers.add(newCustomer);
    }


    private void printMenu() {
        System.out.println("*************************");
        System.out.println("What would you like to do next(select the number):");
        System.out.println("[1] Exit Program");
        System.out.println("[2] add customer");
        System.out.println("[3] Select a customer by ID");
        System.out.println("we'll add more soon");
        System.out.println("*****************************");
        System.out.println("Enter choice:");
    }
}
