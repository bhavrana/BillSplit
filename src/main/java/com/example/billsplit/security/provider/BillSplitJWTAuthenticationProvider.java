package com.example.billsplit.security.provider;

import com.example.billsplit.security.authentication.BillSplitJWTProviderAuthentication;
import com.example.billsplit.security.service.AuthenticationFilterService;
import com.example.billsplit.security.service.BillSplitJWTAuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class BillSplitJWTAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private BillSplitJWTAuthenticationProviderService billSplitJWTAuthenticationProviderService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        BillSplitJWTProviderAuthentication billSplitJWTProviderAuthentication = (BillSplitJWTProviderAuthentication) authentication;
        String token = String.valueOf(billSplitJWTProviderAuthentication.getPrincipal());
        Map<String, Object> payload = AuthenticationFilterService.decodeTokenAndParseJSONtoMap(token);
        String userName = String.valueOf(payload.get("email"));
        if(!billSplitJWTAuthenticationProviderService.checkIfUserIsRegistered(userName)) {
            //Register the new user
            billSplitJWTAuthenticationProviderService.registerUser(userName);
        }
        return new BillSplitJWTProviderAuthentication(token, null, Collections.emptyList(), userName);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return BillSplitJWTProviderAuthentication.class.equals(aClass);
    }
}

