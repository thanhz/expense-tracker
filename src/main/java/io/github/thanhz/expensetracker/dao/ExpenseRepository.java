package io.github.thanhz.expensetracker.dao;

import io.github.thanhz.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query("SELECT SUM(e.cost) FROM Expense e")
    Double getTotalExpense();
}
