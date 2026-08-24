package com.groupeisi.diti4_spring_boot.mapper;

import com.groupeisi.diti4_spring_boot.dto.ProduitRequestDTO;
import com.groupeisi.diti4_spring_boot.dto.ProduitResponseDTO;
import com.groupeisi.diti4_spring_boot.entity.Produit;
import org.springframework.stereotype.Component;

@Component
public class ProduitMapperE {

    public Produit toEntity(ProduitRequestDTO dto) {
        if (dto == null) {
            return null;
        }

        Produit produit = new Produit();
        produit.setLibelle(dto.getLibelle());
        produit.setPrix(dto.getPrix());

        return produit;
    }

    public ProduitResponseDTO toDTO(Produit produit) {
        if (produit == null) {
            return null;
        }

        ProduitResponseDTO dto = new ProduitResponseDTO();
        dto.setId(produit.getId());
        dto.setLibelle(produit.getLibelle());
        dto.setPrix(produit.getPrix());

        if (produit.getType() != null) {
            dto.setTypeLibelle(produit.getType().getLibelle());
        }

        return dto;
    }
}
