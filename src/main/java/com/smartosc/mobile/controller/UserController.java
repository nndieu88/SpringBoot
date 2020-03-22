package com.smartosc.mobile.controller;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.model.cart.Cart;
import com.smartosc.mobile.model.counter.Counter;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.service.CategoryService;
import com.smartosc.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/mobile")
@Controller
@SessionAttributes({"myCounter", "myCart"})
public class UserController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ProductService productService;

    @ModelAttribute("myCounter")
    public Counter setCounter() {
        return new Counter();
    }

    @ModelAttribute("myCart")
    public Cart setCart() {
        return new Cart();
    }

    @GetMapping("")
    public String index(@ModelAttribute("myCounter") Counter counter, @ModelAttribute("myCart") Cart car, Model model, @RequestParam(required = false) Integer page) {
        counter.increment();
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);

        int currentPage = (page == null ? 0 : page - 1);
        Paging products = productService.getAllProduct(currentPage);
        model.addAttribute("products", products);
        return "/user/index";
    }

    @GetMapping("/category")
    public String indexByCategory(Model model, @RequestParam(required = false) String nameCate, Integer page) {
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);

        int currentPage = (page == null ? 0 : page - 1);
        Paging products=productService.getAllProductByCategory(nameCate,page);
        model.addAttribute("products",products);
        return "/user/index";
    }

    @GetMapping("/login")

    public String login() {
        return "/user/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

    @GetMapping("/cart")
    public String cart() {
        return "/user/cart";
    }

    @GetMapping("/info")
    public String info() {
        return "/user/info";
    }

}
