package lk.kavee.expenseservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lk.kavee.expenseservice.entity.Expense;
import lk.kavee.expenseservice.service.ExpenseService;

import java.util.List;

@RestController
@RequestMapping("/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService service;

    @PostMapping
    public ResponseEntity<Expense> create(@RequestBody Expense expense) {
        return ResponseEntity.ok(service.createExpense(expense));
    }

    @GetMapping
    public ResponseEntity<List<Expense>> getAll() {
        return ResponseEntity.ok(service.getAllExpenses());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> update(
            @PathVariable("id") String id,
            @RequestBody Expense expense) {
        return ResponseEntity.ok(service.updateExpense(id, expense));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        service.deleteExpense(id);
        return ResponseEntity.ok().build();
    }
}