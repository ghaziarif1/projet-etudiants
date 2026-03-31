package com.gestion.etudiants_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.util.List;

@Component
public class DataInitializer implements CommandLineRunner {

    private final EtudiantRepository repository;

    public DataInitializer(EtudiantRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() == 0) {
            List<Etudiant> etudiants = List.of(
                    new Etudiant("12345678", "Mohamed Ali", LocalDate.of(2002, 5, 15)),
                    new Etudiant("87654321", "Fatima Ben", LocalDate.of(2001, 8, 22)),
                    new Etudiant("11223344", "Ahmed Trabelsi", LocalDate.of(2003, 1, 10)),
                    new Etudiant("55667788", "Leila Mansour", LocalDate.of(2000, 11, 30)),
                    new Etudiant("99887766", "Karim Jouini", LocalDate.of(2004, 3, 5))
            );
            repository.saveAll(etudiants);
            System.out.println("✅ 5 étudiants initiaux ajoutés !");
        }
    }
}