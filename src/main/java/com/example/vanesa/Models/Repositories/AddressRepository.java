package com.example.vanesa.Models.Repositories;

import com.example.vanesa.Models.Entities.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository  extends JpaRepository<AddressEntity, Integer> {
}
