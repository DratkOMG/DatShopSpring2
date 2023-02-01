package com.example.datshopspring2.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long accountId;

    @Column(name = "email", nullable = false, length = 50)
    private String email;
    private String password;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "is_seller")
    private Boolean isSeller;

    @Transient
    private String username;

    @Transient
    private String confirmPassword;

}
