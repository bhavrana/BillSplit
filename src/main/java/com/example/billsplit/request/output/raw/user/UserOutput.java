package com.example.billsplit.request.output.raw.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Getter
public class UserOutput {

    private Long id;

    private String name;

    private List<String> userGroup = new ArrayList<>();

}
