package controller;

import view.ExpenseTrackerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale.Category;

import javax.xml.crypto.dsig.spec.XPathType.Filter;

import Interfaces.AmountInterface.AmountFilterContext;
import Interfaces.CategoryInterface.CategoryFilter;
import model.ExpenseTrackerModel;
import model.Transaction;

public class ExpenseTrackerController {

  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

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

  public void deleteRow(int selectedRow) {
    view.removeTableRow(selectedRow);
    List<Transaction> transactions = model.getTransactions();
    Transaction t = transactions.get(selectedRow);
    model.removeTransaction(t);
    refresh();
  }

  // Other controller methods
}