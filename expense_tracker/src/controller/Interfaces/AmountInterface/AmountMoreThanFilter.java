package controller.Interfaces.AmountInterface;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class AmountMoreThanFilter implements AmountFilterStrategy {

    /**
     * @param transactions list of transactions
     * @param threshold minimum allowed value for a transaction amount for it to pass the filter
     * @return list of indexes of transactions which pass the filter check 
     */
    @Override
    public ArrayList<Integer> filter(List<Transaction> transactions, int threshold) {
        ArrayList<Integer> rowIndexes = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() > threshold) {
                rowIndexes.add(i);
            }

        }
        return rowIndexes;
    }
}
