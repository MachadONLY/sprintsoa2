package com.faculdade.repository;

import com.faculdade.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Interface de repositório para a entidade User.
 * Estende JpaRepository para fornecer operações CRUD básicas.
 * Segue o padrão Repository para separação de responsabilidades.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Busca um usuário pelo email.
     *
     * @param email o email do usuário
     * @return Optional contendo o usuário se encontrado
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica se um usuário com o email especificado existe.
     *
     * @param email o email a ser verificado
     * @return true se o email existe, false caso contrário
     */
    boolean existsByEmail(String email);
}

