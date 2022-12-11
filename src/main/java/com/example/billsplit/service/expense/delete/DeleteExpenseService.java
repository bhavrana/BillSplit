package com.example.billsplit.service.expense.delete;

import com.example.billsplit.domain.Expense;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteExpenseService {
    private final ExpenseRepository expenseRepository;

    public DeleteExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void deleteExpenseByID(Long expenseID) {
        try {
            expenseRepository.deleteById(expenseID);
        } catch (RuntimeException ex) {
            throw new BillSplitException("Expetion : " + ex.getMessage(), ex);
        }
    }
}
