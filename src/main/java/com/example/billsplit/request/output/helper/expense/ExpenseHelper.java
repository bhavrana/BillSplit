package com.example.billsplit.request.output.helper.expense;

import com.example.billsplit.request.output.decorated.expense.ExpenseDecoratedOutput;
import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import org.springframework.stereotype.Component;

@Component
public class ExpenseHelper {

    public ExpenseDecoratedOutput addExpenseOutputDecorator(final ExpenseOutput expenseOutput) {
        ExpenseDecoratedOutput expenseDecoratedOutput = new ExpenseDecoratedOutput(expenseOutput);
        return expenseDecoratedOutput;
    }
}
