package com.example.billsplit.service.User.get;

import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.output.raw.user.UserOutput;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserService {

    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserOutput getUserForUserID(Long userID) {
        Uzer user = userRepository.findById(userID).get();

        List<String> userGroupList = new ArrayList<>();
        for(UserGroup userGroup : user.getUserGroups()) {
            userGroupList.add(userGroup.getTitle());
        }
        UserOutput userOutput = new UserOutput(user.getId(), user.getName(),
                !user.getUserGroups().isEmpty() ? userGroupList : null);

        return userOutput;
    }
}
