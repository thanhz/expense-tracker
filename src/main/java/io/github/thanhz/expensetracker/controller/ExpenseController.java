package io.github.thanhz.expensetracker.controller;

import io.github.thanhz.expensetracker.model.Expense;
import io.github.thanhz.expensetracker.model.ExpenseTypeCost;
import io.github.thanhz.expensetracker.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class ExpenseController {

    @Autowired
    ExpenseService expenseService;

    @GetMapping("/expense")
    public List<Expense> getExpenses() {
        return expenseService.getExpenses();
    }

    //TODO Add Some sort of check for last update so that the call doesn't do aggregation everytime(memoization)
    @GetMapping("/expense/total")
    public Double getTotalExpenses() {
        return expenseService.getTotalExpense();
    }

    @GetMapping("/expense/total/type")
    public List<ExpenseTypeCost> getTypeCost() {
        return expenseService.getTypeCost();
    }

    @PostMapping("/expense")
    public void addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
    }

    @PutMapping("/expense/{id}")
    public Expense updateExpense(@RequestBody Expense expense, @PathVariable int id) {
        return expenseService.updateExpense(expense, id);
    }

    @DeleteMapping("/expense/{id}")
    public void deleteExpense(@PathVariable int id) {
        expenseService.deleteExpense(id);
    }
}
