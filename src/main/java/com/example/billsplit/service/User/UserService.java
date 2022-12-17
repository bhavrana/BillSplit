package com.example.billsplit.service.User;

import com.example.billsplit.request.output.decorated.user.UserDecoratedOutput;
import com.example.billsplit.request.output.helper.user.UserHelper;
import com.example.billsplit.request.output.raw.user.UserOutput;
import com.example.billsplit.service.User.create.CreateUserService;
import com.example.billsplit.service.User.delete.DeleteUserService;
import com.example.billsplit.service.User.get.GetUserService;
import com.example.billsplit.service.User.validation.UserValidationService;
import com.example.billsplit.service.usergroup.validate.GetUserGroupValidationService;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    private final UserValidationService userValidationService;
    private final GetUserService getUserService;
    private final UserHelper userHelper;
    private final GetUserGroupValidationService getUserGroupValidationService;
    private final CreateUserService createUserService;
    private final DeleteUserService deleteUserService;

    public UserService(UserValidationService userValidationService, GetUserService getUserService, UserHelper userHelper,
                       GetUserGroupValidationService getUserGroupValidationService, CreateUserService createUserService, DeleteUserService deleteUserService) {
        this.userValidationService = userValidationService;
        this.getUserService = getUserService;
        this.userHelper = userHelper;
        this.getUserGroupValidationService = getUserGroupValidationService;
        this.createUserService = createUserService;
        this.deleteUserService = deleteUserService;
    }

    public UserDecoratedOutput getUserForID(Long userID) {
        userValidationService.validateIfUserExists(userID);
        UserOutput userOutput = getUserService.getUserForUserID(userID);
        return userHelper.getUserHelper(userOutput);
    }
    
    public UserDecoratedOutput postUser(final String name, Long groupID) {
        getUserGroupValidationService.validateIfUserGroupExits(groupID);
        UserOutput userOutput = createUserService.createUser(name,groupID);
        return userHelper.getUserHelper(userOutput);
    }

    public void deleteUser(Long userID) {
        userValidationService.validateIfUserExists(userID);
        deleteUserService.deleteUserByID(userID);
    }
}
