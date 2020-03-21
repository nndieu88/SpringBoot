package com.smartosc.mobile.controller.api;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.model.dto.CategoryDto;
import com.smartosc.mobile.model.request.CreateCategoryRequest;
import com.smartosc.mobile.model.request.UpdateCategoryRequest;
import com.smartosc.mobile.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("admins/categories")
@RestController
public class ApiCategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<?> getAllCategory() {
        List<Category> categories = categoryService.findAllCategory();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createCategory(@Valid @RequestBody CreateCategoryRequest category) {
        CategoryDto category1 = categoryService.createCategory(category);
        return ResponseEntity.ok(category1);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryRequest category, @PathVariable Long id) {
        categoryService.updateCategory(id, category);
        return ResponseEntity.ok("successfull");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>("Successful", HttpStatus.OK);
    }
}
