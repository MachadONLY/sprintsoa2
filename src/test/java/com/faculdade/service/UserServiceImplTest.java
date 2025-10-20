package com.faculdade.service;

import com.faculdade.domain.User;
import com.faculdade.dto.CreateUserRequest;
import com.faculdade.dto.UserDTO;
import com.faculdade.exception.EmailAlreadyExistsException;
import com.faculdade.exception.UserNotFoundException;
import com.faculdade.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

/**
 * Testes unitários para a classe UserServiceImpl.
 * Testa a lógica de negócio de forma isolada usando mocks.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    private User testUser;
    private CreateUserRequest createUserRequest;

    @BeforeEach
    void setUp() {
        testUser = User.builder()
                .id(1L)
                .name("João Silva")
                .email("joao@example.com")
                .password("encodedPassword")
                .active(true)
                .createdAt(System.currentTimeMillis())
                .build();

        createUserRequest = CreateUserRequest.builder()
                .name("João Silva")
                .email("joao@example.com")
                .password("senha123")
                .build();
    }

    @Test
    void testCreateUserSuccess() {
        // Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(testUser);

        // Act
        UserDTO result = userService.createUser(createUserRequest);

        // Assert
        assertNotNull(result);
        assertEquals("João Silva", result.getName());
        assertEquals("joao@example.com", result.getEmail());
        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testCreateUserWithDuplicateEmail() {
        // Arrange
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        // Act & Assert
        assertThrows(EmailAlreadyExistsException.class, () -> {
            userService.createUser(createUserRequest);
        });

        verify(userRepository, times(1)).existsByEmail(anyString());
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testGetUserByIdSuccess() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // Act
        UserDTO result = userService.getUserById(1L);

        // Assert
        assertNotNull(result);
        assertEquals("João Silva", result.getName());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserByIdNotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(1L);
        });

        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testDeleteUserSuccess() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        // Act
        userService.deleteUser(1L);

        // Assert
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).delete(testUser);
    }

    @Test
    void testDeleteUserNotFound() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(1L);
        });

        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, never()).delete(any(User.class));
    }
}

