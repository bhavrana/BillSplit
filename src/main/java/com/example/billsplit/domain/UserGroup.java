package com.example.billsplit.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "userGroups")
    private Set<Uzer> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userGroup")
    private List<Expense> expenses = new ArrayList<>();

    /*@PreRemove
    public void nullification() {
        users.forEach(user -> user.setUserGroups(null));
    }*/

}
