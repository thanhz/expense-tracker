package io.github.thanhz.expensetracker.controller;

import io.github.thanhz.expensetracker.model.Expense;
import io.github.thanhz.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/expense")
    public List<Expense> getExpenses() {
        return expenseService.getExpenses();
    }

    @PostMapping("/expense")
    public void addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
    }
}
