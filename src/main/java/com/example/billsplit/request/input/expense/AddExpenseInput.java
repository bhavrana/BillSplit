package com.example.billsplit.request.input.expense;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AddExpenseInput {

    private Long groupID;

    private Long userID;

    private Long paid;

    private Long currency;

    private String title;
}
