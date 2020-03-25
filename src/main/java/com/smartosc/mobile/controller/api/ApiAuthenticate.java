package com.smartosc.mobile.controller.api;

import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.model.mapper.UserMapper;
import com.smartosc.mobile.model.request.AuthenticationRequest;
import com.smartosc.mobile.security.CookieUtil;
import com.smartosc.mobile.security.CustomUserDetails;
import com.smartosc.mobile.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
public class ApiAuthenticate {
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    private final String NAME_TOKEN = "JWT_TOKEN";

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest req, HttpServletResponse res) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gen token
        String token = jwtTokenUtil.generateToken((CustomUserDetails) authentication.getPrincipal());

        // Luu cookie
        CookieUtil.create(res, NAME_TOKEN, token);

        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String role = userDetails.getUser().getRole().getNameRole();

        return ResponseEntity.ok(role);
    }

    @GetMapping("/current-user")
    public ResponseEntity<?> getCurrentUser() {
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(UserMapper.toUserDto(userDetails.getUser()));
    }
}
