package com.groupeisi.diti4_spring_boot.repository;

import com.groupeisi.diti4_spring_boot.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    List<Type> findByLibelle(String libelle);
    List<Type> findByLibelleContaining(String libelle);
}

