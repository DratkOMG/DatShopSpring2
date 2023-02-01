package com.example.datshopspring2.repositories;

import com.example.datshopspring2.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findAccountByEmail(String email);

    Account findAccountByEmailAndPassword(String email, String password);

    Account findAccountByAccountId(Long sellerId);

    @Query(value = "select password from account where email = ?1", nativeQuery = true)
    String findPasswordByEmail(String email);

}