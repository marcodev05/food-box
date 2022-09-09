package com.tsk.services.category;

import com.tsk.domain.dto.category.CategoryDtoRequest;
import com.tsk.domain.entities.Category;
import com.tsk.domain.entities.Menu;

import java.util.List;

public interface ICategoryService {

    public Category createCategory(CategoryDtoRequest category);

    public Category updateCategory(Category category);

    public Boolean deleteCategory(Long id);

    public List<Category> fetchAllCategories();

    public Category fetchCategoryById(Long categoryId);

    public List<Menu> fetchAllProductsByCategory(Long categoryId);
}
