package com.example.billsplit.service.usergroup.get;

import com.example.billsplit.domain.UserGroup;
import com.example.billsplit.repository.UserGroupRepository;
import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import com.example.billsplit.request.output.raw.usergroup.UserGroupOutput;
import com.example.billsplit.service.expense.get.GetExpenseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetUserGroupService {

    private final UserGroupRepository userGroupRepository;

    private final GetExpenseService getExpenseService;

    public GetUserGroupService(UserGroupRepository userGroupRepository, GetExpenseService getExpenseService) {
        this.userGroupRepository = userGroupRepository;
        this.getExpenseService = getExpenseService;
    }

    public UserGroupOutput getUserGroupForID(Long userGroupID) {
        UserGroup userGroup = userGroupRepository.findById(userGroupID).get();
        List<ExpenseOutput> expenseOutputs = new ArrayList<>();

        List<String> users = new ArrayList<>();
        for(var user : userGroup.getUsers()) {
            users.add(user.getName());
        }

        for(var expense : userGroup.getExpenses()) {
            expenseOutputs.add(getExpenseService.getExpenseForID(expense.getId()));
        }

        UserGroupOutput userGroupOutput = new UserGroupOutput(userGroup.getId(), userGroup.getTitle(),
                userGroup.getDescription(), users, expenseOutputs);
        return userGroupOutput;
    }
}
