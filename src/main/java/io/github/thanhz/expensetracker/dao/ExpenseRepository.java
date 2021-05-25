package io.github.thanhz.expensetracker.dao;

import io.github.thanhz.expensetracker.model.Expense;
import io.github.thanhz.expensetracker.model.ExpenseTypeCost;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    @Query("SELECT SUM(e.cost) FROM Expense e")
    Double getTotalExpense();

    @Query("SELECT SUM(e.cost) as cost, e.type as type FROM Expense e GROUP BY e.type")
    List<ExpenseTypeCost> findTypeCost();
}
