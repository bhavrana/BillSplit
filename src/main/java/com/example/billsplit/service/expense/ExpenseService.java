package com.example.billsplit.service.expense;

import com.example.billsplit.request.input.expense.AddExpenseInput;
import com.example.billsplit.request.output.decorated.expense.ExpenseDecoratedOutput;
import com.example.billsplit.request.output.helper.expense.ExpenseHelper;
import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import com.example.billsplit.service.expense.create.CreateExpenseService;
import com.example.billsplit.service.expense.delete.DeleteExpenseService;
import com.example.billsplit.service.expense.get.GetExpenseService;
import com.example.billsplit.service.expense.validate.AddExpenseValidationService;
import com.example.billsplit.service.expense.validate.GetExpenseValidationService;
import org.springframework.stereotype.Service;

@Service
public class ExpenseService {

    private final AddExpenseValidationService addExpenseValidationService;
    private final CreateExpenseService createExpenseService;
    private final ExpenseHelper expenseHelper;
    private final GetExpenseValidationService getExpenseValidationService;
    private final GetExpenseService getExpenseService;
    private final DeleteExpenseService deleteExpenseService;

    public ExpenseService(AddExpenseValidationService addExpenseValidationService, CreateExpenseService createExpenseService,
                          ExpenseHelper expenseHelper, GetExpenseValidationService getExpenseValidationService,
                          GetExpenseService getExpenseService, DeleteExpenseService deleteExpenseService) {
        this.addExpenseValidationService = addExpenseValidationService;
        this.createExpenseService = createExpenseService;
        this.expenseHelper = expenseHelper;
        this.getExpenseValidationService = getExpenseValidationService;
        this.getExpenseService = getExpenseService;
        this.deleteExpenseService = deleteExpenseService;
    }

    public ExpenseDecoratedOutput addExpense(final AddExpenseInput addExpenseInput) {
        addExpenseValidationService.validateExpense(addExpenseInput);
        ExpenseOutput expenseOutput = createExpenseService.createExpense(addExpenseInput);
        return expenseHelper.addExpenseOutputDecorator(expenseOutput);
    }

    public ExpenseDecoratedOutput getExpense(Long expenseID) {
        getExpenseValidationService.validateIfExpenseExists(expenseID);
        ExpenseOutput expenseOutput = getExpenseService.getExpenseForID(expenseID);
        return  expenseHelper.addExpenseOutputDecorator(expenseOutput);
    }

    public void deleteExpense(Long expenseID) {
        getExpenseValidationService.validateIfExpenseExists(expenseID);
        deleteExpenseService.deleteExpenseByID(expenseID);
    }
}
