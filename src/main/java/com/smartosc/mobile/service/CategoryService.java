package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.model.dto.CategoryDto;
import com.smartosc.mobile.model.request.CreateCategoryRequest;
import com.smartosc.mobile.model.request.UpdateCategoryRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    public List<Category> findAllCategory();

    public Category findById(Long id);

    public CategoryDto createCategory(CreateCategoryRequest category);

    public CategoryDto updateCategory(Long id, UpdateCategoryRequest category);

    public void deleteCategory(Long id);
}
