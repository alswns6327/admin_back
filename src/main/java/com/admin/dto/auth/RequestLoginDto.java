package com.admin.dto.auth;

import com.admin.domain.auth.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestLoginDto {

    private String adminId;
    private String password;
    private String name;

    public void encrytPassword(BCryptPasswordEncoder bCryptPasswordEncoder){
        this.password = bCryptPasswordEncoder.encode(this.password);
    }

    public RequestLoginDto(Account account){
        this.adminId = account.getAdminId();
        this.name = account.getName();
    }
}
