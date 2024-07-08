package com.admin.dto.auth.res;

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

    @Builder
    public ResponseLoginDto(RequestLoginDto requestLoginDto, String accessToken, String refreshToken){
        this.adminId = requestLoginDto.getAdminId();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
