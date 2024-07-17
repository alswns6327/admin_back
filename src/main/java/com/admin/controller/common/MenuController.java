package com.admin.controller.common;

import com.admin.dto.menu.ResponseMenuDto;
import com.admin.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu")
    public ResponseEntity<List<ResponseMenuDto>> getMenuList(){
        System.out.println("git 커밋 잔디 테스트");
        return ResponseEntity.ok().body(menuService.getMenuList());
    }

}
