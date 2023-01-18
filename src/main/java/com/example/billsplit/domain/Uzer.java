package com.example.billsplit.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.util.*;

@Entity
@RequiredArgsConstructor
@Getter
@Setter
public class Uzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private Set<UserGroup> userGroups = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<UserBalance> userBalanceList = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Uzer uzer = (Uzer) o;
        return id.equals(uzer.id) && name.equals(uzer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    public void addUserGroup(UserGroup userGroup) {
        this.userGroups.add(userGroup);
    }

}

