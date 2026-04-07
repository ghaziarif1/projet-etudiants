# Projet Gestion Étudiants - Spring Boot 4 + Flutter + Docker

## 🚀 Lancement rapide

1. `docker compose up --build` → API + PostgreSQL
2. API disponible : http://localhost:8080/api/etudiants
3. Lancer Flutter : `cd mobile-app && flutter run`

📝 Description générale

Ce projet est la Partie 2 des activités d’intégration de compétences pour le cours Spring Boot et DevOps. Il consiste à enrichir une API REST de gestion des étudiants avec :

Une logique métier testée (calcul d’âge, filtrage par année de première inscription)
Une interface web légère en HTML/JS
Une image Docker publiée sur Docker Hub
Un déploiement Kubernetes sur K3S
Une architecture microservice complète incluant cache Redis, gestion des erreurs, documentation Swagger/OpenAPI, et traçabilité Jira
📁 Structure du projet
projet-etudiants/
├── api-spring-boot/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/example/etudiants/
│   │   │   │   ├── controller/       # API REST
│   │   │   │   ├── service/          # Logique métier
│   │   │   │   ├── repository/       # Accès à la base de données
│   │   │   │   ├── entity/           # Entités JPA (Etudiant, Departement)
│   │   │   │   ├── dto/              # Data Transfer Objects
│   │   │   │   ├── mapper/           # Conversion DTO ↔ Entity
│   │   │   │   └── config/           # Configuration Spring Boot (Redis, Swagger)
│   │   │   └── resources/
│   │   │       └── static/index.html # Interface web
│   │   └── test/
│   │       └── resources/features/etudiant.feature  # Tests BDD Cucumber
│   ├── Dockerfile                     # Image Docker
│   └── pom.xml                         # Dépendances Maven
├── k8s/
│   ├── etudiant-deployment.yaml       # Deployment + Service Etudiant
│   └── postgres-deployment.yaml       # Deployment + Service PostgreSQL
└── docker-compose.yml                 # Conteneur Redis et base de données
⚙️ Fonctionnalités
1. API REST complète pour Etudiant et Département
   Endpoints Etudiant :
   GET /api/etudiants : liste tous les étudiants
   GET /api/etudiants/{id} : récupère un étudiant
   POST /api/etudiants : crée un étudiant
   PUT /api/etudiants/{id} : met à jour un étudiant
   DELETE /api/etudiants/{id} : supprime un étudiant
   Endpoints Département :
   GET /api/departements : liste tous les départements
   GET /api/departements/{id} : récupère un département
   POST /api/departements : crée un département
   PUT /api/departements/{id} : met à jour un département
   DELETE /api/departements/{id} : supprime un département
2. Logique métier
   Méthode age() dans l’entité Etudiant calculant l’âge dynamiquement à partir de dateNaissance.
   Filtrage des étudiants par année de première inscription :
   List<Etudiant> findByAnneePremiereInscription(int annee);
3. Interface web simple
   index.html situé dans src/main/resources/static/
   Affiche la liste des étudiants via fetch('/api/etudiants') et mise à jour dynamique du DOM.
4. Gestion des erreurs globales
   @RestControllerAdvice avec traitement de :
   ResourceNotFoundException → 404
   MethodArgumentNotValidException → 400
   Erreurs génériques → 500
5. Documentation Swagger / OpenAPI
   Accessible via : http://localhost:8080/swagger-ui.html
   Annotée avec @Operation et @ApiResponse pour chaque endpoint.
6. Cache Redis
   Endpoints de lecture annotés avec @Cacheable(value="etudiants")
   Invalidation du cache lors des opérations d’écriture avec @CacheEvict(value="etudiants", allEntries=true)
7. Dockerisation
   Image Docker construite et poussée sur Docker Hub :
   docker build -t <votre-username>/etudiant-service:1.0 .
   docker push <votre-username>/etudiant-service:1.0
8. Déploiement Kubernetes
   Microservice étudiant et PostgreSQL déployés avec K3S
   Manifests Kubernetes :
   k8s/etudiant-deployment.yaml
   k8s/postgres-deployment.yaml
   Accès via NodePort ou kubectl port-forward.
9. Gestion de projet via Jira
   Projet Scrum avec deux sprints :
   Sprint 1 : API REST de base
   Sprint 2 : Enrichissement (Partie 2)
   Epic principale : Gestion des Étudiants
   User stories et tâches techniques liées aux commits Git (clé Jira incluse dans les messages de commit)
   🧪 Tests
   Tests BDD avec Cucumber + JUnit 5 pour la méthode age()
   Scénario Gherkin :
   Feature: Calcul de l'âge d'un étudiant
   Scenario: Étudiant né il y a 22 ans
   Given un étudiant avec la date de naissance "2002-04-07"
   When on calcule son âge
   Then l'âge retourné doit être 23
   🚀 Instructions pour exécuter le projet
   Prérequis
   Java 25
   Maven
   Docker
   K3S / Kubernetes
   Redis (via docker-compose ou conteneur)
   Lancer l’API localement
   git checkout version-2
   mvn clean install
   mvn spring-boot:run
   Accéder à l’interface web
   URL : http://localhost:8080/index.html
   Déploiement Docker
   docker build -t <votre-username>/etudiant-service:1.0 .
   docker push <votre-username>/etudiant-service:1.0
   Déploiement Kubernetes
   kubectl apply -f k8s/postgres-deployment.yaml
   kubectl apply -f k8s/etudiant-deployment.yaml
   kubectl get pods
   kubectl get svc
   Accès via NodePort : http://<IP_VM>:30080/api/etudiants
   🔧 Technologies utilisées
   Java 25, Spring Boot, JPA/Hibernate
   Lombok, MapStruct (Mapper DTO ↔ Entity)
   PostgreSQL
   Redis (Cache)
   Docker, Kubernetes (K3S)
   Swagger/OpenAPI
   Cucumber + JUnit 5 (BDD)
   HTML / Vanilla JS pour la page front
   Jira Scrum pour gestion de projet
   📝 Remarques finales

Ce projet illustre :

Une architecture en couches propre (Controller, Service, Repository, DTO, Mapper, Config)
La Dockerisation et le déploiement Kubernetes d’un microservice
L’utilisation de cache Redis pour optimiser les lectures
La gestion d’erreurs et la documentation API
La traçabilité via Jira avec des sprints et user stories