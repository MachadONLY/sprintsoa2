# Instruções de Entrega - Trabalho de Faculdade

## 📋 Resumo do Projeto

Este é um projeto de **API REST com Spring Boot** desenvolvido como trabalho acadêmico, atendendo a todos os critérios de avaliação solicitados pelo professor.

## ✅ Critérios Atendidos

### 1. Estruturação do Projeto, Código Limpo e Princípios SOLID (25%)

- ✓ **Arquitetura em Camadas:** Separação clara entre controllers, services, repositories e entities
- ✓ **Interfaces e Polimorfismo:** Uso de interfaces (`UserService`, `UserRepository`) para promover desacoplamento
- ✓ **Princípios SOLID:** Aplicação consistente de todos os cinco princípios SOLID
- ✓ **Código Legível:** Nomes significativos, documentação em JavaDoc, formatação consistente
- ✓ **Boas Práticas:** Uso de Lombok para reduzir boilerplate, injeção de dependências, etc.

### 2. Configurações de Segurança e Autenticação (20%)

- ✓ **Autenticação Stateless:** Implementação de autenticação sem manutenção de estado no servidor
- ✓ **JWT (JSON Web Token):** Geração e validação de tokens JWT para autenticação
- ✓ **BCryptPasswordEncoder:** Criptografia segura de senhas com BCrypt
- ✓ **Filtros de Segurança:** `JwtAuthenticationFilter` para interceptação de requisições
- ✓ **Spring Security:** Configuração completa com `SecurityConfig` e `CustomUserDetailsService`

### 3. Regras de Negócio Implementadas como Serviços (15%)

- ✓ **Serviços Encapsulados:** Lógica de negócio em `UserService` e `AuthService`, separada dos controllers
- ✓ **Interfaces para Extensibilidade:** Interface `UserService` permite múltiplas implementações
- ✓ **Clareza e Coesão:** Métodos bem definidos com responsabilidades claras
- ✓ **Validações:** Regras de negócio como verificação de email duplicado, validação de credenciais

### 4. Documentação Automática da API (15%)

- ✓ **SpringDoc OpenAPI:** Integração com Swagger/OpenAPI para documentação automática
- ✓ **Anotações Descritivas:** Uso de `@Operation`, `@ApiResponse`, `@Schema` em controllers e DTOs
- ✓ **Swagger UI:** Interface interativa em `http://localhost:8080/api/swagger-ui.html`
- ✓ **Exemplos de Requisições e Respostas:** Documentação detalhada de cada endpoint
- ✓ **Organização em Tags:** Endpoints organizados por funcionalidade (Autenticação, Usuários)

### 5. Testes Automatizados (15%)

- ✓ **Testes Unitários:** `UserServiceImplTest` com testes isolados usando Mockito
- ✓ **Testes de Integração:** `AuthControllerIntegrationTest` e `UserControllerIntegrationTest` com MockMvc
- ✓ **Uso de Mocks:** Isolamento de dependências com `@Mock` e `@InjectMocks`
- ✓ **Boa Cobertura:** Testes cobrem fluxos de sucesso e casos de erro
- ✓ **Execução:** Todos os testes podem ser executados com `mvn test`

### 6. Documentação do Projeto (10%)

- ✓ **README.md Completo:** Documentação detalhada com:
  - Descrição do projeto
  - Tecnologias utilizadas
  - Estrutura do projeto
  - Instruções de execução
  - Como rodar os testes
  - Exemplos de uso
  - Troubleshooting

## 🚀 Como Entregar o Projeto

### Opção 1: Via GitHub (Recomendado)

1. **Criar um repositório GitHub:**
   - Acesse [github.com](https://github.com)
   - Clique em "New repository"
   - Nome: `api-rest` ou similar
   - Descrição: "API REST com Spring Boot - Trabalho de Faculdade"
   - Escolha "Public" para que o professor possa acessar
   - Clique em "Create repository"

2. **Clonar este projeto e fazer push:**
   ```bash
   cd /home/ubuntu/spring-boot-api
   git init
   git add .
   git commit -m "Initial commit: API REST com Spring Boot"
   git branch -M main
   git remote add origin https://github.com/seu-usuario/api-rest.git
   git push -u origin main
   ```

3. **Compartilhar o link:**
   - Copie o URL do repositório: `https://github.com/seu-usuario/api-rest`
   - Envie este link ao professor

### Opção 2: Compactar e Enviar por Email

1. **Compactar o projeto:**
   ```bash
   cd /home/ubuntu
   zip -r api-rest.zip spring-boot-api/
   ```

2. **Enviar o arquivo `api-rest.zip` ao professor**

### Opção 3: Usar Google Drive ou OneDrive

1. **Compactar o projeto** (conforme acima)
2. **Fazer upload** para Google Drive ou OneDrive
3. **Compartilhar o link** com o professor

## 📝 Checklist de Entrega

Antes de entregar, verifique:

- [ ] O projeto compila sem erros: `mvn clean install`
- [ ] Todos os testes passam: `mvn test`
- [ ] O README.md está completo e atualizado
- [ ] A documentação Swagger é acessível: `http://localhost:8080/api/swagger-ui.html`
- [ ] Os endpoints funcionam corretamente
- [ ] O arquivo `.gitignore` está presente
- [ ] O arquivo `LICENSE` está presente
- [ ] O código segue boas práticas e está bem documentado
- [ ] Não há arquivos temporários ou de build inclusos

## 🔍 Como o Professor Pode Avaliar

### 1. Verificar a Estrutura do Projeto

```bash
cd api-rest
tree src/main/java/com/faculdade
```

### 2. Compilar e Executar

```bash
mvn clean install
mvn spring-boot:run
```

### 3. Acessar a Documentação Swagger

```
http://localhost:8080/api/swagger-ui.html
```

### 4. Testar os Endpoints

**Registrar um usuário:**
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"João","email":"joao@test.com","password":"senha123"}'
```

**Fazer login:**
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"joao@test.com","password":"senha123"}'
```

**Listar usuários (com token):**
```bash
curl -X GET http://localhost:8080/api/users \
  -H "Authorization: Bearer {token_recebido_no_login}"
```

### 5. Executar os Testes

```bash
mvn test
```

## 📚 Estrutura de Arquivos Entregues

```
api-rest/
├── pom.xml                          # Dependências Maven
├── README.md                        # Documentação completa
├── ENTREGA.md                       # Este arquivo
├── LICENSE                          # Licença MIT
├── .gitignore                       # Arquivos a ignorar no Git
└── src/
    ├── main/
    │   ├── java/com/faculdade/
    │   │   ├── ApiRestApplication.java
    │   │   ├── config/              # Configurações
    │   │   ├── controller/          # REST Controllers
    │   │   ├── service/             # Lógica de Negócio
    │   │   ├── repository/          # Acesso a Dados
    │   │   ├── domain/              # Entidades
    │   │   ├── dto/                 # Data Transfer Objects
    │   │   ├── security/            # Componentes de Segurança
    │   │   ├── filter/              # Filtros HTTP
    │   │   └── exception/           # Tratamento de Exceções
    │   └── resources/
    │       └── application.properties
    └── test/
        └── java/com/faculdade/
            ├── service/             # Testes Unitários
            └── controller/          # Testes de Integração
```

## 🎯 Pontos Fortes do Projeto

1. **Arquitetura Profissional:** Segue padrões de indústria com separação clara de responsabilidades
2. **Segurança Robusta:** Implementação correta de JWT e criptografia de senhas
3. **Documentação Completa:** README detalhado, código documentado com JavaDoc, Swagger automático
4. **Testes Abrangentes:** Cobertura de testes unitários e de integração
5. **Código Limpo:** Segue princípios SOLID, usa boas práticas, nomes significativos
6. **Fácil de Executar:** Sem dependências externas (usa H2 em memória)

## ❓ Dúvidas Frequentes

**P: Como alterar a porta?**
R: Edite `src/main/resources/application.properties` e altere `server.port=8080`

**P: Como usar um banco de dados diferente?**
R: Altere as configurações de datasource em `application.properties` e adicione a dependência do driver ao `pom.xml`

**P: Como gerar um relatório de cobertura de testes?**
R: Execute `mvn clean test jacoco:report` e abra `target/site/jacoco/index.html`

**P: Como fazer deploy em produção?**
R: Altere a chave JWT em `application.properties`, configure um banco de dados externo, e execute `mvn clean package` para gerar o JAR

## 📞 Suporte

Se o professor tiver dúvidas sobre o projeto, consulte:
- **README.md:** Documentação completa do projeto
- **Código Comentado:** Todas as classes têm comentários explicativos
- **Swagger UI:** Documentação interativa dos endpoints
- **Testes:** Exemplos de como usar cada funcionalidade

---

**Projeto Entregue:** Outubro de 2024
**Versão:** 1.0.0
**Status:** Pronto para Avaliação ✓
