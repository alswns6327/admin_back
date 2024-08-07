package com.admin.dto.auth;

import com.admin.domain.auth.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseLoginDto {
    private Long id;
    private String adminId;
    private String accessToken;
    private String name;

    @Builder
    public ResponseLoginDto(Account account, String accessToken){
        this.adminId = account.getAdminId();
        this.name = account.getName();
        this.accessToken = accessToken;
    }

    public ResponseLoginDto(Account account) {
        this.id = account.getId();
        this.adminId = account.getAdminId();
        this.name = account.getName();
    }
}
