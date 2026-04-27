package com.gestion.etudiants_api.service;

import com.gestion.etudiants_api.dto.EtudiantDTO;
import com.gestion.etudiants_api.entity.Etudiant;
import com.gestion.etudiants_api.mapper.EtudiantMapper;
import com.gestion.etudiants_api.repository.EtudiantRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EtudiantService {

    private final EtudiantRepository repository;
    private final EtudiantMapper mapper;

    public EtudiantService(EtudiantRepository repository, EtudiantMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Cacheable(value = "etudiants")
    public List<EtudiantDTO> findAll() {
        return repository.findAll().stream().map(mapper::toDto).toList();
    }

    public EtudiantDTO findById(Long id) {
        Etudiant etudiant = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé avec l'id : " + id));
        return mapper.toDto(etudiant);
    }

    @CacheEvict(value = "etudiants", allEntries = true)
    public EtudiantDTO save(EtudiantDTO dto) {
        Etudiant entity = mapper.toEntity(dto);
        Etudiant saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @CacheEvict(value = "etudiants", allEntries = true)
    public EtudiantDTO update(Long id, EtudiantDTO dto) {
        Etudiant existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Étudiant non trouvé"));

        existing.setCin(dto.getCin());
        existing.setNom(dto.getNom());
        existing.setDateNaissance(dto.getDateNaissance());
        existing.setEmail(dto.getEmail());
        existing.setAnneePremiereInscription(dto.getAnneePremiereInscription());

        Etudiant updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    @CacheEvict(value = "etudiants", allEntries = true)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Étudiant non trouvé");
        }
        repository.deleteById(id);
    }

    // Q9 exposé via query param
    public List<EtudiantDTO> findByAnnee(int annee) {
        return repository.findByAnneePremiereInscription(annee)
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    public List<EtudiantDTO> findByDepartementId(Long departementId) {
        return repository.findByDepartementId(departementId)
                .stream()
                .map(mapper::toDto)
                .toList();
    }
}