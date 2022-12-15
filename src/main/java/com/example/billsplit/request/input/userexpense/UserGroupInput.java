package com.example.billsplit.request.input.userexpense;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserGroupInput {

    private String title;

    private String description;

    private List<Long> users = new ArrayList<>();
}
