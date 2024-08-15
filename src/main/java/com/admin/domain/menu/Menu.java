package com.admin.domain.menu;

import com.admin.dto.menu.RequestMenuDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "admin_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADMIN_MENU_ID", nullable = false, updatable = false)
    private Long id;

    @Column(name = "PARENT_MENU_ID", nullable = false)
    private Long parentMenuId;

    @Column(name = "MENU_ORDER", nullable = false)
    private int menuOrder;

    @Column(name = "MENU_PATH", nullable = false)
    private String menuPath;

    @Column(name = "MENU_NAME", nullable = false)
    private String menuName;

    @Column(name = "DEL_YN", nullable = false)
    private int delYn;

    public Menu removeMenu(){
        this.delYn = 0;
        return this;
    }

    public Menu(RequestMenuDto requestMenuDto){
        this.id = requestMenuDto.getId();
        this.parentMenuId = requestMenuDto.getParentMenuId();
        this.menuOrder = requestMenuDto.getMenuOrder();
        this.menuPath = requestMenuDto.getMenuPath();
        this.menuName = requestMenuDto.getMenuName();
        this.delYn = 1;
    }

    public Menu update(RequestMenuDto requestMenuDto) {
        this.menuPath = requestMenuDto.getMenuPath();
        this.menuName = requestMenuDto.getMenuName();

        return this;
    }
}
