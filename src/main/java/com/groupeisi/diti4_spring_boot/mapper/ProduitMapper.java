package com.groupeisi.diti4_spring_boot.mapper;

import com.groupeisi.diti4_spring_boot.dto.ProduitRequestDTO;
import com.groupeisi.diti4_spring_boot.dto.ProduitResponseDTO;
import com.groupeisi.diti4_spring_boot.entity.Produit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProduitMapper {


    Produit toEntity(ProduitRequestDTO dto);

    @Mapping(source = "type.libelle", target = "typeLibelle")
    ProduitResponseDTO toDTO(Produit produit);
}
