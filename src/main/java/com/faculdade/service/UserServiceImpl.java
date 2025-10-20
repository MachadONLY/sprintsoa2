package com.faculdade.service;

import com.faculdade.domain.User;
import com.faculdade.dto.CreateUserRequest;
import com.faculdade.dto.UserDTO;
import com.faculdade.exception.EmailAlreadyExistsException;
import com.faculdade.exception.UserNotFoundException;
import com.faculdade.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementação do serviço de usuários.
 * Encapsula a lógica de negócio relacionada a usuários.
 * Segue os princípios SOLID: Single Responsibility, Dependency Inversion.
 */
@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createUser(CreateUserRequest request) {
        log.info("Criando novo usuário com email: {}", request.getEmail());

        // Verifica se o email já existe
        if (userRepository.existsByEmail(request.getEmail())) {
            log.warn("Tentativa de criar usuário com email duplicado: {}", request.getEmail());
            throw new EmailAlreadyExistsException("Email já cadastrado no sistema");
        }

        // Cria a entidade User
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .active(true)
                .build();

        // Persiste no banco de dados
        User savedUser = userRepository.save(user);
        log.info("Usuário criado com sucesso. ID: {}", savedUser.getId());

        return convertToDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDTO getUserById(Long id) {
        log.info("Buscando usuário com ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado com ID: {}", id);
                    return new UserNotFoundException("Usuário não encontrado");
                });

        return convertToDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        log.info("Buscando usuário com email: {}", email);

        return userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado com email: {}", email);
                    return new UserNotFoundException("Usuário não encontrado");
                });
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDTO> getAllUsers() {
        log.info("Buscando todos os usuários");

        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserDTO updateUser(Long id, CreateUserRequest request) {
        log.info("Atualizando usuário com ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado para atualização. ID: {}", id);
                    return new UserNotFoundException("Usuário não encontrado");
                });

        // Verifica se o novo email já existe (se foi alterado)
        if (!user.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            log.warn("Email já cadastrado durante atualização: {}", request.getEmail());
            throw new EmailAlreadyExistsException("Email já cadastrado no sistema");
        }

        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User updatedUser = userRepository.save(user);
        log.info("Usuário atualizado com sucesso. ID: {}", updatedUser.getId());

        return convertToDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Deletando usuário com ID: {}", id);

        User user = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("Usuário não encontrado para deleção. ID: {}", id);
                    return new UserNotFoundException("Usuário não encontrado");
                });

        userRepository.delete(user);
        log.info("Usuário deletado com sucesso. ID: {}", id);
    }

    /**
     * Converte uma entidade User para UserDTO.
     * Não expõe dados sensíveis como senha.
     *
     * @param user a entidade User
     * @return o DTO do usuário
     */
    private UserDTO convertToDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .build();
    }
}

