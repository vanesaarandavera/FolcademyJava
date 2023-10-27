package com.example.vanesa.Controllers;


import com.example.vanesa.Models.Dtos.UserAddDTO;
import com.example.vanesa.Models.Dtos.UserEditDTO;
import com.example.vanesa.Models.Dtos.UserReadDTO;
import com.example.vanesa.Services.UserService;
import org.springframework.data.domain.Page;
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
    public ResponseEntity<Page<UserReadDTO>> findAll(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size) {
        return ResponseEntity.ok(userService.findAll(page, size));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<UserReadDTO> findById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.findById(userId));
    }


    @PostMapping
    public ResponseEntity<UserReadDTO> add(@RequestBody UserAddDTO userAddDTO){

        return ResponseEntity.ok(userService.add(userAddDTO));
    }
    @PostMapping("/contacto")
    public ResponseEntity<UserReadDTO> addUser(@RequestBody UserAddDTO userAddDTO){

        return ResponseEntity.ok(userService.add(userAddDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<UserReadDTO> deleteById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.deleteById(userId));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserReadDTO> edit(@PathVariable Integer userId, @RequestBody UserEditDTO user) {
        return ResponseEntity.ok(userService.edit(userId, user));
    }
}
