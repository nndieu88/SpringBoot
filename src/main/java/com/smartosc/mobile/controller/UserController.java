package com.smartosc.mobile.controller;

import com.smartosc.mobile.entity.Category;
import com.smartosc.mobile.entity.Product;
import com.smartosc.mobile.model.cart.Cart;
import com.smartosc.mobile.model.counter.Counter;
import com.smartosc.mobile.model.dto.Paging;
import com.smartosc.mobile.model.mapper.UserMapper;
import com.smartosc.mobile.security.CustomUserDetails;
import com.smartosc.mobile.service.CategoryService;
import com.smartosc.mobile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

        model.addAttribute("isMobile", true);
        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);

        int currentPage = (page == null ? 0 : page - 1);
        Paging products = productService.getAllProduct(currentPage);
        model.addAttribute("products", products);

        isUser(model);
        return "/user/index";
    }

    @GetMapping("/category")
    public String indexByCategory(Model model, @RequestParam(required = false) Integer cateid,
                                  @RequestParam(required = false) Integer page) {
        model.addAttribute("isCate", true);

        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);

        int currentPage = (page == null ? 0 : page - 1);
        Paging products = productService.getAllProductByCategory(cateid, currentPage);
        model.addAttribute("products", products);
        model.addAttribute("cateid", cateid);

        isUser(model);
        return "/user/index";
    }

    @GetMapping("/search")
    public String indexByName(Model model,
                              @RequestParam(required = false) String key,
                              Integer page) {
        model.addAttribute("isSearch", true);

        List<Category> categories = categoryService.findAllCategory();
        model.addAttribute("categories", categories);

        int currentPage = (page == null ? 0 : page - 1);
        Paging products = productService.getAllByName(key, currentPage);
        model.addAttribute("products", products);

        model.addAttribute("key", key);

        isUser(model);
        return "/user/index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        isUser(model);
        return "/user/login";
    }

    @GetMapping("/register")
    public String register() {
        return "/user/register";
    }

    @GetMapping("/tran-page")
    public String tranPage() {
        return "/user/tran-page";
    }

    @GetMapping("/info/{id}")
    public String info(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id).get();
        model.addAttribute("product", product);
        List<Category> category = categoryService.findAllCategory();
        model.addAttribute("categories", category);
        return "/user/info";
    }

    @PostMapping("/info")
    public String addCart(@RequestParam("id") Long id, @ModelAttribute("myCart") Cart cart) {
        cart.addProduct(productService.getProductById(id).get());
        return "redirect:/mobile/info/" + id;
    }

    @GetMapping("/cart")
    public String cart(@ModelAttribute("myCart") Cart cart, Model model) {
        String name = "";

        model.addAttribute("products", cart.getCart());
        for (Product product : cart.getCart()) {
            name += product.toString();
        }
        model.addAttribute("nameProd", name);
        return "/user/cart";
    }

    @GetMapping("/cart/{id}")
    public String deleteCart(@PathVariable Long id, @ModelAttribute("myCart") Cart cart) {
        cart.deleteProduct(id);
        return "redirect:/mobile/cart";
    }

    @GetMapping("/404")
    public String err404(){
        return "user/404";
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
