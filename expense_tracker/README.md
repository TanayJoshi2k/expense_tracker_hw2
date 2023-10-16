# hw1- Manual Review

The homework will be based on this project named "Expense Tracker",where users will be able to add/remove daily transaction. 

## Compile

To compile the code from terminal, use the following command:
```
cd src
javac ExpenseTrackerApp.java
java ExpenseTracker
```

You should be able to view the GUI of the project upon successful compilation. 

## Java Version
This code is compiled with ```openjdk 17.0.7 2023-04-18```. Please update your JDK accordingly if you face any incompatibility issue.

Modularity: Open-Closed Principle

1. Applied encapsulation for the list of transactions.

Changed Field Access Modifier of list of transactions from public to private in ExpenseTrackerModel.java file to disable direct access and also added final modifier. Adding final modifier ensures object cannot be modified after creation.

Removed setter methods from Transaction.java so as to prevent external modification of a transaction. Also, added final modifier to all properties - amount, category and timestamp to ensure immutability.