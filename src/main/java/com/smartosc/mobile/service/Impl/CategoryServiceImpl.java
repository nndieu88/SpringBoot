package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.exception.DuplicateRecordException;
import com.smartosc.mobile.exception.InternalServerException;
import com.smartosc.mobile.exception.NotFoundException;
import com.smartosc.mobile.model.dto.CategoryDto;
import com.smartosc.mobile.model.mapper.CategoryMapper;
import com.smartosc.mobile.model.request.CreateCategoryRequest;
import com.smartosc.mobile.model.request.UpdateCategoryRequest;
import com.smartosc.mobile.repository.CategoryRepository;
import com.smartosc.mobile.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAllCategory() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    @Override
    public Category findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new NotFoundException("Not found category");
        }
        return category.get();
    }

    @Override
    public CategoryDto createCategory(CreateCategoryRequest category) {
        Category category1 = categoryRepository.findByNameCate(category.getNameCate());
        if (category1 != null) {
            throw new DuplicateRecordException("category already is in use");
        }
        try {
            categoryRepository.save(CategoryMapper.toCategory(category));
        } catch (Exception ex) {
            throw new InternalServerException("Can't create category");
        }
        return new CategoryDto();
    }

    @Override
    public CategoryDto updateCategory(Long id, UpdateCategoryRequest category) {
        Optional<Category> category1 = categoryRepository.findById(id);
        if (!category1.isPresent()) {
            throw new NotFoundException("Not found category");
        }
        try {
            categoryRepository.save(CategoryMapper.toCategory(category, id, category1.get().getCreateDate()));
        } catch (Exception ex) {
            throw new InternalServerException("Can't update category");
        }
        return CategoryMapper.toCategoryDto(new Category());
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()) {
            throw new NotFoundException("Not found category");
        }
        try {
            categoryRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Can't delete category");
        }
    }
}
