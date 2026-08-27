//package com.groupeisi.diti4_spring_boot.REST;
//
//import com.groupeisi.diti4_spring_boot.dto.TypeRequestDTO;
//import com.groupeisi.diti4_spring_boot.dto.TypeResponseDTO;
//import com.groupeisi.diti4_spring_boot.entity.Type;
//import com.groupeisi.diti4_spring_boot.mapper.TypeMapper;
//import com.groupeisi.diti4_spring_boot.service.TypeService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import jakarta.validation.Valid;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RestController
//@RequestMapping("/api/types")
//public class TypeRestController {
//
//    private final TypeService typeService;
//    private final TypeMapper typeMapper;
//
//    public TypeRestController(TypeService typeService, TypeMapper typeMapper) {
//        this.typeService = typeService;
//        this.typeMapper = typeMapper;
//    }
//
//    @GetMapping
//    public List<TypeResponseDTO> getList() {
//        return typeService.findAll().stream()
//                .map(typeMapper::toDTO)
//                .collect(Collectors.toList());
//    }
//
//    @PostMapping
//    public ResponseEntity<TypeResponseDTO> save(@Valid @RequestBody TypeRequestDTO dto) {
//        Type type = typeMapper.toEntity(dto);
//        Type savedType = typeService.save(type);
//        return ResponseEntity.status(HttpStatus.CREATED).body(typeMapper.toDTO(savedType));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Type> delete(@PathVariable Long id){
//        Optional<Type> type = typeService.findById(id);
//        if(!type.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        typeService.delete(id);
//        return ResponseEntity.status(HttpStatus.OK).build();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Type> getById(@PathVariable Long id) {
//        Optional<Type> type = typeService.findById(id);
//        if (!type.isPresent()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(type.get());
//    }
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<Type> edit(@PathVariable Long id, @RequestBody Type type){
//        Optional<Type> typeUpd = typeService.findById(id);
//        if(!typeUpd.isPresent()){
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//        }
//
//        typeUpd.get().setLibelle(type.getLibelle());
//        typeService.save(typeUpd.get());
//
//        return ResponseEntity.status(HttpStatus.OK).body(typeUpd.get());
//    }
//}
//


package com.groupeisi.diti4_spring_boot.REST;

import com.groupeisi.diti4_spring_boot.dto.TypeRequestDTO;
import com.groupeisi.diti4_spring_boot.dto.TypeResponseDTO;
import com.groupeisi.diti4_spring_boot.entity.Type;
import com.groupeisi.diti4_spring_boot.exception.ResourceNotFoundException;
import com.groupeisi.diti4_spring_boot.mapper.TypeMapper;
import com.groupeisi.diti4_spring_boot.service.TypeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/types")
public class TypeRestController {

    private final TypeService typeService;
    private final TypeMapper typeMapper;

    public TypeRestController(TypeService typeService, TypeMapper typeMapper) {
        this.typeService = typeService;
        this.typeMapper = typeMapper;
    }

    @GetMapping
    public List<TypeResponseDTO> getList() {
        return typeService.findAll().stream()
                .map(typeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<TypeResponseDTO> save(@Valid @RequestBody TypeRequestDTO dto) {
        Type type = typeMapper.toEntity(dto);
        Type savedType = typeService.save(type);
        return ResponseEntity.status(HttpStatus.CREATED).body(typeMapper.toDTO(savedType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeResponseDTO> getById(@PathVariable Long id) {
        Type type = typeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Le type avec l'ID " + id + " n'existe pas."));
        return ResponseEntity.ok(typeMapper.toDTO(type));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TypeResponseDTO> edit(@PathVariable Long id,
                                                @Valid @RequestBody TypeRequestDTO dto) {
        Type existing = typeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Le type avec l'ID " + id + " n'existe pas."));

        existing.setLibelle(dto.getLibelle());
        Type updated = typeService.save(existing);

        return ResponseEntity.ok(typeMapper.toDTO(updated));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        typeService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Le type avec l'ID " + id + " n'existe pas."));
        typeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}