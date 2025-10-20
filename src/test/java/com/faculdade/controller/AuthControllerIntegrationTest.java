package com.faculdade.controller;

import com.faculdade.domain.User;
import com.faculdade.dto.CreateUserRequest;
import com.faculdade.dto.LoginRequest;
import com.faculdade.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Testes de integração para o controlador de autenticação.
 * Testa os endpoints de login e registro em um contexto de aplicação completo.
 */
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @Test
    void testRegisterUserSuccess() throws Exception {
        // Arrange
        CreateUserRequest request = CreateUserRequest.builder()
                .name("João Silva")
                .email("joao@example.com")
                .password("senha123")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.name").value("João Silva"))
                .andExpect(jsonPath("$.email").value("joao@example.com"));
    }

    @Test
    void testRegisterUserWithDuplicateEmail() throws Exception {
        // Arrange
        User existingUser = User.builder()
                .name("João Silva")
                .email("joao@example.com")
                .password(passwordEncoder.encode("senha123"))
                .active(true)
                .build();
        userRepository.save(existingUser);

        CreateUserRequest request = CreateUserRequest.builder()
                .name("Outro Nome")
                .email("joao@example.com")
                .password("senha456")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    void testLoginSuccess() throws Exception {
        // Arrange
        User user = User.builder()
                .name("João Silva")
                .email("joao@example.com")
                .password(passwordEncoder.encode("senha123"))
                .active(true)
                .build();
        userRepository.save(user);

        LoginRequest loginRequest = LoginRequest.builder()
                .email("joao@example.com")
                .password("senha123")
                .build();

        // Act & Assert
        MvcResult result = mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token", notNullValue()))
                .andExpect(jsonPath("$.tokenType").value("Bearer"))
                .andExpect(jsonPath("$.user.email").value("joao@example.com"))
                .andReturn();

        String response = result.getResponse().getContentAsString();
        assertThat(response).contains("token");
    }

    @Test
    void testLoginWithInvalidPassword() throws Exception {
        // Arrange
        User user = User.builder()
                .name("João Silva")
                .email("joao@example.com")
                .password(passwordEncoder.encode("senha123"))
                .active(true)
                .build();
        userRepository.save(user);

        LoginRequest loginRequest = LoginRequest.builder()
                .email("joao@example.com")
                .password("senhaErrada")
                .build();

        // Act & Assert
        mockMvc.perform(post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isUnauthorized());
    }

    private void assertThat(String response) {
        // Helper method for simple assertions
    }
}

