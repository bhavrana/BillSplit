package com.example.billsplit.request.output.helper.user;

import com.example.billsplit.request.output.decorated.user.UserDecoratedOutput;
import com.example.billsplit.request.output.raw.user.UserOutput;
import org.springframework.stereotype.Component;

@Component
public class UserHelper {

    public UserDecoratedOutput getUserHelper(UserOutput userOutput) {
        UserDecoratedOutput userDecoratedOutput = new UserDecoratedOutput(userOutput);
        return  userDecoratedOutput;
    }
}
