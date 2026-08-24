package com.groupeisi.diti4_spring_boot.repository;

import com.groupeisi.diti4_spring_boot.entity.Produit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Produit, Long> {

    List<Produit> findByLibelle(String libelle);
    List<Produit> findByLibelleContainingAndPrixGreaterThan(String libelle, double prix);

}

