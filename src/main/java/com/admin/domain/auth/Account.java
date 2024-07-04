package com.admin.domain.auth;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ACCOUNT_ID", nullable = false)
    private long adminAccountId;

    @Column(name = "ADMIN_ID", nullable = false)
    private String adminId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name="NAME", nullable = false)
    private String name;
}
