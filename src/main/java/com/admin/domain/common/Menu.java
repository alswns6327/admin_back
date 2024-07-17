package com.admin.domain.common;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
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
}
