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
  private JComboBox<String> amountFilter;
  private JComboBox<String> categoryFilter;

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

  public void showError(String errorMessage) {
    JOptionPane.showMessageDialog(null, errorMessage);
  }

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

  public void removeTableRow(int selectedRow) {
    model.removeRow(selectedRow);
  }

  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }

  public JComboBox<String> getAmountFilterBox() {
    return amountFilter;
  }

  public JComboBox<String> getCategoryFilterBox() {
    return categoryFilter;
  }

  public DefaultTableModel getTableModel() {
    return model;
  }

  // Other view methods
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if (amountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountField.getText());
      return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }

  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
}