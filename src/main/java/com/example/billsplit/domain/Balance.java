package com.example.billsplit.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@NoArgsConstructor
@Entity
@Getter
@Setter
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long amount;

    @ManyToOne
    private Currency currency;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "balance")
    //private List<UserBalance> userBalanceList = new ArrayList<>();
}
