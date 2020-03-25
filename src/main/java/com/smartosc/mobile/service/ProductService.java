package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.model.request.CreateProduct;
import com.smartosc.mobile.model.request.UpdateProductRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    public Paging getAllProduct(int page);

    public Paging getAllProductByCategory(Integer id,int page);

    public Paging getAllByName(String name, int page);

    public Optional<Product> getProductById(Long id);

    public CreateProduct createProduct(CreateProduct product);

    public UpdateProductRequest updateProduct(Long id, UpdateProductRequest product);

    public void deleteProduct(Long id);
}
