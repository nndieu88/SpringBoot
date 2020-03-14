package com.smartosc.mobile.controller.api;

import com.smartosc.mobile.entity.User;
import com.smartosc.mobile.model.dto.UserDto;
import com.smartosc.mobile.model.request.AuthenticationRequest;
import com.smartosc.mobile.model.request.CreateUserRequest;
import com.smartosc.mobile.model.request.UpdateUserRequest;
import com.smartosc.mobile.security.CustomUserDetails;
import com.smartosc.mobile.security.JwtTokenUtil;
import com.smartosc.mobile.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("admins/users")
public class ApiUserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @ApiOperation(value = "get all user", response = UserDto.class, responseContainer = "List")
    @ApiResponses({
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping("")
    public ResponseEntity<?> getAllUser() {
        List<User> userDtos = userService.listUser();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @ApiOperation(value = "get user by id", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "not found user"),
            @ApiResponse(code = 500, message = "")
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        UserDto userDto = userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @ApiOperation(value = "create user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 400, message = "email already exists in the system"),
            @ApiResponse(code = 500, message = "")
    })
    @PostMapping("")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserDto userDto = userService.createUser(createUserRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @ApiOperation(value = "update user", response = UserDto.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "not found user"),
            @ApiResponse(code = 500, message = "")
    })
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@Valid @PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        UserDto userDto = userService.updateUser(id, updateUserRequest);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @ApiOperation(value = "delete user", response = String.class)
    @ApiResponses({
            @ApiResponse(code = 404, message = "not found user"),
            @ApiResponse(code = 500, message = "")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(@Valid @RequestBody AuthenticationRequest req) {
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

        return ResponseEntity.ok(token);
    }
}
