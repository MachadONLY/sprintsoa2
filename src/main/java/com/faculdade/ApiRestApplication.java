package com.faculdade;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal da aplicação Spring Boot.
 * Define configurações de OpenAPI/Swagger para documentação automática.
 */
@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "API REST - Trabalho de Faculdade",
        version = "1.0.0",
        description = "API REST com autenticação JWT, documentação automática e testes",
        contact = @Contact(
            name = "Seu Nome",
            email = "seu.email@faculdade.com"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080/api",
            description = "Servidor Local"
        )
    }
)
public class ApiRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiRestApplication.class, args);
    }
}

