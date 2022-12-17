package com.example.billsplit.service.User.delete;

import com.example.billsplit.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteUserService {

    private final UserRepository userRepository;

    public DeleteUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void deleteUserByID(Long userID) {
        userRepository.deleteById(userID);
    }
}
