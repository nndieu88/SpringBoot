package com.smartosc.mobile.repository;

import com.smartosc.mobile.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByNameProd(String name);
//    public Page<Product> findAll(Pageable pageable);
    public Page<Product> findAllByCategoryContaining(String nameCate,Pageable pageable);
}
