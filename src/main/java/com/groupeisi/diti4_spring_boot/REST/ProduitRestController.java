package com.groupeisi.diti4_spring_boot.REST;

import com.groupeisi.diti4_spring_boot.dto.ProduitRequestDTO;
import com.groupeisi.diti4_spring_boot.dto.ProduitResponseDTO;
import com.groupeisi.diti4_spring_boot.entity.Produit;
import com.groupeisi.diti4_spring_boot.entity.Type;
import com.groupeisi.diti4_spring_boot.exception.ResourceNotFoundException;
import com.groupeisi.diti4_spring_boot.mapper.ProduitMapper;
import com.groupeisi.diti4_spring_boot.service.ProductService;
import com.groupeisi.diti4_spring_boot.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;


@RestController
@RequestMapping("/api/produits")
public class ProduitRestController {


    @Autowired
    private ProductService productService;

    @Autowired
    private TypeService typeService;



    @Autowired
    private ProduitMapper produitMapper;


    public ProduitRestController(ProductService productService, TypeService typeService, ProduitMapper produitMapper) {
        this.productService = productService;
        this.typeService = typeService;
        this.produitMapper = produitMapper;
    }




    @GetMapping
    public Page<ProduitResponseDTO> getList(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());

        Page<Produit> produitPage = productService.findAll(pageable);

        return produitPage.map(produitMapper::toDTO);
    }



    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody ProduitRequestDTO dto) {
        Optional<Type> typeOpt = typeService.findById(dto.getTypeId());
        if (!typeOpt.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur : Le type avec l'ID " + dto.getTypeId() + " n'existe pas.");
        }

        Produit produit = produitMapper.toEntity(dto);
        produit.setType(typeOpt.get());

        Produit savedProduit = productService.save(produit);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produitMapper.toDTO(savedProduit));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        Optional<Produit> produit = productService.findById(id);
        if(!produit.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        productService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }




    @GetMapping("/{id}")
    public ResponseEntity<ProduitResponseDTO> getById(@PathVariable Long id) {
        Produit produit = productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le produit avec l'ID " + id + " n'existe pas en base de données."));

        return ResponseEntity.ok(produitMapper.toDTO(produit));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<String> edit(@PathVariable Long id,@RequestBody Produit  produit){
        Optional<Produit> produitUpd = productService.findById(id);
        if(!produitUpd.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        produitUpd.get().setLibelle(produit.getLibelle());
        produitUpd.get().setPrix(produit.getPrix());

        productService.save(produitUpd.get());

        return ResponseEntity.status(HttpStatus.OK).body("produit modifie avec succes");
    }


}

