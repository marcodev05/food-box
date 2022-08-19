package com.tsk.application.web.menu;

import com.tsk.domain.dto.request.MenuDtoRequest;
import com.tsk.domain.entities.Menu;
import com.tsk.exception.BadRequestException;
import com.tsk.service.menu.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tsk.application.utils.Constants.*;

@RestController
public class MenuController {

    @Autowired
    IMenuService iMenuService;

    /*****************************
     *          PUBLIC URL
     ****************************/

    @GetMapping(URL_PUBLIC + "/menus/available")
    public ResponseEntity<List<Menu>> getAllAvailableMenu(){
        List<Menu> menus = iMenuService.fetchAllAvailableMenus();
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }


    @GetMapping(URL_PUBLIC + "/menus/{id}")
    public ResponseEntity<Menu> getMenuById(@PathVariable("id")Long id){
        Menu menu = iMenuService.fetchMenuById(id);
        return new ResponseEntity<>(menu, HttpStatus.OK);
    }

    /*****************************
     *          MANAGER URL
     ****************************/

    @PostMapping(URL_MANAGER + "/menus/add")
    public ResponseEntity<Menu> addMenu(@RequestBody MenuDtoRequest request) {
        Menu menu = iMenuService.createMenu(request);
        return new ResponseEntity<>(menu, HttpStatus.CREATED);
    }

    @PutMapping(URL_MANAGER + "/menus/{id}/update")
    public ResponseEntity<Menu> updateMenu(@RequestBody MenuDtoRequest request, @PathVariable("id") Long id) {
        Menu menu = iMenuService.createMenu(request);
        if (menu != null) {
            return new ResponseEntity<>(menu, HttpStatus.OK);
        } else throw new BadRequestException("Operation failed");
    }

    @PostMapping(URL_MANAGER + "/menus/{menuId}/category/{categoryId}")
    public void moveMenuToCategory(@PathVariable("menuId") Long menuId, @PathVariable("categoryId") Long categoryId){
        Menu response = iMenuService.moveMenuToCategory(menuId, categoryId);
    }

    @DeleteMapping(URL_MANAGER + "/menus/{id}/delete")
    public Map<String, Boolean> deleteProduct(@PathVariable("id") Long id) {
        Boolean deleted = iMenuService.deleteMenu(id);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        if (deleted) {
            response.put("deleted", Boolean.TRUE);
            return response;
        } else throw new BadRequestException("Operation failed");
    }
}
