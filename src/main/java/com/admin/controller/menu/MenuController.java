package com.admin.controller.menu;

import com.admin.dto.menu.RequestMenuDto;
import com.admin.dto.menu.ResponseMenuDto;
import com.admin.service.menu.MenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MenuController {

    private final MenuService menuService;

    @GetMapping("/menu/list")
    public ResponseEntity<List<ResponseMenuDto>> getMenuList(){
        return ResponseEntity.ok().body(menuService.getMenuList());
    }

    @PostMapping("/menu")
    public ResponseEntity<List<ResponseMenuDto>> saveMenu(@RequestBody RequestMenuDto requestMenuDto){
        return ResponseEntity.ok().body(menuService.saveMenu(requestMenuDto));
    }

    @DeleteMapping("/menu/{menuId}")
    public ResponseEntity<List<ResponseMenuDto>> removeMenu(@PathVariable("menuId") String menuId){
        return ResponseEntity.ok().body(menuService.removeMenu(Long.parseLong(menuId)));
    }
}
