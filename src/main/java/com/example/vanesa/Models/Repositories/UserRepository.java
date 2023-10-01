package com.example.vanesa.Models.Repositories;

import com.example.vanesa.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
    Boolean existsByEmail(String email);

    @Override
    List<UserEntity> findAll();

    Boolean existsByCelular(int celular);
}
