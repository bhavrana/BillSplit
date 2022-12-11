package com.example.billsplit.request.output.decorated.expense;

import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExpenseDecoratedOutput {

    @JsonProperty("Expense")
    ExpenseOutput expenseOutput;
}
