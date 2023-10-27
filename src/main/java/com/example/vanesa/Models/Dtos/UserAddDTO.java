package com.example.vanesa.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserAddDTO {

    private String name;
    private Integer celular;
    private String surname;
    private String email;
    private String password;
    private AddressReadDTO address;


}
