package com.example.billsplit.exception;

import org.springframework.stereotype.Component;

@Component
public class BillSplitException extends RuntimeException {

    public BillSplitException() {
        super();
    }

    public BillSplitException(String message) {
        super(message);
    }

    public BillSplitException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BillSplitException(Throwable throwable) {
        super(throwable);
    }
}
