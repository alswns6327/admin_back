package com.admin.domain.auth;


import com.admin.domain.common.CommonColumns1;
import com.admin.dto.auth.RequestLoginDto;
import com.admin.dto.auth.ResponseLoginDto;
import com.admin.util.Common;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "admin_account")
public class Account extends CommonColumns1 implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_ACCOUNT_ID", updatable = false, nullable = false)
    private Long id;

    @Column(name = "ADMIN_ID", nullable = false)
    private String adminId;

    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Column(name="NAME", nullable = false)
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REFRESH_TOKEN_ID")
    private RefreshToken refreshToken;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getUsername() {
        return adminId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Account(RequestLoginDto requestLoginDto){
        this.id = requestLoginDto.getId();
        this.adminId = requestLoginDto.getAdminId();
        this.password = requestLoginDto.getPassword();
        this.name = requestLoginDto.getName();
    }

    public Account update(RequestLoginDto requestLoginDto) {
        this.adminId = requestLoginDto.getAdminId();
        this.name = requestLoginDto.getName();
        return this;
    }
}
