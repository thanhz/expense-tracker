package io.github.thanhz.expensetracker;

import io.github.thanhz.expensetracker.model.Expense;
import io.github.thanhz.expensetracker.model.ExpenseTypeCost;
import io.github.thanhz.expensetracker.service.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class ExpenseTrackerApplication {

	private static final Logger logger = LoggerFactory.getLogger(ExpenseTrackerApplication.class);
	@Autowired
	private ExpenseService repository;

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			logger.info("ADDING SOME EXPENSES");
			repository.addExpense(new Expense("Stocks","Investment",100.0));
			repository.addExpense(new Expense("Stocks","Investment",222.0));
			repository.addExpense(new Expense("Food","General",200.0));

			logger.info("ALL EXPENSES");
			logger.info(repository.getExpenses().toString());

			logger.info("TOTAL EXPENSE: " + repository.getTotalExpense());
			List<ExpenseTypeCost> typeCost = repository.getTypeCost();
			typeCost.forEach(type -> logger.info(type.getType() + ":" + type.getCost()));
		};
	}

}
