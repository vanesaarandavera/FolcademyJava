package com.example.vanesa.Models.Mappers;

import com.example.vanesa.Models.Dtos.UserAddDTO;
import com.example.vanesa.Models.Dtos.UserReadDTO;
import com.example.vanesa.Models.Entities.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserReadDTO userEntityToUserReadDTO(UserEntity userEntity) {
        UserReadDTO userReadDTO = new UserReadDTO();

        userReadDTO.setId(userEntity.getId());
        userReadDTO.setName(userEntity.getName());
        userReadDTO.setCelular(userEntity.getCelular());

        userReadDTO.setSurname(userEntity.getSurname());
        userReadDTO.setEmail(userEntity.getEmail());
        return userReadDTO;

    }

    public UserEntity userAddDTOToUserEntity(UserAddDTO userAddDTO) {
        UserEntity userEntity = new UserEntity();

        userEntity.setName(userAddDTO.getName());
        userEntity.setSurname(userAddDTO.getSurname());
        userEntity.setCelular(userAddDTO.getCelular());

        userEntity.setEmail(userAddDTO.getEmail());
        userEntity.setPassword(userAddDTO.getPassword());
        return userEntity;
    }
}
