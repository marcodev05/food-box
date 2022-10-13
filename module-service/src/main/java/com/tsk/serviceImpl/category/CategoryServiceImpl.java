package com.tsk.serviceImpl.category;

import com.tsk.dao.CategoryRepository;
import com.tsk.domain.dto.category.CategoryDtoRequest;
import com.tsk.domain.entities.Category;
import com.tsk.domain.entities.Menu;
import com.tsk.exception.BadRequestException;
import com.tsk.exception.ResourceNotFoundException;
import com.tsk.mappers.CategoryMapper;
import com.tsk.services.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public Category createCategory(CategoryDtoRequest category) {

        try {
            Category c = categoryMapper.fromRequestToCategory(category);
            Category created = categoryRepository.save(c);
            return created;
        } catch (Exception e) {
            e.getStackTrace();
            return null;
        }
    }

    @Override
    public Category updateCategory(Category category) {
        try {
            Category updated = categoryRepository.save(category);
            return updated;
        } catch (Exception e){
            throw new BadRequestException("Operation failed");
        }
    }

    @Override
    public Boolean deleteCategory(Long id) {
        Category category = this.fetchCategoryById(id);
        categoryRepository.delete(category);
        return true;
    }

    @Override
    public List<Category> fetchAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category fetchCategoryById(Long categoryId) {
        Category c = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category not found"));
        return c;
    }

    @Override
    public List<Menu> fetchAllProductsByCategory(Long categoryId) {
        List<Menu> menus = (List<Menu>) fetchCategoryById(categoryId).getMenus();
        return menus;
    }
}
