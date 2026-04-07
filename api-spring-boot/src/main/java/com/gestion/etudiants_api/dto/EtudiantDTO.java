package com.gestion.etudiants_api.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EtudiantDTO {
    private Long id;
    private String cin;
    private String nom;
    private LocalDate dateNaissance;
    private int age;
    private String email;
    private int anneePremiereInscription;
    private String departementNom;   // au lieu de l'objet complet
}