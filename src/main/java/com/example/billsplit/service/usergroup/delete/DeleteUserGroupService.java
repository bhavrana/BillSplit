package com.example.billsplit.service.usergroup.delete;

import com.example.billsplit.exception.BillSplitException;
import com.example.billsplit.repository.UserGroupRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class DeleteUserGroupService {

    private final UserGroupRepository userGroupRepository;

    public DeleteUserGroupService(UserGroupRepository userGroupRepository) {
        this.userGroupRepository = userGroupRepository;
    }

    @Transactional
    public void deleteUserGroupForID(Long userGroupID) {
        try {
            userGroupRepository.deleteById(userGroupID);
        } catch (Exception ex) {
            throw new BillSplitException(ex);
        }
    }
}
