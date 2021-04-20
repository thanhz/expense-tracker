package io.github.thanhz.expensetracker.dao;

import io.github.thanhz.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Integer> {

}
