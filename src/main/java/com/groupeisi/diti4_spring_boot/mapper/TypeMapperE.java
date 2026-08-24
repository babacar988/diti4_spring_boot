package com.groupeisi.diti4_spring_boot.mapper;

import com.groupeisi.diti4_spring_boot.dto.TypeRequestDTO;
import com.groupeisi.diti4_spring_boot.dto.TypeResponseDTO;
import com.groupeisi.diti4_spring_boot.entity.Type;
import org.springframework.stereotype.Component;

@Component
public class TypeMapperE {

    // Convertit le DTO de requête en Entité Type
    public Type toEntity(TypeRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Type type = new Type();
        type.setLibelle(dto.getLibelle());

        return type;
    }

    // Convertit l'Entité Type en DTO de réponse
    public TypeResponseDTO toDTO(Type type) {
        if (type == null) {
            return null;
        }

        TypeResponseDTO dto = new TypeResponseDTO();
        dto.setId(type.getId());
        dto.setLibelle(type.getLibelle());

        return dto;
    }
}
