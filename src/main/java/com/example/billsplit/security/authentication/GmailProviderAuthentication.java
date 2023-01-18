package com.example.billsplit.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class GmailProviderAuthentication extends UsernamePasswordAuthenticationToken {

    public GmailProviderAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
