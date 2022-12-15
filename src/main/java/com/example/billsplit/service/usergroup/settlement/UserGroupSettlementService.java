package com.example.billsplit.service.usergroup.settlement;

import com.example.billsplit.domain.Expense;
import com.example.billsplit.domain.UserBalance;
import com.example.billsplit.request.output.raw.usergroup.UserGroupSettlementOutput;
import com.example.billsplit.service.expense.Update.UpdateExpenseService;
import com.example.billsplit.service.expense.get.GetExpenseService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserGroupSettlementService {

    private final GetExpenseService getExpenseService;

    private final UpdateExpenseService updateExpenseService;

    public UserGroupSettlementService(GetExpenseService getExpenseService, UpdateExpenseService updateExpenseService) {
        this.getExpenseService = getExpenseService;
        this.updateExpenseService = updateExpenseService;
    }

    public List<UserGroupSettlementOutput> settle(Long userGroupID) {
        List<Expense> expenses = getExpenseService.getExpensesForUserGroup(userGroupID);
        Map<String, Double> userBalanceMap = new HashMap<>();

        for (Expense expense : expenses) {
            for (UserBalance userBalance : expense.getUserBalanceList()) {
                userBalanceMap.put(userBalance.getUser().getName(),
                        userBalanceMap.getOrDefault(userBalance.getUser().getName(), 0.0) +
                                userBalance.getBalance().getAmount());
            }
        }

        PriorityQueue<Map.Entry<String, Double>> positiveAmount = new PriorityQueue<>
                (Comparator.comparingDouble(balance -> -balance.getValue()));
        PriorityQueue<Map.Entry<String, Double>> negativeAmount = new PriorityQueue<>
                (Comparator.comparingDouble(balance -> balance.getValue()));

        for (Map.Entry<String, Double> e : userBalanceMap.entrySet()) {
            if (e.getValue() < 0) {
                negativeAmount.offer(e);
            } else {
                positiveAmount.offer(e);
            }
        }

        List<UserGroupSettlementOutput> userGroupSettlementOutputs = new ArrayList<>();

        while (!positiveAmount.isEmpty() && !negativeAmount.isEmpty()) {
            UserGroupSettlementOutput userGroupSettlementOutput = new UserGroupSettlementOutput();
            var largestPos = positiveAmount.poll();
            var smallestNeg = negativeAmount.poll();

            if (largestPos.getValue() - Math.abs(smallestNeg.getValue()) > 0) {
                userGroupSettlementOutput.setPayer(smallestNeg.getKey());
                userGroupSettlementOutput.setPayee(largestPos.getKey());
                userGroupSettlementOutput.setAmount(Math.abs(smallestNeg.getValue()));

                positiveAmount.add(Map.entry(largestPos.getKey(),
                        largestPos.getValue() - Math.abs(smallestNeg.getValue())));
            } else if (largestPos.getValue() - Math.abs(smallestNeg.getValue()) < 0) {
                userGroupSettlementOutput.setPayer(smallestNeg.getKey());
                userGroupSettlementOutput.setPayee(largestPos.getKey());
                userGroupSettlementOutput.setAmount(Math.abs(largestPos.getValue()));

                negativeAmount.add(Map.entry(smallestNeg.getKey(),
                        largestPos.getValue() - Math.abs(smallestNeg.getValue())));
            } else {
                userGroupSettlementOutput.setPayer(smallestNeg.getKey());
                userGroupSettlementOutput.setPayee(largestPos.getKey());
                userGroupSettlementOutput.setAmount(Math.abs(largestPos.getValue()));
            }

            userGroupSettlementOutputs.add(userGroupSettlementOutput);
        }
        updateExpenseService.settleExpenses(expenses);
        return userGroupSettlementOutputs;
    }
}
