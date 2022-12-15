package com.example.billsplit.request.output.raw.usergroup;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserGroupSettlementOutput {

    private String payee;

    private String payer;

    private Double amount;

}
