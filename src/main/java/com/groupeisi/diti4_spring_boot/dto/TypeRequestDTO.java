package com.groupeisi.diti4_spring_boot.dto;


import jakarta.validation.constraints.NotBlank;

public class TypeRequestDTO {

    @NotBlank(message = "Le libellé de la catégorie ne doit pas être vide")
    private String libelle;

    // Getter et Setter
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
}
