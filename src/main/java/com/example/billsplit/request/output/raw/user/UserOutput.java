package com.example.billsplit.request.output.raw.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserOutput {

    private Long id;

    private String name;

    private String userGroup;
}
