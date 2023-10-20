package controller.Interfaces.AmountInterface;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public class AmountLessThanFilter implements AmountFilterStrategy {

    @Override
    public ArrayList<Integer> filter(List<Transaction> transactions, int threshold) {
        ArrayList<Integer> rowIndexes = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getAmount() <= threshold) {
                rowIndexes.add(i);
            }

        }
        return rowIndexes;
    }

}