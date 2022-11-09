package com.tsk.serviceImpl.menu;


import com.tsk.dao.MenuRepository;
import com.tsk.domain.dto.menu.MenuDtoRequest;
import com.tsk.domain.entities.Category;
import com.tsk.domain.entities.Menu;
import com.tsk.exception.ResourceNotFoundException;
import com.tsk.mappers.MenuMapper;
import com.tsk.services.category.ICategoryService;
import com.tsk.services.file.FileStorageService;
import com.tsk.services.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class MenuServiceImpl implements IMenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private FileStorageService fileStorageService;

    @Override
    public Menu createMenu(MenuDtoRequest request) {
        Menu menu = menuMapper.fromRequestToMenu(request);
        if (request.getCategoryId() != null) {
            Category category = iCategoryService.fetchCategoryById(request.getCategoryId());
            menu.setCategory(category);
        }
        if(request.getFile() != null){
            String uriFile = this.getUriFile(request.getFile());
            menu.setPicture(uriFile);
        }
        Menu created = menuRepository.save(menu);
        System.out.println(created.toString());
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

    private String getUriFile(MultipartFile file) {
        String fileName = fileStorageService.storeFile(file);
        String fileUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName)
                .toUriString();
        return fileUri;
    }

}
