# Spring JWT Authentication Example 🔐

Este projeto é um exemplo de aplicação Spring Boot que implementa autenticação e autorização usando **JWT (JSON Web Tokens)**. O sistema permite login de usuários, criação de novos usuários, e validação de roles (usuários e administradores) com segurança baseada em tokens JWT.

## Funcionalidades ⚙️

- **Login**: Autenticação de usuários através de um nome de usuário e senha, gerando um token JWT para acesso.
- **Criação de Usuários**: Permite o cadastro de novos usuários no sistema.
- **JWT**: Geração e validação de tokens JWT para garantir a segurança e a autenticação nas requisições.
- **Swagger**: A documentação da API é gerada automaticamente usando o Swagger.

## Tecnologias Utilizadas 🛠️

- **Spring Boot**: Framework para construção da aplicação.
- **Spring Security**: Implementação de segurança para autenticação e autorização.
- **JWT (JSON Web Token)**: Para autenticação baseada em token.
- **Swagger**: Para documentação da API.
- **JPA/Hibernate**: Para gerenciamento de dados persistentes no banco de dados.

## Estrutura do Projeto 🗂️

- **Controller**: Controladores que gerenciam as rotas de login e usuários.
- **DTO**: Objetos de Transferência de Dados, como `Login` e `Session`.
- **Model**: Entidades JPA, como `AppUser`, que representam os dados do usuário no banco de dados.
- **Repository**: Interfaces para interação com o banco de dados.
- **Security**: Configurações de segurança e filtros JWT.
- **Service**: Lógica de negócio para criação de usuários.

## Endpoints da API 🌐

### `POST /login` 🔑

Autentica o usuário e gera um token JWT.

**Exemplo de Requisição:**
```json
{
  "username": "usuario_teste",
  "password": "senha_teste"
}
```

### Resposta de Sucesso:
```json
{
  "token": "Bearer <token>",
  "login": "usuario_teste"
}
```

### `POST /users 👤`

Cria um novo usuário no sistema. A senha é criptografada antes de ser salva.

**Exemplo de Requisição:**
```json
{
  "name": "Novo Usuário",
  "username": "usuario_novo",
  "password": "senha_forte"
}
```

## Swagger UI 📖
 A documentação interativa da API está disponível no Swagger UI. Acesse a seguinte URL para explorar a API:

`http://localhost:8080/swagger-ui/index.html`

## Configuração ⚙️
- Clone o repositório:
```bash
git clone https://github.com/champs-8/springSwagger.git
cd springSwagger
```

### Instale as dependências:
- Se você já tiver o Java 17+ e o Maven instalados, basta rodar:
```bash
mvn install
```

### Execute a aplicação:
- Para rodar a aplicação localmente, utilize o comando:
```bash
mvn spring-boot:run
```

### Banco de Dados:
- A aplicação utiliza o H2 Database por padrão para armazenamento de dados. Acesse o console do H2 em:
`http://localhost:8080/h2-console`

>Configure a URL do banco de dados como jdbc:h2:mem:testdb para testar localmente.


## Segurança 🛡️
-O sistema utiliza JWT para autenticação. Ao fazer login, um token JWT é gerado e deve ser incluído no cabeçalho Authorization das requisições subsequentes.

### Exemplo de Cabeçalho de Requisição com Token:
```json
Authorization: Bearer <seu_token_aqui>
```

### Configurações de Segurança 🔐
- As configurações de segurança podem ser ajustadas no arquivo application.properties ou application.yml. Algumas propriedades que você pode configurar incluem:

- `security.config.prefix`: Prefixo utilizado no token JWT (default: Bearer).

- `security.config.key`: Chave secreta para assinatura do JWT.

- `security.config.expiration`: Tempo de expiração do token JWT (em milissegundos, default: 1 hora).

## Contribuindo 🤝
Sinta-se à vontade para abrir um pull request ou issues para melhorar ou sugerir novas funcionalidades para este projeto.
