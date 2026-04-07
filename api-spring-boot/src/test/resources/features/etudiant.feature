Feature: Calcul de l'âge d'un étudiant

  Scenario: Étudiant né il y a 23 ans (exemple pour 2026)
    Given un étudiant avec la date de naissance "2003-04-07"
    When on calcule son âge
    Then l'âge retourné doit être 23