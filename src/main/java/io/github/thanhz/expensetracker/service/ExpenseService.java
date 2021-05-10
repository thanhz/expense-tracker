package io.github.thanhz.expensetracker.service;

import io.github.thanhz.expensetracker.dao.ExpenseRepository;
import io.github.thanhz.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getExpenses() {
        return expenseRepository.findAll();
    }

    public void addExpense(Expense expense) {
        expenseRepository.save(expense);
    }

    public double getTotalExpense() {
        return expenseRepository.getTotalExpense();
    }

    //Todo Maybe use .orElseThrow a custom exception
    public Expense updateExpense(Expense update, int id) {
        return expenseRepository.findById(id).map(
                expense -> {
                    expense.setExpense(expense, update);
                    return expenseRepository.save(expense);
                }).orElseGet(() -> expenseRepository.save(update));
    }

    public void deleteExpense(int id) {
        expenseRepository.deleteById(id);
    }
}
