package com.tsk.service.menu;

import com.tsk.domain.dto.menu.MenuDtoRequest;
import com.tsk.domain.entities.Menu;

import java.util.List;

public interface IMenuService {

    public Menu createMenu(MenuDtoRequest request);

    public Menu updateMenu(Menu request);

    public Boolean deleteMenu(Long id);

    public List<Menu> fetchAllAvailableMenus();

    public Menu fetchMenuById(Long id);

    public Menu moveMenuToCategory(Long menuId, Long categoryId);
}
