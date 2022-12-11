package com.example.billsplit.service.expense.validate;


import com.example.billsplit.domain.Expense;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetExpenseValidationService {

    private final ExpenseRepository expenseRepository;


    public GetExpenseValidationService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public void validateIfExpenseExists(Long expenseId) {
        Optional<Expense> expense = expenseRepository.findById(expenseId);
        if(!expense.isPresent()) {
            throw new BillSplitException("The expense with id " + expenseId + " doesn't exist.");
        }
    }
}
