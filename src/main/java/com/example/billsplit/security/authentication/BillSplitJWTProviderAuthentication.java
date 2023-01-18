package com.example.billsplit.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class BillSplitJWTProviderAuthentication extends UsernamePasswordAuthenticationToken {

    private String userName;

    public BillSplitJWTProviderAuthentication(String principal, String credentials) {
        super(principal, credentials);
    }

    public BillSplitJWTProviderAuthentication(Object principal, Object credentials,
                                              Collection<? extends GrantedAuthority> authorities, String userName) {
        super(principal, credentials, authorities);
        this.userName = userName;
    }
}
