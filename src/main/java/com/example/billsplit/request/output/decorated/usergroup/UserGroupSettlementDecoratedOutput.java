package com.example.billsplit.request.output.decorated.usergroup;

import com.example.billsplit.request.output.raw.usergroup.UserGroupSettlementOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UserGroupSettlementDecoratedOutput {

    @JsonProperty("Settlement")
    private List<UserGroupSettlementOutput> userGroupSettlementOutputs;
}
