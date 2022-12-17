package com.example.billsplit.request.output.decorated.user;

import com.example.billsplit.request.output.raw.user.UserOutput;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDecoratedOutput {

    @JsonProperty("User")
    private UserOutput userOutput;
}
