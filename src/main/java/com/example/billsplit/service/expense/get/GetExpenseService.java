package com.example.billsplit.service.expense.get;

import com.example.billsplit.domain.Expense;
import com.example.billsplit.domain.UserBalance;
import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.repository.ExpenseRepository;
import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetExpenseService {

    private final ExpenseRepository expenseRepository;

    public GetExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    public ExpenseOutput getExpenseForID(Long expenseID) {
        Expense expense = expenseRepository.findById(expenseID).get();
        ExpenseOutput expenseOutput = new ExpenseOutput(expense.getId(), expense.getTitle(), expense.getTimestamp(),
                expense.getSettled(), expense.getUserGroup().getTitle());

        for(UserBalance userBalance : expense.getUserBalanceList()) {
            expenseOutput.addUserBalance(userBalance.getUser().getName(), userBalance.getBalance().getAmount());
        }
        return expenseOutput;
    }

    public List<Expense> getExpensesForUserGroup(final Long userGroupID) {
        return expenseRepository.findExpenseByUserGroup_Id(userGroupID);
    }
}
