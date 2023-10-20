package controller.Interfaces.AmountInterface;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class AmountFilterContext {
    private AmountFilterStrategy filterStrategy;

    public void setFilterStrategy(AmountFilterStrategy filterStrategy) {
        this.filterStrategy = filterStrategy;
    }

    public AmountFilterStrategy getFilterStrategy() {
        return this.filterStrategy;
    }

    public ArrayList<Integer> applyFilter(List<Transaction> transactions, int value) {
        return filterStrategy.filter(transactions, value);
    }
}