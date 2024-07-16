package com.admin.dto.auth.res;

import com.admin.domain.auth.Account;
import com.admin.dto.auth.req.RequestLoginDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseLoginDto {
    private String adminId;
    private String accessToken;
    private String refreshToken;
    private String name;

    @Builder
    public ResponseLoginDto(Account account, String accessToken){
        this.adminId = account.getAdminId();
        this.name = account.getName();
        this.accessToken = accessToken;
    }
}
