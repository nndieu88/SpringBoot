package com.smartosc.mobile.model.mapper;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.model.dto.CategoryDto;
import com.smartosc.mobile.model.request.CreateCategoryRequest;
import com.smartosc.mobile.model.request.UpdateCategoryRequest;

import java.util.Date;

public class CategoryMapper {
    public static CategoryDto toCategoryDto(Category category) {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setNameCate(category.getNameCate());
        return categoryDto;
    }

    public static Category toCategory(CreateCategoryRequest createCategoryRequest) {
        Category category = new Category();
        category.setNameCate(createCategoryRequest.getNameCate());
        category.setCreateDate(new Date());
        category.setUpdateDate(new Date());
        return category;
    }

    public static Category toCategory(UpdateCategoryRequest updateCategoryRequest, Long id, Date date) {
        Category category = new Category();
        category.setId(id);
        category.setNameCate(updateCategoryRequest.getNameCate());
        category.setCreateDate(date);
        category.setUpdateDate(new Date());
        return category;
    }
}
