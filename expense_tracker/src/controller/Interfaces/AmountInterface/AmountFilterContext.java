package controller.Interfaces.AmountInterface;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class AmountFilterContext {
    /** filterStrategy property - either AmountLessThanFilter or AmountMoreThanFilter object */
    private AmountFilterStrategy filterStrategy;

    /**
     * @param filterStrategy set filter strategy for a AmountFilterContext object
     */
    public void setFilterStrategy(AmountFilterStrategy filterStrategy) {
        this.filterStrategy = filterStrategy;
    }

    /**
     * @return filter strategy for a AmountFilterContext object
     */
    public AmountFilterStrategy getFilterStrategy() {
        return this.filterStrategy;
    }

    /**
     * 
     * @param transactions list of transactions to be filtered
     * @param value amount value for the filter 
     * @return filtered list of transactions
     */
    public ArrayList<Integer> applyFilter(List<Transaction> transactions, int value) {
        return filterStrategy.filter(transactions, value);
    }
}