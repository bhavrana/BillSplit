package com.example.billsplit.service.User.get;

import com.example.billsplit.domain.Uzer;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.output.raw.user.UserOutput;
import org.springframework.stereotype.Service;

@Service
public class GetUserService {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput getUserForUserID(Long userID) {
        Uzer user = userRepository.findById(userID).get();

        UserOutput userOutput = new UserOutput(user.getId(), user.getName(),
                user.getUserGroup() != null? user.getUserGroup().getTitle() : "null");

        return userOutput;
    }
}
