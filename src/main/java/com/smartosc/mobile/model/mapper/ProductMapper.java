package com.smartosc.mobile.model.mapper;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.dto.ProductDto;
import com.smartosc.mobile.model.request.CreateProduct;
import com.smartosc.mobile.model.request.UpdateProductRequest;

public class ProductMapper {
    public static ProductDto toProductDto(Product product){
        ProductDto productDto=new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getNameProd());
        productDto.setCate(product.getCategory().getNameCate());
        productDto.setImage(product.getImage());
        productDto.setPrice(product.getPrice());
        productDto.setDescription(product.getDescription());
        return productDto;
    }

    public static Product toProduct(CreateProduct createProduct){
        Product product=new Product();
        product.setNameProd(createProduct.getNameProd());
        product.setCategory(createProduct.getCategory());
        product.setImage(createProduct.getImage());
        product.setPrice(createProduct.getPrice());
        product.setStatus(createProduct.getStatus());
        product.setDescription(createProduct.getDescription());
        return product;
    }

    public static Product toProduct(UpdateProductRequest productRequest,Long id){
        Product product=new Product();
        product.setId(id);
        product.setNameProd(productRequest.getNameProd());
        product.setCategory(productRequest.getCategory());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setStatus(productRequest.getStatus());
        product.setDescription(productRequest.getDescription());
        return product;
    }
}
