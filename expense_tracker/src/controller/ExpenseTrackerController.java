package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;

import controller.Interfaces.AmountInterface.AmountFilterContext;
import controller.Interfaces.CategoryInterface.CategoryFilter;
import model.ExpenseTrackerModel;
import model.Transaction;

public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  /**
   * Constructor to initialize the model and the view
   * 
   * @param model - The transaction model
   * @param view  - The view
   * 
   */
  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  /**
   * This method refreshes the view to display the changes
   */
  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  /**
   * This method checks the validity of transaction amount
   * It pops up dialog box if validity check fails and doesn't paint transaction
   * table.
   * 
   * If amount is valid, it returns a new list of transactions filtered by the
   * amount
   * and paints the corresponding rows of the transactions table
   * 
   * @param filterContext - filterContext object either stores
   *                      MoreThanFilterStrategy or LessThanFilterStrategy
   * 
   */
  public void applyFilter(AmountFilterContext filterContext) {
    int threshold = 500;
    if (filterContext.getFilterStrategy() != null && InputValidation.isValidAmount(threshold)) {
      List<Transaction> transactions = model.getTransactions();
      List<Integer> rowIndexes = filterContext.applyFilter(transactions, threshold);
      view.paintRows(rowIndexes);
    } else {
      List<Integer> emptyList = new ArrayList<>();
      view.paintRows(emptyList);
      view.showError("Invalid Amount");
    }
  }

  /**
   * This method checks the validity of transaction category
   * It pops up dialog box if validity check fails and doesn't paint transaction
   * table.
   * 
   * If category is valid, it returns a new list of transactions filtered by the
   * category
   * and paints the corresponding rows of the transactions table
   * 
   * @param categoryFilter - the categoryFilter object
   */
  public void applyFilter(CategoryFilter categoryFilter) {
    String category = categoryFilter.getCategory();
    if (InputValidation.isValidCategory(category)) {
      List<Transaction> transactions = model.getTransactions();
      List<Integer> rowIndexes = categoryFilter.filter(transactions, category);
      view.paintRows(rowIndexes);
    } else {
      List<Integer> emptyList = new ArrayList<>();
      view.paintRows(emptyList);
      view.showError("Null or Invalid Category");
    }

  }

  /**
   * This method checks if a transaction is valid.
   * If valid, it adds a transaction to the model and the view, refreshes the view
   * 
   * @param amount   - amount field of a transaction
   * @param category - category field of a transaction
   * @return boolean - whether the transaction was added or not
   */
  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount) || !InputValidation.isValidCategory(category)) {
      return false;
    }

    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[] { t.getAmount(), t.getCategory(), t.getTimestamp() });
    refresh();
    return true;
  }

  /**
   * This method deletes a particular transaction from the model and the view,
   * refreshes the view
   * 
   * @param selectedRow
   */
  public void deleteRow(int selectedRow) {
    view.removeTableRow(selectedRow);
    List<Transaction> transactions = model.getTransactions();
    Transaction t = transactions.get(selectedRow);
    model.removeTransaction(t);
    refresh();
  }

  // Other controller methods
}