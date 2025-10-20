# 📡 API REST / Spring Boot 3.3 
> Projeto criado para a sprint 4 da matéria de ARQUITETURA ORIENTADA A SERVIÇOS (SOA) E WEB SERVICES, da challenge 2025

---
## 👨‍💻 Desenvolvido por:
👤 **Camila do Prado Padalino** - `rm98316`  
👤 **Felipe Bressane** - `rm97688`  
👤 **Gabriel Teixeira Machado** - `rm551570`  
👤 **Guilherme Brazioli** - `rm98237`

---

## 📝 Descrição do Projeto

Este projeto é uma **API RESTful** construída com **Spring Boot 3.3**, com foco em segurança via **JWT**, arquitetura em camadas, testes automatizados e documentação interativa com **Swagger/OpenAPI**. Segue os princípios **SOLID** e boas práticas de desenvolvimento.

> Projeto acadêmico desenvolvido para avaliação, com foco em:  
> ✔️ Estrutura de código organizada  
> ✔️ Camadas bem definidas  
> ✔️ Segurança robusta  
> ✔️ Testes automatizados  

---

## 🚀 Funcionalidades

- 🔐 Autenticação Stateless com **JWT**
- 🔒 Criptografia de senhas com **BCrypt**
- 📘 Documentação automática com **Swagger (SpringDoc OpenAPI)**
- 🏛️ Arquitetura em camadas: Controller, Service, Repository, Domain
- 💡 Princípios **SOLID**
- ✅ Testes Unitários e de Integração
- ⚠️ Tratamento global de exceções
- 🛡️ Validação de dados com Jakarta Validation

---

## Estrutura do Projeto

O projeto segue uma arquitetura em camadas bem definida, promovendo separação de responsabilidades e facilitando manutenção e testes:

```
src/main/java/com/faculdade/
├── ApiRestApplication.java          # Classe principal da aplicação
├── config/                          # Configurações da aplicação
│   ├── SecurityConfig.java          # Configuração de segurança Spring Security
│   ├── CustomUserDetailsService.java # Serviço de detalhes de usuário
│   └── OpenApiConfig.java           # Configuração OpenAPI/Swagger
├── controller/                      # Camada de apresentação (REST Controllers)
│   ├── AuthController.java          # Endpoints de autenticação
│   └── UserController.java          # Endpoints CRUD de usuários
├── service/                         # Camada de negócio (Business Logic)
│   ├── UserService.java             # Interface do serviço de usuários
│   ├── UserServiceImpl.java          # Implementação do serviço de usuários
│   └── AuthService.java             # Serviço de autenticação
├── repository/                      # Camada de persistência (Data Access)
│   └── UserRepository.java          # Interface de repositório JPA
├── domain/                          # Camada de domínio (Entidades)
│   └── User.java                    # Entidade User
├── dto/                             # Data Transfer Objects
│   ├── UserDTO.java                 # DTO para resposta de usuário
│   ├── CreateUserRequest.java       # DTO para criação de usuário
│   ├── LoginRequest.java            # DTO para requisição de login
│   └── AuthResponse.java            # DTO para resposta de autenticação
├── security/                        # Componentes de segurança
│   └── JwtTokenProvider.java        # Provedor de tokens JWT
├── filter/                          # Filtros HTTP
│   └── JwtAuthenticationFilter.java # Filtro de autenticação JWT
└── exception/                       # Tratamento de exceções
    ├── UserNotFoundException.java    # Exceção customizada
    ├── EmailAlreadyExistsException.java # Exceção customizada
    ├── GlobalExceptionHandler.java  # Handler global de exceções
    └── ErrorResponse.java           # Classe de resposta de erro

src/main/resources/
└── application.properties           # Configurações da aplicação

src/test/java/com/faculdade/
├── service/
│   └── UserServiceImplTest.java     # Testes unitários do serviço
└── controller/
    ├── AuthControllerIntegrationTest.java  # Testes de integração
    └── UserControllerIntegrationTest.java  # Testes de integração
```

## Instruções de Execução

### Pré-requisitos

Antes de executar a aplicação, certifique-se de ter instalado:

- **Java 17 ou superior:** Verifique com `java -version`
- **Maven 3.9 ou superior:** Verifique com `mvn -version`
- **Git:** Para clonar o repositório

### Clonando o Repositório

```bash
git clone https://github.com/seu-usuario/api-rest.git
cd api-rest
```

### Compilando o Projeto

Para compilar o projeto e baixar todas as dependências:

```bash
mvn clean install
```

Este comando irá:
1. Limpar qualquer build anterior (`clean`)
2. Compilar o código-fonte
3. Executar os testes
4. Empacotar a aplicação em um arquivo JAR

### Executando a Aplicação

Existem duas formas de executar a aplicação:

**Opção 1: Usando Maven**

```bash
mvn spring-boot:run
```

**Opção 2: Executando o JAR diretamente**

```bash
java -jar target/api-rest-1.0.0.jar
```

A aplicação iniciará no endereço `http://localhost:8080/api`.

### Acessando a Documentação da API

Após iniciar a aplicação, acesse a documentação interativa do Swagger/OpenAPI em:

```
http://localhost:8080/api/swagger-ui.html
```

Nesta interface, você poderá:
- Visualizar todos os endpoints disponíveis
- Ver descrições detalhadas de cada endpoint
- Consultar exemplos de requisições e respostas
- Testar os endpoints diretamente (após autenticação)

## Como Rodar os Testes

### Executar Todos os Testes

```bash
mvn test
```

Este comando executará todos os testes unitários e de integração do projeto.

### Executar Testes Específicos

Para executar apenas os testes de uma classe específica:

```bash
mvn test -Dtest=UserServiceImplTest
```

Para executar testes que correspondem a um padrão:

```bash
mvn test -Dtest=*IntegrationTest
```

### Gerar Relatório de Cobertura de Testes

Para gerar um relatório de cobertura de código:

```bash
mvn clean test jacoco:report
```

O relatório será gerado em `target/site/jacoco/index.html`.

## Endpoints da API

### Autenticação

#### Registrar Novo Usuário

```http
POST /api/auth/register
Content-Type: application/json

{
  "name": "João Silva",
  "email": "joao@example.com",
  "password": "senha123"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "name": "João Silva",
  "email": "joao@example.com",
  "active": true,
  "createdAt": 1697000000000
}
```

#### Autenticar Usuário (Login)

```http
POST /api/auth/login
Content-Type: application/json

{
  "email": "joao@example.com",
  "password": "senha123"
}
```

**Resposta (200 OK):**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "user": {
    "id": 1,
    "name": "João Silva",
    "email": "joao@example.com",
    "active": true,
    "createdAt": 1697000000000
  }
}
```

### Usuários (Requer Autenticação)

Todos os endpoints de usuários requerem um token JWT válido no header `Authorization`:

```http
Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
```

#### Listar Todos os Usuários

```http
GET /api/users
Authorization: Bearer {token}
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "name": "João Silva",
    "email": "joao@example.com",
    "active": true,
    "createdAt": 1697000000000
  }
]
```

#### Obter Usuário por ID

```http
GET /api/users/{id}
Authorization: Bearer {token}
```

#### Atualizar Usuário

```http
PUT /api/users/{id}
Authorization: Bearer {token}
Content-Type: application/json

{
  "name": "João Silva Atualizado",
  "email": "joao.novo@example.com",
  "password": "novaSenha123"
}
```

#### Deletar Usuário

```http
DELETE /api/users/{id}
Authorization: Bearer {token}
```

**Resposta (204 No Content):** Sem corpo de resposta

## Autenticação JWT

A API utiliza **JWT (JSON Web Token)** para autenticação stateless. Após fazer login com sucesso, o servidor retorna um token que deve ser incluído em todas as requisições subsequentes.

### Fluxo de Autenticação

1. **Registro:** O usuário se registra fornecendo nome, email e senha
2. **Login:** O usuário faz login com email e senha
3. **Recebimento do Token:** O servidor valida as credenciais e retorna um token JWT
4. **Requisições Autenticadas:** O cliente inclui o token no header `Authorization: Bearer {token}`
5. **Validação:** O servidor valida o token antes de processar a requisição

### Segurança

- As senhas são criptografadas usando **BCryptPasswordEncoder** antes de serem armazenadas
- Os tokens JWT são assinados com uma chave secreta (HS256)
- Os tokens expiram após **24 horas** (configurável em `application.properties`)
- O filtro `JwtAuthenticationFilter` valida o token em cada requisição

## Boas Práticas Implementadas

### Princípios SOLID

O projeto implementa os cinco princípios SOLID de forma consistente:

**Single Responsibility Principle (SRP):** Cada classe possui uma única responsabilidade bem definida. Por exemplo, `UserService` é responsável apenas pela lógica de negócio de usuários, enquanto `UserRepository` cuida da persistência.

**Open/Closed Principle (OCP):** As classes estão abertas para extensão mas fechadas para modificação. O uso de interfaces (`UserService`) permite adicionar novas implementações sem modificar código existente.

**Liskov Substitution Principle (LSP):** As implementações podem ser substituídas por suas interfaces sem quebrar o comportamento esperado.

**Interface Segregation Principle (ISP):** As interfaces são específicas e focadas. `UserService` define apenas os métodos necessários para gerenciar usuários.

**Dependency Inversion Principle (DIP):** As dependências apontam para abstrações (interfaces) e não para implementações concretas, facilitado pelo uso de injeção de dependências do Spring.

### Arquitetura em Camadas

A separação em camadas garante que cada parte da aplicação tenha uma responsabilidade clara:

- **Camada de Apresentação (Controllers):** Responsável por receber requisições HTTP, delegar para a camada de serviço e retornar respostas
- **Camada de Negócio (Services):** Contém a lógica de negócio principal, isolada de detalhes técnicos
- **Camada de Persistência (Repositories):** Gerencia a comunicação com o banco de dados
- **Camada de Domínio (Entities):** Representa as entidades de negócio

### Tratamento de Exceções

O projeto implementa um tratamento global de exceções através da classe `GlobalExceptionHandler`, que:
- Centraliza o tratamento de erros
- Fornece respostas padronizadas
- Registra erros em logs para auditoria
- Diferencia tipos de erro (validação, não encontrado, conflito, etc.)

### Validação de Dados

Utiliza anotações de validação (Jakarta Validation) para garantir integridade dos dados:
- `@NotBlank` para campos obrigatórios
- `@Email` para validação de email
- `@Size` para limites de tamanho
- Mensagens de erro customizadas

### Testes Automatizados

O projeto inclui:
- **Testes Unitários:** Testam componentes isolados usando mocks (Mockito)
- **Testes de Integração:** Testam o comportamento completo dos endpoints usando MockMvc
- **Boa Cobertura:** Testes cobrem fluxos de sucesso e casos de erro

## Configurações Importantes

### Arquivo application.properties

O arquivo `src/main/resources/application.properties` contém as configurações principais:

```properties
# Porta do servidor
server.port=8080

# Configuração do banco de dados H2
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop

# Chave secreta JWT (ALTERAR EM PRODUÇÃO!)
jwt.secret=sua_chave_secreta_super_segura_com_minimo_256_bits_para_HS256
jwt.expiration=86400000

# Swagger/OpenAPI
springdoc.swagger-ui.path=/swagger-ui.html
```

### Alterando a Chave JWT

Para segurança em produção, altere a chave JWT no arquivo `application.properties`:

```properties
jwt.secret=sua_chave_muito_segura_com_minimo_256_bits_aqui
```

A chave deve ter no mínimo 256 bits (32 caracteres em base64) para o algoritmo HS256.

## Banco de Dados

O projeto utiliza **H2 Database** em memória para facilitar desenvolvimento e testes sem necessidade de configuração externa.

### Console H2

Para acessar o console do H2 (apenas em desenvolvimento):

```
http://localhost:8080/api/h2-console
```

**Credenciais:**
- JDBC URL: `jdbc:h2:mem:testdb`
- User Name: `sa`
- Password: (deixar em branco)

### Mudando para Banco de Dados Externo

Para usar um banco de dados como MySQL ou PostgreSQL em produção, altere as configurações em `application.properties`:

**MySQL:**
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/api_rest
spring.datasource.username=root
spring.datasource.password=senha
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
```

**PostgreSQL:**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/api_rest
spring.datasource.username=postgres
spring.datasource.password=senha
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## Exemplo de Uso Completo

Aqui está um exemplo de fluxo completo de uso da API:

### 1. Registrar um Novo Usuário

```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{
    "name": "João Silva",
    "email": "joao@example.com",
    "password": "senha123"
  }'
```

### 2. Fazer Login

```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{
    "email": "joao@example.com",
    "password": "senha123"
  }'
```

Resposta (copie o token):
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tokenType": "Bearer",
  "expiresIn": 86400000,
  "user": {...}
}
```

### 3. Acessar Endpoints Protegidos

```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
```

## Troubleshooting

### Erro: "Port 8080 is already in use"

Se a porta 8080 já está em uso, altere-a em `application.properties`:

```properties
server.port=8081
```

### Erro: "Failed to connect to database"

Se o H2 não inicializar corretamente, verifique se o arquivo `pom.xml` contém a dependência do H2:

```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

### Erro: "Invalid JWT token"

Certifique-se de que:
1. O token foi obtido recentemente (não expirou)
2. O token está sendo enviado corretamente no header `Authorization: Bearer {token}`
3. A chave JWT em `application.properties` não foi alterada

## Próximos Passos

Para melhorias futuras, considere:

1. **Autenticação OAuth2:** Integrar com provedores como Google ou GitHub
2. **Refresh Tokens:** Implementar tokens de atualização para renovar sessões
3. **Roles e Permissões:** Adicionar sistema de papéis (admin, user, etc.)
4. **Auditoria:** Registrar todas as ações dos usuários
5. **Rate Limiting:** Implementar limitação de requisições por IP/usuário
6. **Caching:** Adicionar cache com Redis para melhorar performance
7. **Documentação de Erro:** Expandir documentação de códigos de erro
8. **Integração Contínua:** Configurar CI/CD com GitHub Actions ou GitLab CI



