package com.admin.service.menu;

import com.admin.domain.menu.Menu;
import com.admin.dto.menu.RequestMenuDto;
import com.admin.dto.menu.ResponseMenuDto;
import com.admin.repository.menu.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;

    public List<ResponseMenuDto> getMenuList(){
        List<ResponseMenuDto> responseMenuDtos = menuRepository.findByParentMenuIdAndDelYnOrderByMenuOrder(0L, 0)
                .stream()
                .map(ResponseMenuDto::new)
                .collect(Collectors.toList());

        for(ResponseMenuDto responseMenuDto : responseMenuDtos){
            responseMenuDto.setChildrenMenu(
                menuRepository.findByParentMenuIdAndDelYnOrderByMenuOrder(responseMenuDto.getId(), 0)
                    .stream()
                    .map(ResponseMenuDto::new)
                    .collect(Collectors.toList())
            );
        }

        return responseMenuDtos;
    }

    public List<ResponseMenuDto> saveMenu(RequestMenuDto requestMenuDto) {
        Menu menu = menuRepository.findById(requestMenuDto.getId()).map(entity -> entity.update(requestMenuDto))
                        .orElse(new Menu(requestMenuDto));
        menuRepository.save(menu);
        return getMenuList();
    }

    public List<ResponseMenuDto> removeMenu(Long menuId) {

        Menu menu = menuRepository.findById(menuId)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + menuId));

        menu.remove();

        menuRepository.save(menu);

        return getMenuList();
    }
}
