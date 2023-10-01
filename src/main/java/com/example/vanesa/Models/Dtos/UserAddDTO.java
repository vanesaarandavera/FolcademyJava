package com.example.vanesa.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {


    Integer id;
    String name;
    Integer celular;
    String surname;
    String email;
    private String password;


    public String getPassword() {
        return this.password;
    }
}
