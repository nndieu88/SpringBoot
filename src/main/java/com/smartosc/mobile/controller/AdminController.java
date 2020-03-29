package com.smartosc.mobile.controller;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.entity.Orders;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.model.mapper.UserMapper;
import com.smartosc.mobile.security.CustomUserDetails;
import com.smartosc.mobile.service.CategoryService;
import com.smartosc.mobile.service.OrdersService;
import com.smartosc.mobile.service.ProductService;
import com.smartosc.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(Model model) {
        isUser(model);
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
    public String product(Model model, @RequestParam(required = false) Integer page) {
        int currentPage = (page == null ? 0 : page - 1);
        Paging products = productService.getAllProduct(currentPage);
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "/admin/products";
    }

    //admin-orders-page
    @GetMapping("/orders")
    public String order(Model model, @RequestParam(required = false) Integer page) {
        int currentPage = (page == null ? 0 : page - 1);

        Paging orders = ordersService.findAllOrder(currentPage);
        model.addAttribute("orders", orders);
        return "/admin/order";
    }


    public void isUser(Model model) {
        if (!SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            model.addAttribute("isUser", true);
            addUser(model);
        }
    }

    public void addUser(Model model) {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("currentUser", UserMapper.toUserDto(userDetails.getUser()));
    }
}
