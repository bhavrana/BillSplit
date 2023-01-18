package com.example.billsplit.security.service;

import com.example.billsplit.service.User.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillSplitJWTAuthenticationProviderService {

    private final UserService userService;

    public boolean checkIfUserIsRegistered(final String name) {
        return userService.validateIfUserExists(name);
    }

    public void registerUser(final String userName) {
        userService.postUser(userName, null);
    }
}
