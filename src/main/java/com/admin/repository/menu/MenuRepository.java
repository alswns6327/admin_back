package com.admin.repository.menu;

import com.admin.domain.menu.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, Long> {

    List<Menu> findByParentMenuId(Long parentMenuId);

    List<Menu> findByParentMenuIdAndDelYn(Long parentMenuId, int delYn);

}
