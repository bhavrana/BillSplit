package com.example.billsplit.repository;

import com.example.billsplit.domain.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Uzer, Long> {
}
