package com.example.billsplit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class UserBalance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Uzer user;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Balance balance;

    @ManyToOne
    private Expense expense;
}
