package com.example.billsplit.service.expense.Update;

import com.example.billsplit.domain.Expense;
import com.example.billsplit.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UpdateExpenseService {

    private final ExpenseRepository expenseRepository;

    public UpdateExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void settleExpenses(List<Expense> expenseList) {

        for(Expense expense : expenseList) {
            expense.setSettled(true);
        }
        expenseRepository.saveAll(expenseList);
    }
}
