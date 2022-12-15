package com.example.billsplit.service.usergroup.validate;

import com.example.billsplit.domain.Uzer;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.input.userexpense.UserGroupInput;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CreateUserGroupValidationService {

    private final UserRepository userRepository;

    public CreateUserGroupValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateIfUsersExists(UserGroupInput userGroupInput) {
        List<Long> userIDs = userGroupInput.getUsers();
        for(Long userID : userIDs) {
            Optional<Uzer> userOptional = userRepository.findById(userID);

            if(!userOptional.isPresent()) {
                throw new BillSplitException("The user with ID " + userID + " doesn't exist.");
            }
        }
    }
}
