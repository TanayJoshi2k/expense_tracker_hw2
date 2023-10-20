package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerModel {

  /**
   * A list of transactions to hold transaction objects
   */
  private final List<Transaction> transactions;

  /**
   * Initialize the list of transactions
   */
  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  /**
   * This method adds a transaction to the list of transactions
   * @param t Transaction object
   */
  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  /**
   * This method removes a transaction from the list of transactions
   * @param t Transaction object
   */
  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  /**
   * This method returns the list of transactions
   * @return list of transactions
   */
  public List<Transaction> getTransactions() {
    return transactions;
  }

}