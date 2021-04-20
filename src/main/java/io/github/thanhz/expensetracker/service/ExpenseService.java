package io.github.thanhz.expensetracker.service;

import io.github.thanhz.expensetracker.dao.ExpenseRepository;
import io.github.thanhz.expensetracker.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "expenseService")
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getExpenses(){
        return expenseRepository.findAll();
    }

    public void addExpense(Expense expense){
        expenseRepository.save(expense);
    }
}
