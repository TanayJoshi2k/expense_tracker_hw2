package Interfaces.CategoryInterface;
import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public interface CategoryFilterStrategy {
    ArrayList<Integer> filter(List<Transaction> transactions, String category);
}