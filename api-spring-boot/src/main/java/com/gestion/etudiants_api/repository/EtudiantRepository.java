package com.gestion.etudiants_api.repository;

import com.gestion.etudiants_api.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Q9 : requête dérivée (Spring Data JPA)
    List<Etudiant> findByAnneePremiereInscription(int annee);

    // Bonus (utile pour le CRUD) :
    List<Etudiant> findByDepartementId(Long departementId);
}