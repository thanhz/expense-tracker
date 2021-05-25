package io.github.thanhz.expensetracker;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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
        Expense expense2 = new Expense("expense2", "test", 200.0);
        expense1.setId(1);
        expense2.setId(2);
        expenseList = Arrays.asList(expense1, expense2);
    }

    @Test
    public void givenExpenses_whenGetExpenses_thenReturnJsonArray() throws Exception {
        given(expenseService.getExpenses())
                .willReturn(expenseList);

        mvc.perform(get("/expense")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(expenseList.get(0).getId()));
    }

    @Test
    public void givenExpenses_whenGetTotalExpenses_thenReturnTotalCost() throws Exception {
        given(expenseService.getTotalExpense())
                .willReturn(expenseList.get(0).getCost() + expenseList.get(1).getCost());

        mvc.perform(get("/expense/total")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(300D));
    }

    //todo Find way to ignore expense parameter in expenseService.updateExpense(expense, id)
    @Test
    public void givenExpenses_whenUpdatingExpenses_thenReturnJson() throws Exception {
        Expense expense3 = new Expense("update", "test", 300.0);

        given(expenseService.updateExpense(expense3, 1))
                .willReturn(expense3);

        mvc.perform(put("/expense/{id}", 1)
                .content(asJsonString(expense3))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void givenExpenses_whenDeletingExpenses_thenReturnOk() throws Exception {
        mvc.perform(post("/expense/{id}", 2)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    public String asJsonString(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
