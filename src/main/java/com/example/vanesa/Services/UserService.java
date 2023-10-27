package com.example.vanesa.Services;

import com.example.vanesa.Exceptions.ExceptionKinds.UserBadRequestException;
import com.example.vanesa.Exceptions.ExceptionKinds.UserNotFoundException;
import com.example.vanesa.Models.Dtos.UserAddDTO;
import com.example.vanesa.Models.Dtos.UserEditDTO;
import com.example.vanesa.Models.Dtos.UserReadDTO;
import com.example.vanesa.Models.Entities.UserEntity;
import com.example.vanesa.Models.Mappers.UserMapper;
import com.example.vanesa.Models.Repositories.UserRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Collections;
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

    public Page<UserReadDTO> findAll(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("name").ascending());

        Page<UserEntity> userEntities = userRepository.findAll(pageable);

        List<UserReadDTO> users = userEntities
                .stream()
                .map(userEntity -> userMapper.userEntityToUserReadDTO(userEntity    ))
                .collect(Collectors.toList());

        return new PageImpl<>(users, pageable, userEntities.getTotalElements());
    }

    public UserReadDTO add(UserAddDTO userAddDTO) {
        try {
            //Verifica si existen usuarios con esos datos
            Boolean nameExist = userRepository.existsByName(userAddDTO.getName());
            Boolean emailExist = userRepository.existsByEmail(userAddDTO.getEmail());
            Boolean celularExist = userRepository.existsByCelular(userAddDTO.getCelular());

            if (nameExist){
                throw new UserBadRequestException("ya existe un usuario con este nombre");
            } else {
                if (emailExist) {
                    throw new UserBadRequestException("ya existe un usuario con este email");
                }else{
                    if(celularExist){
                        throw new UserBadRequestException("ya existe un usuario con este numero de celular");
                    }
                }
            }

            UserEntity newUserEntity = userMapper.userAddDTOToUserEntity(userAddDTO);
            UserEntity savedUserEntity = userRepository.save(newUserEntity);

            return userMapper.userEntityToUserReadDTO(savedUserEntity);
        } catch (UserBadRequestException e) {
            throw new RuntimeException("Error al agregar usuario: " + e.getMessage(), e);
        }
    }



    public UserReadDTO findById(Integer userId) {
        // Verificar si el usuario con el ID proporcionado existe en la base de datos
        boolean existeUsuario = userRepository.existsById(userId);

        if (!existeUsuario) {
            throw new UserNotFoundException("No se encontró un usuario con el ID " + userId);
        }

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
        // Verificar si el usuario con el ID proporcionado existe en la base de datos
        boolean existeUsuario = userRepository.existsById(userId);

        if (!existeUsuario) {
            throw new UserNotFoundException("No se encontró un usuario con el ID " + userId);
        }

        try {
            UserEntity oldUser = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("No se encontró un usuario con ese identificador"));

            if (!user.getName().isBlank()) {
                oldUser.setName(user.getName());
            }
            if (!user.getSurname().isBlank()) {
                oldUser.setSurname(user.getSurname());
            }

            UserEntity newUser = userRepository.save(oldUser);

            return userMapper.userEntityToUserReadDTO(newUser);
        } catch (Exception e) {
            throw new RuntimeException("Error al editar usuario por ID", e);
        }
    }


    public UserReadDTO deleteById(Integer userId) {
        // Verificar si el usuario con el ID proporcionado existe en la base de datos
        boolean existeUsuario = userRepository.existsById(userId);

        if (!existeUsuario) {
            throw new UserNotFoundException("No se encontró un usuario con el ID " + userId);
        }

        try {
            UserEntity user = userRepository.findById(userId)
                    .orElseThrow(() -> new UserNotFoundException("No se encontró un usuario con ese identificador"));

            userRepository.delete(user);

            return userMapper.userEntityToUserReadDTO(user);
        } catch (Exception e) {
            throw new RuntimeException("Error al eliminar usuario por ID", e);
        }
    }

}

