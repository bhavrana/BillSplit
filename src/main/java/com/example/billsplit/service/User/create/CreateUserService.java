package com.example.billsplit.service.User.create;

import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.repository.UserGroupRepository;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.output.raw.user.UserOutput;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CreateUserService {

    private  final UserRepository userRepository;
    private final UserGroupRepository userGroupRepository;

    public CreateUserService(UserRepository userRepository, UserGroupRepository userGroupRepository) {
        this.userRepository = userRepository;
        this.userGroupRepository = userGroupRepository;
    }

    @Transactional
    public UserOutput createUser(final String name, Long groupID) {
        UserGroup userGroup = userGroupRepository.findById(groupID).get();

        Uzer user = new Uzer();
        user.setName(name);
        user.setUserGroup(userGroup);

        userRepository.save(user);

        UserOutput userOutput = new UserOutput(user.getId(), user.getName(), user.getUserGroup().getTitle());
        return userOutput;
    }
}
