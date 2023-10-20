import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;

import controller.Interfaces.AmountInterface.*;
import controller.Interfaces.CategoryInterface.*;
import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;

public class ExpenseTrackerApp {

  public static void main(String[] args) {

    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
      // Get transaction data from view
      double amount = view.getAmountField();
      String category = view.getCategoryField();

      // Call controller to add transaction
      boolean added = controller.addTransaction(amount, category);

      if (!added) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });


    view.getTransactionsTable().addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() >= 2) { // Check for a double-click
          int selectedRow = view.getTransactionsTable().getSelectedRow();
          if (selectedRow != -1) {
            controller.deleteRow(selectedRow);
          }
        }
      }
    });

    JComboBox<String> amountFilterBox = view.getAmountFilterBox();
    ActionListener amountChangeActionListener = new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedAmountOption = (String) amountFilterBox.getSelectedItem();
        AmountFilterStrategy lessthanFilterStrategy = new AmountLessThanFilter();
        AmountFilterStrategy morethanFilterStrategy = new AmountMoreThanFilter();

        // Creating an instance of FilterContext
        AmountFilterContext filterContext = new AmountFilterContext();

        switch (selectedAmountOption) {
          case "Less than equal to 500":
            filterContext.setFilterStrategy(lessthanFilterStrategy);
            break;

          case "More than 500":
            filterContext.setFilterStrategy(morethanFilterStrategy);
            break;

          default:
            break;
        }
        controller.applyFilter(filterContext);
      }
    };
    amountFilterBox.addActionListener(amountChangeActionListener);

    JComboBox<String> categoryFilterBox = view.getCategoryFilterBox();

    ActionListener categoryChangeActionListener = new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String selectedCategoryOption = (String) categoryFilterBox.getSelectedItem();
        // Creating an instance of FilterContext
        CategoryFilter categoryFilter = new CategoryFilter(selectedCategoryOption);
        controller.applyFilter(categoryFilter);
      }
    };
    categoryFilterBox.addActionListener(categoryChangeActionListener);
  }

}