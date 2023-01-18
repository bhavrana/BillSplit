package com.example.billsplit.service.User.validation;

import com.example.billsplit.domain.Uzer;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.output.raw.user.UserOutput;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserValidationService {

    private final UserRepository userRepository;


    public UserValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateIfUserExists(Long userID) {
        Optional<Uzer> uzer = userRepository.findById(userID);

        if(!uzer.isPresent()) {
            throw new BillSplitException("The user " + userID + " doesn't exist.");
        }
    }

    public boolean validateIfUserExists(String name) {
        Optional<Uzer> user = userRepository.getUzerByName(name);
        if(!user.isPresent()) {
            return false;
        }
        return true;
    }
}
