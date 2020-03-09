package com.smartosc.mobile.repository;

import com.smartosc.mobile.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Category findByNameCate(String name);
}
