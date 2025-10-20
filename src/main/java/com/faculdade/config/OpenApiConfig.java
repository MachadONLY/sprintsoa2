package com.faculdade.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de OpenAPI/Swagger para documentação automática da API.
 * Define informações gerais, esquemas de segurança e componentes reutilizáveis.
 */
@Configuration
public class OpenApiConfig {

    /**
     * Configura o OpenAPI com informações da API e esquemas de segurança.
     *
     * @return a configuração OpenAPI
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST - Trabalho de Faculdade")
                        .version("1.0.0")
                        .description("API REST com autenticação JWT, documentação automática e testes. " +
                                "Implementa boas práticas de projeto orientado a objetos, segurança stateless e princípios SOLID.")
                        .contact(new Contact()
                                .name("Seu Nome")
                                .email("seu.email@faculdade.com")
                                .url("https://github.com/seu-usuario/api-rest"))
                        .license(new License()
                                .name("MIT")
                                .url("https://opensource.org/licenses/MIT")))
                .components(new Components()
                        .addSecuritySchemes("bearer-jwt", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT para autenticação. " +
                                        "Obtenha o token através do endpoint /api/auth/login")));
    }
}

