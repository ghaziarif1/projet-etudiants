package com.gestion.etudiants_api.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.time.Period;

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
    private Integer anneePremiereInscription;

    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    // Méthode calculant l'âge à partir de la date de naissance
    public int age() {
        if (dateNaissance == null) return 0;
        return Period.between(this.dateNaissance, LocalDate.now()).getYears();
    }
}