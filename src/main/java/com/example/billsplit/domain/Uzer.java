package com.example.billsplit.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Uzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private UserGroup userGroup;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserBalance> userBalanceList = new ArrayList<>();
}

