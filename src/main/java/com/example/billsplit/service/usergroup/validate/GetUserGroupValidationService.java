package com.example.billsplit.service.usergroup.validate;

import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.UserGroupRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetUserGroupValidationService {

    private final UserGroupRepository userGroupRepository;

    public GetUserGroupValidationService(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    public void validateIfUserGroupExits(Long groupID) {
        Optional<UserGroup> userGroupOptional = userGroupRepository.findById(groupID);
        if(!userGroupOptional.isPresent()) {
            throw  new BillSplitException("UserGroup doesn't exist");
        }
    }
}
