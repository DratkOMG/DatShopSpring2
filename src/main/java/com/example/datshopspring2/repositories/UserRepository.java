package com.example.datshopspring2.repositories;

import com.example.datshopspring2.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u order by u.userId")
    List<User> findAll();
    User findUserByUserId(Long id);

    User findUserByUserName(String username);

    User findUserByEmail(String email);
}