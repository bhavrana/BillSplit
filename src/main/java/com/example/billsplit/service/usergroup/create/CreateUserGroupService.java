package com.example.billsplit.service.usergroup.create;

import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.domain.Uzer;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.UserGroupRepository;
import com.example.billsplit.repository.UserRepository;
import com.example.billsplit.request.input.userexpense.UserGroupInput;
import com.example.billsplit.request.output.raw.usergroup.UserGroupOutput;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CreateUserGroupService {

    private final UserGroupRepository userGroupRepository;
    private final UserRepository userRepository;

    public CreateUserGroupService(UserGroupRepository userGroupRepository, UserRepository userRepository) {
        this.userGroupRepository = userGroupRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public UserGroupOutput createUserGroup(final UserGroupInput userGroupInput) {
        try {
            UserGroup userGroup = new UserGroup();

            Set<Uzer> users = new HashSet<>();

            for (Long userID : userGroupInput.getUsers()) {
                users.add(userRepository.findById(userID).get());
            }

            userGroup.setUsers(users);
            userGroup.setDescription(userGroupInput.getDescription());
            userGroup.setTitle(userGroupInput.getTitle());

            userGroupRepository.save(userGroup);

            List<String> userList = new ArrayList<>();
            for(var user : userGroup.getUsers()) {
                userList.add(user.getName());
            }

            UserGroupOutput userGroupOutput = new UserGroupOutput(userGroup.getId(), userGroup.getTitle(),
                    userGroup.getDescription(), userList);

            return userGroupOutput;

        } catch (RuntimeException ex) {
            throw new BillSplitException(ex.getMessage(), ex);
        }
    }
}
