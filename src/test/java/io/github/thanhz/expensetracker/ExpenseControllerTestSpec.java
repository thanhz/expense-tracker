package io.github.thanhz.expensetracker;

import io.github.thanhz.expensetracker.controller.ExpenseController;
import io.github.thanhz.expensetracker.model.Expense;
import io.github.thanhz.expensetracker.service.ExpenseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExpenseController.class)
public class ExpenseControllerTestSpec {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ExpenseService expenseService;

    @Test
    public void givenExpenses_whenGetExpenses_thenReturnJsonArray()
            throws Exception {

        Expense expense1 = new Expense("expense1", "test", 100.0);
        Expense expense2 = new Expense("expense1", "test", 200.0);
        List<Expense> expenseList = Arrays.asList(expense1, expense2);

        given(expenseService.getExpenses()).willReturn(expenseList);

        mvc.perform(get("/expense")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", is(expense1.getId())));
    }
}
