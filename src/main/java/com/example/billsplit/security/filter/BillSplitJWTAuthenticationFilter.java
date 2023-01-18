package com.example.billsplit.security.filter;

import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.security.authentication.BillSplitJWTProviderAuthentication;
import com.example.billsplit.security.service.AuthenticationFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class BillSplitJWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        if(request.getRequestURI().contains("h2-console") || request.getRequestURI().contains("favicon")) {
            return true;
        }
        return false;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
            String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (!bearerToken.startsWith("Bearer ")) {
                throw new BillSplitException("The bearer token is not in Bearer token format.");
            }
            String token = bearerToken.split(" ")[1];

            // Validate the token signature
            AuthenticationFilterService.validateGmailToken(token);
            BillSplitJWTProviderAuthentication billSplitJWTProviderAuthentication = new BillSplitJWTProviderAuthentication(token,
                    null);
            BillSplitJWTProviderAuthentication authentication = (BillSplitJWTProviderAuthentication) authenticationManager
                    .authenticate(billSplitJWTProviderAuthentication);
            SecurityContextHolder
                    .getContext()
                    .setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } catch(BillSplitException ex) {
            throw new BillSplitException(ex);
        }

    }
}
