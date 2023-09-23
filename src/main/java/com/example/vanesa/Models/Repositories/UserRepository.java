package com.example.vanesa.Models.Repositories;

import com.example.vanesa.Models.Entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository extends JpaRepository <UserEntity, Integer> {
}
