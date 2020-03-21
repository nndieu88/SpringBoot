package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.exception.DuplicateRecordException;
import com.smartosc.mobile.exception.InternalServerException;
import com.smartosc.mobile.exception.NotFoundException;
import com.smartosc.mobile.model.mapper.ProductMapper;
import com.smartosc.mobile.model.request.CreateProduct;
import com.smartosc.mobile.model.request.UpdateProductRequest;
import com.smartosc.mobile.repository.CategoryRepository;
import com.smartosc.mobile.repository.ProductRepository;
import com.smartosc.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Product> getAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        return products;
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new NotFoundException("Not found product");
        }
        return product;
    }

    @Override
    public CreateProduct createProduct(CreateProduct product) {
        Product product1 = productRepository.findByNameProd(product.getNameProd());
        if (product1 != null) {
            throw new DuplicateRecordException("Name_product is already in use");
        }
        Category category = categoryRepository.findById(product.getCate_id()).get();
        product.setCategory(category);
        productRepository.save(ProductMapper.toProduct(product));
        return product;
    }

    @Override
    public UpdateProductRequest updateProduct(Long id, UpdateProductRequest product) {
        Optional<Product> product1 = productRepository.findById(id);
        if (!product1.isPresent()) {
            throw new NotFoundException("Not found product");
        }
        Category category = categoryRepository.findById(product.getCate_id()).get();
        product.setCategory(category);
        try {
            productRepository.save(ProductMapper.toProduct(product, id));
        } catch (Exception ex) {
            throw new InternalServerException("Can't update product");
        }
        return product;
    }

    @Override
    public void deleteProduct(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new NotFoundException("Not found product");
        }
        try {
            productRepository.deleteById(id);
        } catch (Exception ex) {
            throw new InternalServerException("Can't delete product");
        }
    }
}
