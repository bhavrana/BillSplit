package com.example.billsplit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "expense")
    private List<UserBalance> userBalanceList = new ArrayList<>();

    @ManyToOne
    private UserGroup userGroup;

    private String title;

    private Long timestamp;

    private Boolean settled;
}
