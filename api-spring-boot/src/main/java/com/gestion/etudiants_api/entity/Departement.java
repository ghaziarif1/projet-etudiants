package com.gestion.etudiants_api.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "departements")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
}