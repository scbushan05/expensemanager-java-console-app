package in.bushansirgur.expensemanager.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Expense {
    private Long id;
    private String date;
    private String description;
    private double amount;

}
