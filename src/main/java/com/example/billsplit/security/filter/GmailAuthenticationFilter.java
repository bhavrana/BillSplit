package com.example.billsplit.security.filter;

import com.example.billsplit.security.authentication.GmailAuthTokenAuthentication;
import com.example.billsplit.security.authentication.GmailProviderAuthentication;
import com.example.billsplit.security.response.GmailAuthenticationResponse;
import com.example.billsplit.security.service.AuthenticationFilterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class GmailAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String bearerToken = request.getHeader(HttpHeaders.AUTHORIZATION);
            if (bearerToken != null && !bearerToken.startsWith("Bearer ")) {
                throw new Exception("The bearer token is not in Bearer token format.");
            }
            String token = bearerToken != null ? bearerToken.split(" ")[1] : null;

            if (token != null && AuthenticationFilterService.isGmailTokenExpired(token)) {
                filterChain.doFilter(request, response);
            } else {
                // Perform gmail authentication
                GmailProviderAuthentication gmailProviderAuthentication = new GmailProviderAuthentication("text", null);
                GmailAuthTokenAuthentication gmailAuthTokenAuthentication = (GmailAuthTokenAuthentication) authenticationManager.authenticate(gmailProviderAuthentication);

                // Send gmail id token response
                response.setStatus(HttpStatus.UNAUTHORIZED.value());
                response.setContentType(MediaType.APPLICATION_JSON_VALUE);
                GmailAuthenticationResponse gmailAuthenticationResponse = new GmailAuthenticationResponse(
                        gmailAuthTokenAuthentication.getGmailAuthToken(),
                        "Please request again with the received Bearer id-token.");
                new ObjectMapper().writeValue(response.getWriter(), gmailAuthenticationResponse);
            }
        } catch (Exception ex) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            new ObjectMapper().writeValue(response.getWriter(), ex.getMessage());
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        if(request.getRequestURI().contains("h2-console") || request.getRequestURI().contains("favicon")) {
            return true;
        }
        return false;
    }

}
