package com.example.billsplit.repository;

import com.example.billsplit.domain.UserBalance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserBalanceRepository extends JpaRepository<UserBalance, Long> {
}
