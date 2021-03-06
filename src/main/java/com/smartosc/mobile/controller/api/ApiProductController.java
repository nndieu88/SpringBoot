package com.smartosc.mobile.controller.api;

import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.model.request.CreateCategoryRequest;
import com.smartosc.mobile.model.request.CreateProduct;
import com.smartosc.mobile.model.request.UpdateProductRequest;
import com.smartosc.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("admins/products")
public class ApiProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(@RequestParam Integer page) {
        int currentPage = (page == null ? 0 : page - 1);
        Paging products = productService.getAllProduct(currentPage);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

//    @GetMapping("/cate")
//    public ResponseEntity<?> getAll(@RequestParam Long id)

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> createProduct(@RequestBody CreateProduct product) {
        productService.createProduct(product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody UpdateProductRequest product) {
        productService.updateProduct(id, product);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
