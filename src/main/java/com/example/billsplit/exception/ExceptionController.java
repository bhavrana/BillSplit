package com.example.billsplit.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(BillSplitException.class)
    public ResponseEntity exceptionBillSplit(BillSplitException ex){

        return ResponseEntity.ok(ex.getMessage());
    }

}
