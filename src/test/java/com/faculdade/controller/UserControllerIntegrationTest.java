package com.faculdade.controller;

import com.faculdade.domain.User;
import com.faculdade.repository.UserRepository;
import com.faculdade.security.JwtTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de integração para o controlador de usuários.
 * Testa os endpoints CRUD de usuários com autenticação JWT.
 */
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    private User testUser;
    private String authToken;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();

        testUser = User.builder()
                .name("João Silva")
                .email("joao@example.com")
                .password(passwordEncoder.encode("senha123"))
                .active(true)
                .build();
        testUser = userRepository.save(testUser);

        authToken = "Bearer " + jwtTokenProvider.generateToken(testUser.getEmail(), testUser.getId());
    }

    @Test
    void testGetAllUsersSuccess() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/users")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name").value("João Silva"));
    }

    @Test
    void testGetAllUsersWithoutAuth() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testGetUserByIdSuccess() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/users/" + testUser.getId())
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(testUser.getId()))
                .andExpect(jsonPath("$.name").value("João Silva"))
                .andExpect(jsonPath("$.email").value("joao@example.com"));
    }

    @Test
    void testGetUserByIdNotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(get("/api/users/999")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUserSuccess() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/users/" + testUser.getId())
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify user was deleted
        mockMvc.perform(get("/api/users/" + testUser.getId())
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteUserNotFound() throws Exception {
        // Act & Assert
        mockMvc.perform(delete("/api/users/999")
                .header("Authorization", authToken)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }
}

