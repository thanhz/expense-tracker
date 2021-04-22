package io.github.thanhz.expensetracker;

import io.github.thanhz.expensetracker.controller.ExpenseController;
import io.github.thanhz.expensetracker.model.Expense;
import io.github.thanhz.expensetracker.service.ExpenseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTestSpec {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExpenseService expenseService;

    List<Expense> expenseList;

    @BeforeEach
    public void setup() {
        Expense expense1 = new Expense("expense1", "test", 100.0);
        Expense expense2 = new Expense("expense1", "test", 200.0);
        expenseList = Arrays.asList(expense1, expense2);

        given(expenseService.getExpenses())
                .willReturn(expenseList);

        given(expenseService.getTotalExpense())
                .willReturn(300D);
    }

    @Test
    public void givenExpenses_whenGetExpenses_thenReturnJsonArray() throws Exception {
        mvc.perform(get("/expense")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(expenseList.get(0).getId())));
    }

    @Test
    public void givenExpenses_whenGetTotalExpenses_thenReturnTotalCost() throws Exception {
        mvc.perform(get("/expense/total")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(300D)));
    }
}
