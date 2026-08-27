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