package com.example.billsplit.service.User.create;

import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.repository.UserGroupRepository;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.output.raw.user.UserOutput;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

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
        UserGroup userGroup = null;
        if(groupID != null) {
            userGroup = userGroupRepository.findById(groupID).get();
        }

        Uzer user = new Uzer();
        user.setName(name);
        if(userGroup != null)
            user.addUserGroup(userGroup);

        userRepository.save(user);

        UserGroup finalUserGroup = userGroup;
        UserOutput userOutput = new UserOutput(user.getId(), user.getName(), user.getUserGroups().isEmpty() == true
                ? null
                : new ArrayList<String>() {
            {
                add(finalUserGroup.getTitle());
            }
        });
        return userOutput;
    }
}
