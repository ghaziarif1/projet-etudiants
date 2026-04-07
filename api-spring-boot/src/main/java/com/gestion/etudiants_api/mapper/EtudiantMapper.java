package com.gestion.etudiants_api.mapper;

import com.gestion.etudiants_api.dto.EtudiantDTO;
import com.gestion.etudiants_api.entity.Etudiant;
import org.springframework.stereotype.Component;

@Component
public class EtudiantMapper {

    public EtudiantDTO toDto(Etudiant entity) {
        return EtudiantDTO.builder()
                .id(entity.getId())
                .cin(entity.getCin())
                .nom(entity.getNom())
                .dateNaissance(entity.getDateNaissance())
                .age(entity.age())
                .email(entity.getEmail())
                .anneePremiereInscription(entity.getAnneePremiereInscription())
                .departementNom(entity.getDepartement() != null ? entity.getDepartement().getNom() : null)
                .build();
    }

    public Etudiant toEntity(EtudiantDTO dto) {
        // implémentation simple
        return Etudiant.builder()
                .id(dto.getId())
                .cin(dto.getCin())
                .nom(dto.getNom())
                .dateNaissance(dto.getDateNaissance())
                .email(dto.getEmail())
                .anneePremiereInscription(dto.getAnneePremiereInscription())
                .build();
    }
}