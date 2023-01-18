package com.example.billsplit.service.expense.create;

import com.example.billsplit.domain.*;
import com.example.billsplit.repository.CurrencyRepository;
import com.example.billsplit.repository.ExpenseRepository;
import com.example.billsplit.repository.UserGroupRepository;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.input.expense.AddExpenseInput;
import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class CreateExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;
    private final CurrencyRepository currencyRepository;

    public CreateExpenseService(ExpenseRepository expenseRepository, UserGroupRepository userGroupRepository,
                                UserRepository userRepository, CurrencyRepository currencyRepository) {
        this.expenseRepository = expenseRepository;
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }

    @Transactional
    public ExpenseOutput createExpense(final AddExpenseInput addExpenseInput) {
        Expense expense = new Expense();
        expense.setTimestamp(System.currentTimeMillis());
        expense.setTitle(addExpenseInput.getTitle());
        expense.setSettled(false);

        UserGroup userGroup = userGroupRepository.findById(addExpenseInput.getGroupID()).get();

        expense.setUserGroup(userGroup);

        Uzer payee = userRepository.findById(addExpenseInput.getUserID()).get();

        Set<Uzer> groupUsers = userGroup.getUsers();

        Long amountPaid = addExpenseInput.getPaid();

        double sharedAmount = (double) amountPaid / groupUsers.size();

        List<UserBalance> userBalanceList = new ArrayList<>();

        for(Uzer uzer : groupUsers) {
            UserBalance userBalance = new UserBalance();

            Balance balance = new Balance();
            balance.setCurrency(currencyRepository.findById(addExpenseInput.getCurrency()).get());
            if(uzer.getId() == payee.getId()) {
                balance.setAmount(amountPaid - sharedAmount);
            } else {
                balance.setAmount(-sharedAmount);
            }

            userBalance.setBalance(balance);
            userBalance.setUser(uzer);
            userBalance.setExpense(expense);
            userBalanceList.add(userBalance);
        }

        expense.setUserBalanceList(userBalanceList);

        expenseRepository.save(expense);

        ExpenseOutput expenseOutput = new ExpenseOutput(expense.getId(), expense.getTitle(), expense.getTimestamp(),
                expense.getSettled(), userGroup.getTitle());

        for(UserBalance userBalance : expense.getUserBalanceList()) {
            expenseOutput.addUserBalance(userBalance.getUser().getName(), userBalance.getBalance().getAmount());
        }

        return expenseOutput;
    }
}
