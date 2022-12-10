package com.example.billsplit.repository;

import com.example.billsplit.domain.Balance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {
}
