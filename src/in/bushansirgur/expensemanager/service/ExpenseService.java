package in.bushansirgur.expensemanager.service;

import in.bushansirgur.expensemanager.model.Expense;

import java.util.ArrayList;
import java.util.List;

public class ExpenseService {

    public static List<Expense> expensesList = new ArrayList<>();

    public static void saveExpenses(Expense expense) {
        expensesList.add(expense);
    }

    public static List<Expense> loadExpenses() {
        return expensesList;
    }

    public static boolean deleteExpenseById(Long id) {
        return expensesList.removeIf(expense -> expense.getId().equals(id));
    }

    public static List<Expense> updateExpenseById(Expense updatedExpense) {
        List<Expense> newExpenseList = expensesList.stream()
                .map(expense -> expense.getId().equals(updatedExpense.getId()) ? updatedExpense : expense)
                .toList();
        expensesList.clear();
        expensesList.addAll(newExpenseList);
        return expensesList;
    }

    public static void checkExpenseId(Long id) {
        if(expensesList.isEmpty() || expensesList.stream().noneMatch(e -> e.getId().equals(id))) {
            throw new RuntimeException("No expense found for the id: "+id);
        }
    }
}
