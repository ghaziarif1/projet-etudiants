package com.gestion.etudiants_api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Gestion des Étudiants",
                version = "2.0",
                description = "API complète avec CRUD, cache, et documentation OpenAPI"
        )
)
public class OpenApiConfig {
}