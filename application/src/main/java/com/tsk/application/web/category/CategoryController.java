package com.tsk.application.web.category;

import com.tsk.domain.dto.request.CategoryDtoRequest;
import com.tsk.domain.entities.Category;
import com.tsk.domain.entities.Menu;

import com.tsk.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tsk.application.utils.Constants.URL_MANAGER;
import static com.tsk.application.utils.Constants.URL_PUBLIC;

@RestController
public class CategoryController {

    @Autowired
    ICategoryService iCategoryService;

    /*****************************
     *          PUBLIC URL
     ****************************/

    @GetMapping(URL_PUBLIC + "/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = iCategoryService.fetchAllCategories();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }


    @GetMapping(URL_PUBLIC + "/categories/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id")Long id){
        Category c = iCategoryService.fetchCategoryById(id);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }


    @GetMapping(URL_PUBLIC + "/categories/{id}/menus")
    public ResponseEntity<List<Menu>> getAllMenusByCategory(@PathVariable("id") Long idCategory){
        List<Menu> menus = iCategoryService.fetchAllProductsByCategory(idCategory);
        return new ResponseEntity<>(menus, HttpStatus.OK);
    }

    /*****************************
     *          MANAGER URL
     ****************************/

    @PostMapping(URL_MANAGER + "/categories/add")
    public ResponseEntity<Category> addCategory(@Valid @RequestBody CategoryDtoRequest category) {
        Category c = iCategoryService.createCategory(category);
        if (c != null) return new ResponseEntity<>(c, HttpStatus.CREATED);
        else return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);

    }


    @DeleteMapping(URL_MANAGER + "/categories/{id}/delete")
    public Map<String, Boolean> deleteCategoryById(@PathVariable("id") Long id) {
        iCategoryService.deleteCategory(id);
        Map<String, Boolean> response = new HashMap<String, Boolean>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


    @PutMapping(URL_MANAGER + "/categories/update")
    public ResponseEntity<Category> updateCategory(Long id, @Valid @RequestBody Category category) {
        Category c = iCategoryService.updateCategory(category);
        return new ResponseEntity<>(c, HttpStatus.OK);
    }

}
