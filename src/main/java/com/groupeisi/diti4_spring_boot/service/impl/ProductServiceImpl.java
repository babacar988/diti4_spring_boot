package com.groupeisi.diti4_spring_boot.service.impl;

import com.groupeisi.diti4_spring_boot.entity.Produit;
import com.groupeisi.diti4_spring_boot.repository.ProductRepository;
import com.groupeisi.diti4_spring_boot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public Produit save(Produit product) {
        return  repository.save(product);
    }
//
//    @Override
//    public List<Produit> findAll() {
//        return repository.findAll();
//    }

    @Override
    public Page<Produit> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Optional<Produit> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}