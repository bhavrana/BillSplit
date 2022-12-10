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

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "userGroup")
    private List<Uzer> users = new ArrayList<>();
}
