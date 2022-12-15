package com.example.billsplit.request.output.helper.usergroup;

import com.example.billsplit.request.output.decorated.usergroup.UserGroupDecoratedOutput;
import com.example.billsplit.request.output.decorated.usergroup.UserGroupSettlementDecoratedOutput;
import com.example.billsplit.request.output.raw.usergroup.UserGroupOutput;
import com.example.billsplit.request.output.raw.usergroup.UserGroupSettlementOutput;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserGroupHelper {

    public UserGroupDecoratedOutput addUserGroupOutputDecorator(UserGroupOutput userGroupOutput) {
        UserGroupDecoratedOutput userGroupDecoratedOutput = new UserGroupDecoratedOutput(userGroupOutput);
        return userGroupDecoratedOutput;
    }

    public UserGroupSettlementDecoratedOutput userGroupSettlementOutputHelper(List<UserGroupSettlementOutput>
                                                                                      userGroupSettlementOutputList) {
        UserGroupSettlementDecoratedOutput userGroupSettlementDecoratedOutput = new
                UserGroupSettlementDecoratedOutput(userGroupSettlementOutputList);
        return userGroupSettlementDecoratedOutput;
    }
}
