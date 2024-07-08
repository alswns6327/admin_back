package com.admin.domain.auth;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REFRESH_TOKEN_ID", updatable = false, nullable = false)
    private Long id;

    @OneToOne(mappedBy = "refreshToken", fetch = FetchType.LAZY)
    private Account account;

    @Column(name = "REFRESH_TOKEN", nullable = false)
    private String refreshToken;

    public RefreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }

    public void update(String newRefreshToken){
        this.refreshToken = newRefreshToken;
    }
}
