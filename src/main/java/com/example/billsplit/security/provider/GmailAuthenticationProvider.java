package com.example.billsplit.security.provider;

import com.example.billsplit.security.authentication.GmailAuthToken;
import com.example.billsplit.security.authentication.GmailAuthTokenAuthentication;
import com.example.billsplit.security.authentication.GmailProviderAuthentication;
import com.example.billsplit.security.service.GmailAuthenticationProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class GmailAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private GmailAuthenticationProviderService gmailAuthenticationProviderService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // send request to gmail to get code
        String code = gmailAuthenticationProviderService.getAuthorizationCode();

        // get id token and access token
        GmailAuthToken gmailAuthToken = gmailAuthenticationProviderService.getGmailIDTokenAccessToken(code);

        GmailAuthTokenAuthentication gmailAuthTokenAuthentication = new GmailAuthTokenAuthentication(gmailAuthToken.getId_token(),
                null, gmailAuthToken);
        return gmailAuthTokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return GmailProviderAuthentication.class.equals(aClass);
    }
}
