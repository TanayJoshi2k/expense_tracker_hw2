package controller.Interfaces.AmountInterface;

import java.util.ArrayList;
import java.util.List;
import model.Transaction;

public interface AmountFilterStrategy {
  ArrayList<Integer> filter(List<Transaction> transactions, int threshold);
};