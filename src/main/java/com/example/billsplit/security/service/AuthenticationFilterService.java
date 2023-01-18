package com.example.billsplit.security.service;

import com.example.billsplit.util.Constant;
import com.example.billsplit.exception.BillSplitException;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.HashMap;

@Service
public class AuthenticationFilterService {

    public static boolean isGmailTokenExpired(final String token) {
        HashMap<String, Object> payloadMap = decodeTokenAndParseJSONtoMap(token);
        long expiryTime = Double.valueOf((Double) payloadMap.get("exp")).longValue();
        long currTime = Instant.now().getEpochSecond();
        if(expiryTime <= currTime) {
            // The token has expired
            return false;
        }
        return true;
    }

    public static void validateGmailToken(final String token) {
        try {
            HashMap<String, Object> payloadMap = decodeTokenAndParseJSONtoMap(token);
            String issuer = String.valueOf(payloadMap.get("azp"));
            if(!issuer.equals(Constant.CLIENT_ID)) {
                throw  new BillSplitException("Validation for the token : " + token + " failed.");
            }
        } catch (BillSplitException ex){
            throw new BillSplitException(ex);
        }
    }

    public static HashMap<String, Object> decodeTokenAndParseJSONtoMap(final String token) {
        String[] tokenChunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        HashMap<String, Object> payloadMap = new Gson().fromJson(new String(decoder.decode(tokenChunks[1]),
                StandardCharsets.UTF_8), HashMap.class);

        return payloadMap;
    }

}
