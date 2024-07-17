package com.admin.service.menu;

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
        List<ResponseMenuDto> responseMenuDtos = menuRepository.findByParentMenuId(0L)
                .stream()
                .map(ResponseMenuDto::new)
                .collect(Collectors.toList());

        for(ResponseMenuDto responseMenuDto : responseMenuDtos){
            responseMenuDto.setChildrenMenu(
                menuRepository.findByParentMenuId(responseMenuDto.getId())
                    .stream()
                    .map(ResponseMenuDto::new)
                    .collect(Collectors.toList())
            );
        }

        return responseMenuDtos;
    }

}
