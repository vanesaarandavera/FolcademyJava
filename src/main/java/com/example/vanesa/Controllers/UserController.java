package com.example.vanesa.Controllers;


import com.example.vanesa.Models.Domain.UserAddDTO;
import com.example.vanesa.Models.Domain.UserReadDTO;
import com.example.vanesa.Services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }




    @GetMapping
    public ResponseEntity<List<UserReadDTO>> findAllUsers(){

        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping
    public ResponseEntity<UserReadDTO> add(@RequestBody UserAddDTO userAddDTO){

        return ResponseEntity.ok(userService.add(userAddDTO));
    }

}
