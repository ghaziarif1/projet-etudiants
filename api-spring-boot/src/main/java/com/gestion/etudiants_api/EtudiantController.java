package com.gestion.etudiants_api;

import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/etudiants")
@CrossOrigin(origins = "*")  // Important pour Flutter
public class EtudiantController {

    private final EtudiantRepository repository;

    public EtudiantController(EtudiantRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Etudiant> getAllEtudiants() {
        return repository.findAll();
    }
}