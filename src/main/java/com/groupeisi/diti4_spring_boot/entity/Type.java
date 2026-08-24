package com.groupeisi.diti4_spring_boot.entity;


import jakarta.persistence.*;
import jdk.jfr.DataAmount;

import java.util.List;

@Entity
@Table(name = "types")


public class Type {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String libelle;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL)
    private List<com.groupeisi.diti4_spring_boot.entity.Produit> products;

    public Type() {
    }
    public Type(Long id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<com.groupeisi.diti4_spring_boot.entity.Produit> getProducts() {
        return products;
    }

    public void setProducts(List<com.groupeisi.diti4_spring_boot.entity.Produit> products) {
        this.products = products;
    }

}
