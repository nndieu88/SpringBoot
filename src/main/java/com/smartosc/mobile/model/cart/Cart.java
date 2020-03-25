package com.smartosc.mobile.model.cart;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.service.ProductService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Autowired
    private ProductService productService;

    private int count = 0;

    private Map<Long, Product> cart = new HashMap<>();

    public void addProduct(Product product) {
        cart.put(product.getId(), product);
        count = cart.size();

    }

    public void deleteProduct(Long id) {
        cart.remove(id);
        count = cart.size();
    }

    public List<Product> getCart() {
        return new ArrayList<>(cart.values());
    }

    public Long totalPrice() {
        Long price = 0L;
        List<Product> products = new ArrayList<>(cart.values());
        for (Product product : products) {
            price += product.getPrice();
        }
        return price;
    }
}
