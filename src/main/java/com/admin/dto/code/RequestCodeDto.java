package com.admin.dto.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestCodeDto {

    private Long id;
    private Long codeGroupId;
    private String codeName;
    private String code;

}
