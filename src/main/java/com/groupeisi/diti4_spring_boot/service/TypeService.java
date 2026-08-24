package com.groupeisi.diti4_spring_boot.service;

import com.groupeisi.diti4_spring_boot.entity.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {

    Type save(Type type);

    List<Type> findAll();

    Optional<Type> findById(Long id);

    void delete(Long id);

}
