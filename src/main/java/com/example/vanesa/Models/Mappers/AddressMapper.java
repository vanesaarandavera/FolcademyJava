package com.example.vanesa.Models.Mappers;

import com.example.vanesa.Models.Dtos.AddressReadDTO;
import com.example.vanesa.Models.Entities.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper {

    public AddressReadDTO addressEntityToAddressReadDTO(AddressEntity addressEntity) {
        AddressReadDTO addressReadDTO = new AddressReadDTO();
        addressReadDTO.setNumber(addressEntity.getNumber());
        addressReadDTO.setStreet(addressEntity.getStreet());
        return addressReadDTO;
    }
    public AddressEntity addressReadDTOToAddressEntity(AddressReadDTO addressReadDTO) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(addressReadDTO.getStreet());
        addressEntity.setNumber(addressReadDTO.getNumber());

        return addressEntity;
    }
}
