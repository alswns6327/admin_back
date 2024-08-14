package com.admin.dto.code;

import com.admin.domain.code.AdminCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCodeDto {

    private Long id;
    private String codeName;
    private String code;

    public ResponseCodeDto(AdminCode adminCode) {
        this.id = adminCode.getId();
        this.codeName = adminCode.getAdminCodeName();
        this.code = adminCode.getAdminCode();
    }
}
