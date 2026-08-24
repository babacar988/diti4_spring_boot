package com.groupeisi.diti4_spring_boot.service;

import com.groupeisi.diti4_spring_boot.entity.Produit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Produit save(Produit product);

    //    List<Produit> findAll();
    Page<Produit> findAll(Pageable pageable);

    Optional<Produit> findById(Long id);

    void delete(Long id);
}