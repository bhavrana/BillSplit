package com.example.billsplit.security.response;

import com.example.billsplit.security.authentication.GmailAuthToken;
import com.example.billsplit.security.authentication.GmailAuthTokenAuthentication;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GmailAuthenticationResponse {

    @JsonProperty("GmailAuthResponse")
    private GmailAuthToken gmailAuthToken;

    @JsonProperty("message")
    private String message;
}
