package com.example.vanesa.Services;

import com.example.vanesa.Exceptions.ExceptionKinds.UserBadRequestException;
import com.example.vanesa.Exceptions.ExceptionKinds.UserNotFoundException;
import com.example.vanesa.Models.Dtos.UserAddDTO;
import com.example.vanesa.Models.Dtos.UserEditDTO;
import com.example.vanesa.Models.Dtos.UserReadDTO;
import com.example.vanesa.Models.Entities.UserEntity;
import com.example.vanesa.Models.Mappers.UserMapper;
import com.example.vanesa.Models.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService<UserEditDTO> {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserReadDTO> findAll() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::userEntityToUserReadDTO)
                .collect(Collectors.toList());
    }

    public UserReadDTO add(UserAddDTO userAddDTO) {
        try {
            Boolean emailExist = userRepository.existsByEmail(userAddDTO.getEmail());
            if (emailExist) {
                throw new UserBadRequestException("ya existe un usuario con este email");
            }

            UserEntity newUserEntity = userMapper.userAddDTOToUserEntity(userAddDTO);
            UserEntity savedUserEntity = userRepository.save(newUserEntity);

            return userMapper.userEntityToUserReadDTO(savedUserEntity);
        } catch (UserBadRequestException e) {
            throw new RuntimeException("Error al agregar usuario: " + e.getMessage(), e);
        }
    }

    public UserReadDTO addUser(UserAddDTO userAddDTO) {
        try {
            Boolean celularExist = userRepository.existsByCelular(userAddDTO.getCelular());

            if (celularExist) {
                throw new UserBadRequestException("ya existe un usuario con este celular");
            }

            UserEntity newUserEntity = userMapper.userAddDTOToUserEntity(userAddDTO);
            UserEntity savedUserEntity = userRepository.save(newUserEntity);

            return userMapper.userEntityToUserReadDTO(savedUserEntity);
        } catch (UserBadRequestException e) {
            throw new RuntimeException("Error al agregar usuario: " + e.getMessage(), e);
        }
    }


    public UserReadDTO findById(Integer userId) {
        try {
            UserEntity userEntity = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("No se encontró un usuario con ese identificador"));

            return userMapper.userEntityToUserReadDTO(userEntity);
        } catch (UserNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error al buscar usuario por ID", e);
        }
    }
    
    public UserReadDTO edit(Integer userId, com.example.vanesa.Models.Dtos.UserEditDTO user) {
        try {
            UserEntity oldUser = userRepository.findById(userId)
                    .orElseThrow(()-> new UserNotFoundException("No se encontro un usuario con ese identificador"));

            if(!user.getName().isBlank()) oldUser.setName(user.getName());
            if(!user.getSurname().isBlank()) oldUser.setSurname(user.getSurname());

            UserEntity newUser = userRepository.save(oldUser);

            return userMapper.userEntityToUserReadDTO(newUser);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public UserReadDTO deleteById(Integer userId) {
        try{
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(()-> new UserNotFoundException("No se encontro un usuario con ese identificador"));
                userRepository.delete(user);

                return userMapper.userEntityToUserReadDTO(user);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

