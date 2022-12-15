package com.example.billsplit.request.output.raw.usergroup;

import com.example.billsplit.domain.Uzer;
import com.example.billsplit.request.output.raw.expense.ExpenseOutput;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@AllArgsConstructor
public class UserGroupOutput {

    private Long Id;

    private String title;

    private String description;

    private List<String> users = new ArrayList<>();

    private List<ExpenseOutput> expenseOutputs = new ArrayList<>();

    public UserGroupOutput(Long id, String title, String description, List<String> users) {
        Id = id;
        this.title = title;
        this.description = description;
        this.users = users;
    }
}
