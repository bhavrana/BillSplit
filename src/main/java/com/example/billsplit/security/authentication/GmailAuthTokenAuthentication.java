package com.example.billsplit.security.authentication;

import lombok.Getter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@Getter
public class GmailAuthTokenAuthentication extends UsernamePasswordAuthenticationToken {

    private GmailAuthToken gmailAuthToken;

    public GmailAuthTokenAuthentication(String principal, String credentials, GmailAuthToken gmailAuthToken) {
        super(principal, credentials);
        this.gmailAuthToken = gmailAuthToken;
    }
}
