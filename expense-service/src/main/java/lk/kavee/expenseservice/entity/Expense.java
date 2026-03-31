package lk.kavee.expenseservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "expenses")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {

    @Id
    private String id;

    private String title;

    private String description;

    private double amount;

    private String username;
}