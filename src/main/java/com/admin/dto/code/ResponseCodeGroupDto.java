package com.admin.dto.code;

import com.admin.domain.code.AdminCodeGroup;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCodeGroupDto {

    private Long id;
    private String codeGroupName;

    public ResponseCodeGroupDto(AdminCodeGroup adminCodeGroup) {
        this.id = adminCodeGroup.getId();
        this.codeGroupName = adminCodeGroup.getAdminCodeGroupName();
    }
}
