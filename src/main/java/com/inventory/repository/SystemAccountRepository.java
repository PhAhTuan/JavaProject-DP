package com.inventory.repository;

import com.inventory.entity.SystemAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface SystemAccountRepository extends JpaRepository<SystemAccount, Integer> {
    Optional<SystemAccount> findByEmail(String email);
}