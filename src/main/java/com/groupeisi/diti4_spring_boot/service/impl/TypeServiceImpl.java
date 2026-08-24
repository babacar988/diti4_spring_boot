package com.groupeisi.diti4_spring_boot.service.impl;

import com.groupeisi.diti4_spring_boot.entity.Type;
import com.groupeisi.diti4_spring_boot.repository.TypeRepository;
import com.groupeisi.diti4_spring_boot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository repository;

    @Override
    public Type save(Type type) {
        return repository.save(type);
    }

    @Override
    public List<Type> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Type> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}

