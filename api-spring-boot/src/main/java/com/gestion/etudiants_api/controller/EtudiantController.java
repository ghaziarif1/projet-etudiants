package com.gestion.etudiants_api.controller;

import com.gestion.etudiants_api.dto.EtudiantDTO;
import com.gestion.etudiants_api.service.EtudiantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
public class EtudiantController {

    private final EtudiantService service;

    public EtudiantController(EtudiantService service) {
        this.service = service;
    }

    @Operation(summary = "Lister tous les étudiants")
    @GetMapping
    public List<EtudiantDTO> getAll(@RequestParam(required = false) Integer annee, @RequestParam(required = false) Long departementId) {
        if (annee != null) {
            return service.findByAnnee(annee);   // Q9
        }
        if (departementId != null) {
            return service.findByDepartementId(departementId);
        }
        return service.findAll();
    }

    @Operation(summary = "Récupérer un étudiant par ID")
    @GetMapping("/{id}")
    public ResponseEntity<EtudiantDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @Operation(summary = "Créer un étudiant")
    @PostMapping
    public ResponseEntity<EtudiantDTO> create(@RequestBody EtudiantDTO dto) {
        EtudiantDTO saved = service.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @Operation(summary = "Mettre à jour un étudiant")
    @PutMapping("/{id}")
    public ResponseEntity<EtudiantDTO> update(@PathVariable Long id, @RequestBody EtudiantDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Supprimer un étudiant")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}