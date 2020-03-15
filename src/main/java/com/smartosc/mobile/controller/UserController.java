package com.smartosc.mobile.controller;

import com.smartosc.mobile.model.cart.Cart;
import com.smartosc.mobile.model.counter.Counter;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.List;

@RequestMapping("users")
@Controller
@SessionAttributes({"myCounter", "myCart"})
public class UserController {
    @ModelAttribute("myCounter")
    public Counter setCounter() {
        return new Counter();
    }

    @ModelAttribute("myCart")
    public Cart setCart(){
        return new Cart();
    }

    @GetMapping("")
    public String index(@ModelAttribute("myCounter") Counter counter) {
        counter.increment();
        return "/user/index";
    }


}
