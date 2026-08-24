package com.groupeisi.diti4_spring_boot.mapper;

import com.groupeisi.diti4_spring_boot.dto.TypeRequestDTO;
import com.groupeisi.diti4_spring_boot.dto.TypeResponseDTO;
import com.groupeisi.diti4_spring_boot.entity.Type;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TypeMapper {

    Type toEntity(TypeRequestDTO dto);

    TypeResponseDTO toDTO(Type type);
}
