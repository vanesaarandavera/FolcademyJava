package com.example.vanesa.Models.Repositories;

import com.example.vanesa.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
    Boolean existsByEmail(String email);
    Boolean existsByName(String name);
    Boolean existsByCelular(Integer celular);
    @Override
    List<UserEntity> findAll();
    List<UserEntity> findAllByNameAndSurname(String name,String surname);

    Optional<UserEntity> findOneByEmail(String email);
}
