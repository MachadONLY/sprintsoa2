package com.faculdade.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * Entidade de domínio que representa um usuário no sistema.
 * Implementa os princípios de separação de responsabilidades e encapsulamento.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome não pode estar vazio")
    @Column(nullable = false)
    private String name;

    @Email(message = "O email deve ser válido")
    @NotBlank(message = "O email não pode estar vazio")
    @Column(nullable = false, unique = true)
    private String email;

    @NotBlank(message = "A senha não pode estar vazia")
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private Boolean active = true;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Long createdAt;

    @Column(name = "updated_at")
    private Long updatedAt;

    /**
     * Hook do JPA executado antes de persistir a entidade.
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = System.currentTimeMillis();
        this.updatedAt = System.currentTimeMillis();
    }

    /**
     * Hook do JPA executado antes de atualizar a entidade.
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = System.currentTimeMillis();
    }
}

