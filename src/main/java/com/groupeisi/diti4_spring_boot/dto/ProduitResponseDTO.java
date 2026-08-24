package com.groupeisi.diti4_spring_boot.dto;

public class ProduitResponseDTO {
    private Long id;
    private String libelle;
    private double prix;
    private String typeLibelle;

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getLibelle() { return libelle; }
    public void setLibelle(String libelle) { this.libelle = libelle; }
    public double getPrix() { return prix; }
    public void setPrix(double prix) { this.prix = prix; }
    public String getTypeLibelle() { return typeLibelle; }
    public void setTypeLibelle(String typeLibelle) { this.typeLibelle = typeLibelle; }
}
