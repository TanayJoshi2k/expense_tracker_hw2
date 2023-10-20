package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {

  /**
   * amount field to store the transaction amount
   */
  private final double amount;
  
  /**
   * category field to store the transaction category
   */
  private final String category;

  /**
   * category field to store the transaction time
   */
  private final String timestamp;

  /**
   * @param amount - set the amount field of a transaction 
   * @param category - set the category field of a transaction 
   */
  public Transaction(double amount, String category) {
    this.amount = amount;
    this.category = category;
    this.timestamp = generateTimestamp();
  }

  /**
   * This method returns the transaction amount
   * @return transaction amount
   */
  public double getAmount() {
    return amount;
  }

  /**
   * This method returns the transaction category
   * @return transaction category
   */
  public String getCategory() {
    return category;
  }
  
  /**
   * This method returns the transaction timestamp
   * @return transaction timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * This method generates timestamp of a transaction
   * @return date in simple date format
   */
  private String generateTimestamp() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");  
    return sdf.format(new Date());
  }

}