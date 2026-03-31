package lk.kavee.expenseservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import lk.kavee.expenseservice.entity.Expense;
import lk.kavee.expenseservice.repository.ExpenseRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository repository;

    public Expense createExpense(Expense expense) {
        return repository.save(expense);
    }

    public List<Expense> getAllExpenses() {
        return repository.findAll();
    }

    public Expense updateExpense(String id, Expense updatedExpense) {
        return repository.findById(id).map(expense -> {
            expense.setTitle(updatedExpense.getTitle());
            expense.setDescription(updatedExpense.getDescription());
            expense.setAmount(updatedExpense.getAmount());

            if (updatedExpense.getUsername() != null) {
                expense.setUsername(updatedExpense.getUsername());
            }

            return repository.save(expense);
        }).orElseThrow(() -> new RuntimeException("Expense not found with id: " + id));
    }

    public void deleteExpense(String id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.err.println("Error deleting expense: " + e.getMessage());
        }
    }
}