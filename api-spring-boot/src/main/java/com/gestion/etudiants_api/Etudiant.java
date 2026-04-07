package com.gestion.etudiants_api;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.gestion.etudiants_api.entity.Departement;

@Entity
@Table(name = "etudiants")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private String email;
    private int anneePremiereInscription;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    // CONSTRUCTEUR AJOUTÉ
    public Etudiant(String cin, String nom, LocalDate dateNaissance) {
        this.cin = cin;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public int age() {
        return Period.between(this.dateNaissance, LocalDate.now()).getYears();
    }
}