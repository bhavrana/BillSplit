package com.example.billsplit.request.output.raw.expense;

import com.example.billsplit.domain.Balance;
import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor
public class ExpenseOutput {

    private Long Id;

    private String title;

    private Long timestamp;

    private Boolean settled;

    private String userGroup;

    private Map<String, Double> userBalanceMap = new HashMap<>();

    public ExpenseOutput(Long id, String title, Long timestamp, Boolean settled, String userGroup) {
        Id = id;
        this.title = title;
        this.timestamp = timestamp;
        this.settled = settled;
        this.userGroup = userGroup;
    }

    public void addUserBalance(final String user, final Double balance) {
        userBalanceMap.put(user, balance);
    }

}
