package com.gestion.etudiants_api;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "etudiants")
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 8)
    private String cin;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    // Constructeurs
    public Etudiant() {}
    public Etudiant(String cin, String nom, LocalDate dateNaissance) {
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCin() { return cin; }
    public void setCin(String cin) { this.cin = cin; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
}