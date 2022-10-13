package com.tsk.serviceImpl.menu;


import com.tsk.dao.MenuRepository;
import com.tsk.domain.dto.menu.MenuDtoRequest;
import com.tsk.domain.entities.Category;
import com.tsk.domain.entities.Menu;
import com.tsk.exception.ResourceNotFoundException;
import com.tsk.mappers.MenuMapper;
import com.tsk.services.category.ICategoryService;
import com.tsk.services.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private MenuMapper menuMapper;


    @Override
    public Menu createMenu(MenuDtoRequest request) {
        Menu menu = menuMapper.fromRequestToMenu(request);
        if (request.getCategoryId() != null) {
            Category category = iCategoryService.fetchCategoryById(request.getCategoryId());
            menu.setCategory(category);
        }
        Menu created = menuRepository.save(menu);
        return created;
    }

    @Override
    public Menu updateMenu(Menu menu) {
        return menuRepository.save(menu);
    }


    @Override
    public Boolean deleteMenu(Long id) {
        Menu m = this.fetchMenuById(id);
        menuRepository.delete(m);
        return true;
    }

    @Override
    public List<Menu> fetchAllAvailableMenus() {
        List<Menu> menus = (List<Menu>) menuRepository.findByAvailable(true);
        return menus;
    }

    @Override
    public List<Menu> fetchAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Menu fetchMenuById(Long id) {
        Menu m = menuRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Menu not found"));
        return m;
    }


    @Override
    public Menu moveMenuToCategory(Long menuId, Long categoryId) {
        Menu menu = fetchMenuById(menuId);
        Category category = iCategoryService.fetchCategoryById(categoryId);
        menu.setCategory(category);
        return menuRepository.save(menu);
    }
}
