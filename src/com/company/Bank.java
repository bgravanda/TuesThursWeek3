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
                        doCustomerMenu(menuReader);
                    else
                        System.out.println("No customer exists with that ID");
                    break;
                default:
                    System.out.println("Please choose one of the menu options");
            }

        }

    }

    private void doCustomerMenu(Scanner menuReader) {
        System.out.println("we will finish this");
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
        System.out.println("What would you like to do next(select the number):");
        System.out.println("[1] Exit Program");
        System.out.println("[2] add customer");
        System.out.println("[3] Select a customer by ID");
        System.out.println("we'll add more soon");
        System.out.println("Enter choice:");
    }
}
