package in.bushansirgur.expensemanager;

import in.bushansirgur.expensemanager.model.Expense;
import in.bushansirgur.expensemanager.service.ExpenseService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class ExpenseManagerApplication {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        List<Expense> expenses = ExpenseService.loadExpenses();

        while (true) {
            System.out.println("\nExpense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. Delete Expense");
            System.out.println("4. Update Expense");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            Long expenseId = null;

            switch (choice) {
                case 1 -> {
                    try {
                        Expense newExpense = getInput(scanner);
                        Long timestamp = System.currentTimeMillis();
                        newExpense.setId(timestamp);
                        ExpenseService.saveExpenses(newExpense);
                    } catch (DateTimeParseException exception) {
                        System.out.println("Exception occured while parsing the date:" + exception.getMessage());
                        throw exception;
                    } catch (Exception ex) {
                        System.out.println("Exception occured: " + ex.getMessage());
                        throw ex;
                    }
                }
                case 2 -> printExpenses(expenses);
                case 3 -> {
                    try {
                        System.out.print("Enter the expense id you would like to delete: ");
                        expenseId = scanner.nextLong();
                        ExpenseService.checkExpenseId(expenseId);
                        boolean isDeleted = ExpenseService.deleteExpenseById(expenseId);
                        if (isDeleted)
                            System.out.println("Expense is deleted.");
                        else
                            System.out.println("No expense found.");
                    } catch (Exception ex) {
                        System.out.println("Exception occured: " + ex.getMessage());
                        throw ex;
                    }
                }
                case 4 -> {
                    try {
                        System.out.print("Enter the expense id you would like to update: ");
                        expenseId = scanner.nextLong();
                        ExpenseService.checkExpenseId(expenseId);
                        Expense updateExpense = getInput(scanner);
                        updateExpense.setId(expenseId);
                        List<Expense> updatedExpenses = ExpenseService.updateExpenseById(updateExpense);
                        System.out.println("Expense updated.");
                        printExpenses(updatedExpenses);
                    } catch (Exception ex) {
                        System.out.println("No expense found.");
                        throw ex;
                    }
                }
                case 5 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please select from the menu.");
            }
        }
    }

    private static void printExpenses(List<Expense> expenses) {
        for (Expense e : expenses) {
            System.out.println(e);
        }
    }

    private static Expense getInput(Scanner scanner) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String dateString = scanner.next();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String date = LocalDate.parse(dateString, formatter).toString();
        scanner.nextLine();

        System.out.print("Enter description: ");
        String desc = scanner.nextLine();

        System.out.print("Enter amount: ");
        double amount = scanner.nextDouble();

        Expense expense = new Expense();
        expense.setDate(date);
        expense.setAmount(amount);
        expense.setDescription(desc);
        return expense;
    }
}
