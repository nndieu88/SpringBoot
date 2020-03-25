package com.smartosc.mobile.repository;

import com.smartosc.mobile.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    public Product findByNameProd(String name);
//    public Page<Product> findAll(Pageable pageable);

    @Query(value = "select * from product p where p.category_id = ?", nativeQuery = true)
    public Page<Product> findAllByCate(@Param("id") Integer id, Pageable pageable);

    @Query(value = "select * from product p where p.name_prod like :name%",nativeQuery = true)
    public Page<Product> findAllByName(@Param("name")String name, Pageable pageable);
}
