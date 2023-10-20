package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private DefaultTableModel model;

  /** Amount Filter dropdown */
  private JComboBox<String> amountFilter;

  /** Category Filter dropdown */
  private JComboBox<String> categoryFilter;

  /**
   * Initializes the UI elements - JTable, button, amount and category filters, labels
   */
  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(600, 400); // Make GUI larger

    String[] columnNames = { "serial", "Amount", "Category", "Date" };
    String[] amountFilterOptions = { "", "Less than equal to 500", "More than 500" };
    String[] categoryFilterOptions = {"" ,"food", "travel", "bills", "entertainment", "other"};

    amountFilter = new JComboBox<>(amountFilterOptions);
    categoryFilter = new JComboBox<>(categoryFilterOptions);

    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);

    JLabel categoryLabel = new JLabel("Category:");
    JLabel amountFilterLabel = new JLabel("Filter By Amount:");
    JLabel categoryFilterLabel = new JLabel("Filter By Category:");
    categoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
    transactionsTable.setDefaultEditor(Object.class, null);

    // Layout components
    JPanel inputPanel = new JPanel();
    inputPanel.add(amountLabel);
    inputPanel.add(amountField);
    inputPanel.add(categoryLabel);
    inputPanel.add(categoryField);
    inputPanel.add(addTransactionBtn);
    inputPanel.add(amountFilterLabel);
    inputPanel.add(amountFilter);
    inputPanel.add(categoryFilterLabel);
    inputPanel.add(categoryFilter);

    JPanel buttonPanel = new JPanel();
    buttonPanel.add(addTransactionBtn);

    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    // Set frame properties
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
    
  }

  /**
   * @param rowIndexes - indexes of transactions table to be painted after filtering
   * This method paints the table rows if they match the filter condition
   */
  public void paintRows(List<Integer> rowIndexes) {
    transactionsTable.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
      @Override
      public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
          boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        if (!rowIndexes.isEmpty() && rowIndexes.contains(row)) {
          c.setBackground(new Color(173, 255, 168)); // Light green
        } else {
          c.setBackground(table.getBackground());
        }
        return c;
      }
    });
    transactionsTable.updateUI();
  }

  /**
   * This method pops up dialog box on either amount or category validation check fail
   * @param errorMessage - the error string to be displayed
   */
  public void showError(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage);
  }

  /**
   * @param transactions - list of transactions
   * This method refreshes the transactions Jtable to reflect the latest changes in the data model
   * It updates the row count, total amount.
   */
  public void refreshTable(List<Transaction> transactions) {
    // Clear existing rows
    model.setRowCount(0);
    // Get row count
    int rowNum = model.getRowCount();
    double totalCost = 0;
    // Calculate total cost
    for (Transaction t : transactions) {
      totalCost += t.getAmount();
    }
    // Add rows from transactions list
    for (Transaction t : transactions) {
      model.addRow(new Object[] { rowNum += 1, t.getAmount(), t.getCategory(), t.getTimestamp() });
    }
    // Add total row
    Object[] totalRow = { "Total", null, null, totalCost };
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();

  }

  /**
   * This method removes the selected row from the transactions table
   */
  public void removeTableRow(int selectedRow) {
    model.removeRow(selectedRow);
  }

  /**
   * @return add transaction button 
   */
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  /**
   * @return amount filter dropdown
   */
  public JComboBox<String> getAmountFilterBox() {
    return amountFilter;
  }

  /**
   * @return category filter dropdown
   */
  public JComboBox<String> getCategoryFilterBox() {
    return categoryFilter;
  }

  /**
   * @return table model
   */
  public DefaultTableModel getTableModel() {
    return model;
  }

  /**
   * @return transactions table
   */
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  /**
   * @return the amount value from a table row
   */
  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountField.getText());
      return amount;
    }
  }

  /**
   * This method sets the amount text field in the table for a transaction
   * @param amountField 
   */
  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  /**
   * @return the category field from a table row
   */
  public String getCategoryField() {
    return categoryField.getText();
  }

  /**
   * @param categoryField Sets the category text field of a transaction table row
   * This method sets the category field in the table for a transaction
   */
  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}