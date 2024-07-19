package com.admin.dto.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RequestMenuDto {
    private Long id;
    private String menuName;
    private String menuPath;
    private Long parentMenuId;
    private int menuOrder;
}
