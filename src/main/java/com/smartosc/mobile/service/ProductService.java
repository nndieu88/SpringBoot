package com.smartosc.mobile.service;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.model.dto.ProductDto;
import com.smartosc.mobile.model.request.CreateCategoryRequest;
import com.smartosc.mobile.model.request.CreateProduct;
import com.smartosc.mobile.model.request.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {
    public Paging getAllProduct(int page);

    public Paging getAllProductByCategory(String nameCate,int page);

    public Optional<Product> getProductById(Long id);

    public CreateProduct createProduct(CreateProduct product);

    public UpdateProductRequest updateProduct(Long id, UpdateProductRequest product);

    public void deleteProduct(Long id);
}
