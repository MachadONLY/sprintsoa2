package com.faculdade.controller;

import com.faculdade.dto.AuthResponse;
import com.faculdade.dto.CreateUserRequest;
import com.faculdade.dto.LoginRequest;
import com.faculdade.dto.UserDTO;
import com.faculdade.service.AuthService;
import com.faculdade.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador de autenticação.
 * Responsável por gerenciar endpoints de login e registro de usuários.
 * Segue o padrão REST e implementa boas práticas de API design.
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Autenticação", description = "Endpoints para autenticação de usuários")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    /**
     * Autentica um usuário e retorna um token JWT.
     *
     * @param loginRequest dados de login (email e senha)
     * @return resposta com token JWT
     */
    @PostMapping("/login")
    @Operation(summary = "Autenticar usuário", description = "Autentica um usuário e retorna um token JWT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Autenticação bem-sucedida",
            content = @Content(schema = @Schema(implementation = AuthResponse.class))),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "401", description = "Email ou senha inválidos")
    })
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse response = authService.authenticate(loginRequest);
        return ResponseEntity.ok(response);
    }

    /**
     * Registra um novo usuário no sistema.
     *
     * @param createUserRequest dados do novo usuário
     * @return resposta com os dados do usuário criado
     */
    @PostMapping("/register")
    @Operation(summary = "Registrar novo usuário", description = "Cria um novo usuário no sistema")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso",
            content = @Content(schema = @Schema(implementation = UserDTO.class))),
        @ApiResponse(responseCode = "400", description = "Dados de entrada inválidos"),
        @ApiResponse(responseCode = "409", description = "Email já cadastrado")
    })
    public ResponseEntity<UserDTO> register(@Valid @RequestBody CreateUserRequest createUserRequest) {
        UserDTO user = userService.createUser(createUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
}

