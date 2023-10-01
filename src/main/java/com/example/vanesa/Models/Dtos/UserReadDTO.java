package com.example.vanesa.Models.Dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReadDTO {
    Integer id;
    String name;
    int celular;
    String surname;
    String email;
}
