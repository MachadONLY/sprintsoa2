package com.faculdade.service;

import com.faculdade.domain.User;
import com.faculdade.dto.CreateUserRequest;
import com.faculdade.dto.UserDTO;

import java.util.List;

/**
 * Interface de serviço para operações de usuário.
 * Define o contrato que deve ser implementado pela classe de serviço.
 * Promove desacoplamento e facilita testes (princípio SOLID - Dependency Inversion).
 */
public interface UserService {

    /**
     * Cria um novo usuário.
     *
     * @param request dados do novo usuário
     * @return o usuário criado como DTO
     */
    UserDTO createUser(CreateUserRequest request);

    /**
     * Busca um usuário pelo ID.
     *
     * @param id o ID do usuário
     * @return o usuário encontrado como DTO
     * @throws com.faculdade.exception.UserNotFoundException se o usuário não existir
     */
    UserDTO getUserById(Long id);

    /**
     * Busca um usuário pelo email.
     *
     * @param email o email do usuário
     * @return o usuário encontrado
     * @throws com.faculdade.exception.UserNotFoundException se o usuário não existir
     */
    User getUserByEmail(String email);

    /**
     * Lista todos os usuários.
     *
     * @return lista de usuários como DTOs
     */
    List<UserDTO> getAllUsers();

    /**
     * Atualiza um usuário existente.
     *
     * @param id o ID do usuário
     * @param request dados atualizados
     * @return o usuário atualizado como DTO
     * @throws com.faculdade.exception.UserNotFoundException se o usuário não existir
     */
    UserDTO updateUser(Long id, CreateUserRequest request);

    /**
     * Deleta um usuário.
     *
     * @param id o ID do usuário
     * @throws com.faculdade.exception.UserNotFoundException se o usuário não existir
     */
    void deleteUser(Long id);
}

