package lk.kavee.expenseservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import lk.kavee.expenseservice.entity.Expense;

public interface ExpenseRepository extends MongoRepository<Expense, String> {
}