package com.smartosc.mobile.controller;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.model.cart.Cart;
import com.smartosc.mobile.model.counter.Counter;
import com.smartosc.mobile.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RequestMapping("/mobile")
@Controller
@SessionAttributes({"myCounter", "myCart"})
public class UserController {
    @Autowired
    private CategoryService categoryService;

    @ModelAttribute("myCounter")
    public Counter setCounter() {
        return new Counter();
    }

    @ModelAttribute("myCart")
    public Cart setCart() {
        return new Cart();
    }

    @GetMapping("")
    public String index(@ModelAttribute("myCounter") Counter counter, @ModelAttribute("myCart") Cart car, Model model) {
        counter.increment();
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);
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
    public String cart(){
        return "/user/cart";
    }

    @GetMapping("/info")
    public String info(){
        return "/user/info";
    }

}
