package com.gestion.etudiants_api.stepdefinitions;

import com.gestion.etudiants_api.Etudiant;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;

public class EtudiantStepDefinitions {

    private Etudiant etudiant;
    private int ageCalcule;

    @Given("un étudiant avec la date de naissance {string}")
    public void unEtudiantAvecDateNaissance(String date) {
        etudiant = Etudiant.builder()
                .dateNaissance(LocalDate.parse(date))
                .build();
    }

    @When("on calcule son âge")
    public void onCalculeSonAge() {
        ageCalcule = etudiant.age();
    }

    @Then("l'âge retourné doit être {int}")
    public void lageRetourneDoitEtre(int ageAttendu) {
        assertEquals(ageAttendu, ageCalcule);
    }
}