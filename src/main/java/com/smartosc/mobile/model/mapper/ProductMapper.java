package com.smartosc.mobile.model.mapper;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.request.UpdateProductRequest;

public class ProductMapper {
    public static Product toProduct(UpdateProductRequest productRequest,Long id){
        Product product=new Product();
        product.setId(id);
        product.setNameProd(productRequest.getNameProd());
        product.setModel(productRequest.getModel());
        product.setImage(productRequest.getImage());
        product.setPrice(productRequest.getPrice());
        product.setStatus(productRequest.getStatus());
        product.setDescription(productRequest.getDescription());
        return product;
    }
}
