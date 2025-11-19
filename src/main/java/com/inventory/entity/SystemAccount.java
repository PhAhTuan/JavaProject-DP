package com.inventory.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "systemaccounts")
@Data
public class SystemAccount {

    @Id
    @Column(name = "AccountID")
    private Integer accountId;

    @Column(name = "Username", nullable = false)
    private String username;

    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;

    @Column(name = "Role")
    private Integer role;

    @Column(name = "IsActive")
    private Boolean isActive;
}