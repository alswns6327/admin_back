package com.admin.dto.menu;

import com.admin.domain.common.Menu;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMenuDto {
    private Long id;
    private Long parentMenuId;
    private int menuOrder;
    private String menuPath;
    private String menuName;
    private List<ResponseMenuDto> childrenMenu;

    public ResponseMenuDto(Menu menu) {
        this.id = menu.getId();
        this.parentMenuId = menu.getParentMenuId();
        this.menuOrder = menu.getMenuOrder();
        this.menuPath = menu.getMenuPath();
        this.menuName = menu.getMenuName();
    }
}
