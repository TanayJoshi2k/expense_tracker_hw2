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

2. Extensibility: Strategy Design Pattern

Added filter by amount or category feature.
Interfaces folder contains AmountInterface and CategoryInterface.

AmountInterface directory has AmountFilterStrategy interface which declares the filter method.
AmountLessThanFilter and AmountMoreThanFilter implements AmountFilterStrategy and overrides the filter method.

AmountFilterContext constructor has a property filterStrategy. The constructor expects an instance
of either AmountLessThanFilter or AmountMoreThanFilter.

The importance of AmountFilterContext is that it allows us to choose 
which instance's (AmountLessThanFilter or AmountMoreThanFilter) filter method to apply

Finally, to actually filter the list of transactions we call the applyFilter method of either object stored in the filterStrategy property.

Similar process is followed for filtering by Category.

CategoryFilterStrategy interface declares a filter method.
CategoryFilter implements this interface and defines the filter declared in the interface.

In the ExpenseTrackerApp.java, we create two mouseListeners - each for amount JComboBox and category JComboBox. When a user selects an option, the selected option is stored in a variable.

We create an object of AmountLessThanFilter, AmountMoreThanFilter and AmountFilterContext.

We apply switch statement on the selected option. 

If the user selects "Less than equal to 500":
We set filterStrategy to AmountLessThanFilter object.

If the user selects "More than 500":
We set filterStrategy to AmountMoreThanFilter object.

Lastly, we call applyFilter method of controller passing the filterContext as argument.
The applyFilter method of controller calls the filterContext.applyFilter() at the same time
paints the rows of the table with the paintRows method.


3. Usability: Undo Functionality

On double clicking a transactions table row, it must be deleted. 
To do this, we first get the transactions table with the getTransactionTable() method
defined in the ExpenseTrackerView.java and attach a mouse listener to this table using the addMouseListener() method.

This method accepts a MouseAdapter object as an argument. In the class definition of
MouseAdapter, we override the mouseClicked method. This method checks if any table row
was clicked twice. If yes, we get the clicked row with getSelectedRow() method of the
transactionsTable. We store the row in selectedRow.

We check if the selected row exists. The deleteRow(selectedRow) method 
defined in the controller i.e ExpenseTrackerController.java is called.

The deleteRow(selectedRow) calls the removeTableRow(selectedRow) method defined in the view. 
It simply removes the row from the table using model.removeRow(selectedRow)

Flow - 
controller.deleteRow(selectedRow) -> view.deleteRow(selectedRow) -> model.removeRow(selectedRow)
Clarification - model in the above flow is not the Data model. 
Here, model refers to the DefaultTableModel i.e. the transactionsTable.

In the controller.deleteRow(selectedRow), we fetch all transactions from the data model using 
the getter - model.getTransactions() and store them in a variable transactions. 
The selectedRow acts as an index to get the transaction to be removed - transactions[selectedRow]

The model.removeTransaction(trsansaction) removes the transaction 
from the list of transactions. Finally, we call the refresh() method to 
update the view to reflect the latest changes.

We are essentially updating the view. The view triggers the controller method.
The controller updates the model ie transactions. 
The controller refreshes the view to display the updated list of transactions