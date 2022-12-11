package com.example.billsplit.controller;

import com.example.billsplit.request.input.expense.AddExpenseInput;
import com.example.billsplit.request.output.decorated.expense.ExpenseDecoratedOutput;
import com.example.billsplit.service.expense.ExpenseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/expense")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ExpenseDecoratedOutput> addExpense(@RequestParam(name = "groupID", required = true) Long groupID,
                                                      @RequestParam(name = "userID", required = true) Long userID,
                                                      @RequestParam(name = "paid", required = true) Long paid,
                                                      @RequestParam(name = "currency", defaultValue = "1" ) Long currency,
                                                      @RequestParam(name = "title", defaultValue = "New expense") String title) {

        AddExpenseInput addExpenseInput = new AddExpenseInput(groupID, userID, paid, currency, title);
        ExpenseDecoratedOutput expenseDecoratedOutput = expenseService.addExpense(addExpenseInput);
        return ResponseEntity.status(HttpStatus.CREATED).body(expenseDecoratedOutput);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping("/{expenseID}")
    ResponseEntity<ExpenseDecoratedOutput> getExpense(@PathVariable(name = "expenseID", required = true) Long expenseID) {
        ExpenseDecoratedOutput expenseDecoratedOutput = expenseService.getExpense(expenseID);
        return ResponseEntity.status(HttpStatus.OK).body(expenseDecoratedOutput);
    }

    @DeleteMapping
    @RequestMapping("/delete/{expenseID}")
    ResponseEntity deleteExpense(@PathVariable(name = "expenseID", required = true) Long expenseID) {
        expenseService.deleteExpense(expenseID);
        return new ResponseEntity(HttpStatus.OK);
    }

}
