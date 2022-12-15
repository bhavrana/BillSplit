package com.example.billsplit.request.output.decorated.usergroup;

import com.example.billsplit.request.output.raw.usergroup.UserGroupOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserGroupDecoratedOutput {

    @JsonProperty("UserGroup")
    private UserGroupOutput userGroupOutput;
}
