package com.smartosc.mobile.service.Impl;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.exception.DuplicateRecordException;
import com.smartosc.mobile.exception.InternalServerException;
import com.smartosc.mobile.exception.NotFoundException;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.model.dto.ProductDto;
import com.smartosc.mobile.model.mapper.ProductMapper;
import com.smartosc.mobile.model.request.CreateProduct;
import com.smartosc.mobile.model.request.UpdateProductRequest;
import com.smartosc.mobile.repository.CategoryRepository;
import com.smartosc.mobile.repository.ProductRepository;
import com.smartosc.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Paging getAllProduct(int page) {
        Page<Product> products = productRepository.findAll(PageRequest.of(page, 8, Sort.by("price").descending()));
        List<ProductDto> productDto = new ArrayList<>();
        for (Product product : products.getContent()) {
            productDto.add(ProductMapper.toProductDto(product));
        }

        Paging paging = new Paging();
        paging.setContent(productDto);
        paging.setCurrentPage(page + 1);
        paging.setHasNext(products.hasNext());
        paging.setHasPrev(products.hasPrevious());
        int totalPage = (products.getTotalPages() == 0 ? 1 : products.getTotalPages());
        paging.setTotalPage(totalPage);
        return paging;
    }

    @Override
    public Paging getAllProductByCategory(Integer id, int page) {
        Page<Product> products = productRepository.findAllByCate(id, PageRequest.of(page, 8, Sort.by("price").descending()));
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products.getContent()) {
            productDtos.add(ProductMapper.toProductDto(product));
        }

        Paging paging = new Paging();
        paging.setContent(productDtos);
        paging.setCurrentPage(page + 1);
        paging.setHasNext(products.hasNext());
        paging.setHasPrev(products.hasPrevious());
        int totalPage = (products.getTotalPages() == 0 ? 1 : products.getTotalPages());
        paging.setTotalPage(totalPage);
        return paging;
    }

    @Override
    public Paging getAllByName(String name, int page) {
        Page<Product> products = productRepository.findAllByName(name, PageRequest.of(page, 8, Sort.by("price").descending()));
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product : products.getContent()) {
            productDtos.add(ProductMapper.toProductDto(product));
        }

        Paging paging = new Paging();
        paging.setContent(productDtos);
        paging.setCurrentPage(page + 1);
        int totalPage = (products.getTotalPages() == 0 ? 1 : products.getTotalPages());
        paging.setTotalPage(totalPage);
        paging.setHasPrev(products.hasPrevious());
        paging.setHasNext(products.hasNext());
        return paging;
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
