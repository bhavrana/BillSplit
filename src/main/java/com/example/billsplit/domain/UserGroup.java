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
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private  String title;

    @OneToMany(mappedBy = "userGroup")
    private List<Uzer> users = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup")
    private List<Expense> expenses = new ArrayList<>();

    @PreRemove
    public void nullification() {
        users.forEach(user -> user.setUserGroup(null));
    }

}
