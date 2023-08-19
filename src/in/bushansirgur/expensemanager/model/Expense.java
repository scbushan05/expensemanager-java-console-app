package in.bushansirgur.expensemanager.model;

import in.bushansirgur.expensemanager.service.ExpenseService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private Long id;
    private String date;
    private String description;
    private double amount;

   public Expense(String date, String description, double amount) {
       this.date = date;
       this.description = description;
       this.amount = amount;
   }
}
