package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.request.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    public Page<Product> getAllProduct(Pageable pageable);

    public Optional<Product> getProductById(Long id);

    public Product createProduct(Product product);

    public UpdateProductRequest updateProduct(Long id, UpdateProductRequest product);

    public void deleteProduct(Long id);
}
