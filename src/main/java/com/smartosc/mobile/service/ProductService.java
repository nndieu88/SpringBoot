package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    public Page<Product> getAllProduct(Pageable pageable);

    public Optional<Product> getProductById(Long id);

    public Product createProduct(Product product);

    public Product updateProduct(Long id, Product product);

    public void deleteProduct(Long id);
}
