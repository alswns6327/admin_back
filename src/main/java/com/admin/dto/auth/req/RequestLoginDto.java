package com.admin.dto.auth.req;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RequestLoginDto {

    private String adminId;
    private String password;
}
