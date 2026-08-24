package com.groupeisi.diti4_spring_boot.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ProduitRequestDTO {

    @NotBlank(message = "Le libelle est obligatoire")
    private String libelle;

    @Positive(message = "Le prix doit être positif")
    @Min(value = 11, message = "Le prix doit être supérieur à 10")
    private double prix;

    @NotNull(message = "Le type est obligatoire")
    private Long typeId; // On demande seulement l'ID du type pour créer un produit

    // Getters et Setters
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public Long getTypeId() { return typeId; }
    public void setTypeId(Long typeId) { this.typeId = typeId; }
}
