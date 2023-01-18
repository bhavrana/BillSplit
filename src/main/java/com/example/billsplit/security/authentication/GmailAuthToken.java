package com.example.billsplit.security.authentication;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class GmailAuthToken {

    @JsonProperty("access_token")
    private String access_token;

    @JsonProperty("expires_in")
    private Integer expires_in;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("token_type")
    private String token_type;

    @JsonProperty("id_token")
    private String id_token;

    public GmailAuthToken(String access_token, Integer expires_in, String scope, String token_type, String id_token) {
        this.access_token = access_token;
        this.expires_in = expires_in;
        this.scope = scope;
        this.token_type = token_type;
        this.id_token = id_token;
    }

    public GmailAuthToken() {

    }

}
