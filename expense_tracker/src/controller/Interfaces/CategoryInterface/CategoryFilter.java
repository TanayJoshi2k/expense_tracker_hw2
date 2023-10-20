package controller.Interfaces.CategoryInterface;

import java.util.ArrayList;
import java.util.List;

import model.Transaction;

public class CategoryFilter implements CategoryFilterStrategy {

    String category;

    public CategoryFilter(String category) {
        this.category = category;
    }

    public String getCategory() {
        return this.category.toLowerCase();
    }

    @Override
    public ArrayList<Integer> filter(List<Transaction> transactions, String category) {
        ArrayList<Integer> rowIndexes = new ArrayList<>();
        for (int i = 0; i < transactions.size(); i++) {
            if (transactions.get(i).getCategory().toString().toLowerCase().equals(category)) {
                rowIndexes.add(i);
            }
        }
        System.out.println(rowIndexes);
        return rowIndexes;
    }
}
