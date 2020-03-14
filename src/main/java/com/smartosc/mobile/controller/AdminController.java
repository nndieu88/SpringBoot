package com.smartosc.mobile.controller;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.entity.Orders;
import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.service.CategoryService;
import com.smartosc.mobile.service.OrdersService;
import com.smartosc.mobile.service.ProductService;
import com.smartosc.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("admin")
@Controller
public class AdminController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrdersService ordersService;


    //admin-page
    @GetMapping("")
    public String index() {
        return "/admin/home";
    }

    //admin-catelories-page
    @GetMapping("/categories")
    public String category(Model model) {
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("category", categories);
        return "/admin/category";
    }

    //admin-users-page
    @GetMapping("/users")
    public String user(Model model) {
        List<UserDto> users = userService.findAllUser();
        model.addAttribute("users", users);
        return "/admin/user";
    }

    //admin-products-page
    @GetMapping("/products")
    public String product(Model model,Pageable pageable) {
        Page<Product> products = productService.getAllProduct(pageable);
        model.addAttribute("products",products);
        return "/admin/products";
    }

    //admin-orders-page
    @GetMapping("/orders")
    public String order(Model model){
        List<Orders> orders=ordersService.findAllOrder();
        return "/admin/order";
    }
}
