package com.example.billsplit.service.usergroup;

import com.example.billsplit.request.input.userexpense.UserGroupInput;
import com.example.billsplit.request.output.decorated.usergroup.UserGroupDecoratedOutput;
import com.example.billsplit.request.output.decorated.usergroup.UserGroupSettlementDecoratedOutput;
import com.example.billsplit.request.output.helper.usergroup.UserGroupHelper;
import com.example.billsplit.request.output.raw.usergroup.UserGroupOutput;
import com.example.billsplit.request.output.raw.usergroup.UserGroupSettlementOutput;
import com.example.billsplit.service.usergroup.create.CreateUserGroupService;
import com.example.billsplit.service.usergroup.delete.DeleteUserGroupService;
import com.example.billsplit.service.usergroup.get.GetUserGroupService;
import com.example.billsplit.service.usergroup.settlement.UserGroupSettlementService;
import com.example.billsplit.service.usergroup.validate.CreateUserGroupValidationService;
import com.example.billsplit.service.usergroup.validate.GetUserGroupValidationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserGroupService {

    private final CreateUserGroupValidationService createUserGroupValidationService;

    private final CreateUserGroupService createUserGroupService;

    private final UserGroupHelper userGroupHelper;

    private final GetUserGroupValidationService getUserGroupValidationService;

    private final GetUserGroupService getUserGroupService;

    private final DeleteUserGroupService deleteUserGroupService;

    private final UserGroupSettlementService userGroupSettlementService;

    public UserGroupService(CreateUserGroupValidationService createUserGroupValidationService,
                            CreateUserGroupService createUserGroupService, UserGroupHelper userGroupHelper,
                            GetUserGroupValidationService getUserGroupValidationService, GetUserGroupService getUserGroupService,
                            DeleteUserGroupService deleteUserGroupService, UserGroupSettlementService userGroupSettlementService) {
        this.createUserGroupValidationService = createUserGroupValidationService;
        this.createUserGroupService = createUserGroupService;
        this.userGroupHelper = userGroupHelper;
        this.getUserGroupValidationService = getUserGroupValidationService;
        this.getUserGroupService = getUserGroupService;
        this.deleteUserGroupService = deleteUserGroupService;
        this.userGroupSettlementService = userGroupSettlementService;
    }

    public UserGroupDecoratedOutput createUserGroup(final UserGroupInput userGroupInput) {
        createUserGroupValidationService.validateIfUsersExists(userGroupInput);
        UserGroupOutput userGroupOutput = createUserGroupService.createUserGroup(userGroupInput);

        return userGroupHelper.addUserGroupOutputDecorator(userGroupOutput);
    }

    public UserGroupDecoratedOutput getUserGroup(Long userGroupID) {
        getUserGroupValidationService.validateIfUserGroupExits(userGroupID);
        UserGroupOutput userGroupOutput = getUserGroupService.getUserGroupForID(userGroupID);
        return userGroupHelper.addUserGroupOutputDecorator(userGroupOutput);
    }

    public void deleteUserGroup(Long userGroupID) {
        getUserGroupValidationService.validateIfUserGroupExits(userGroupID);
        deleteUserGroupService.deleteUserGroupForID(userGroupID);
    }

    public UserGroupSettlementDecoratedOutput getUserGroupPaymentGraph(Long userGroupID) {
        getUserGroupValidationService.validateIfUserGroupExits(userGroupID);
        List<UserGroupSettlementOutput> userGroupSettlementOutputList = userGroupSettlementService.settle(userGroupID);
        return userGroupHelper.userGroupSettlementOutputHelper(userGroupSettlementOutputList);
    }
}
