# Sistema de Pedidos!!

Este é um sistema de reservas/pedidos completo, desenvolvido para gerenciar usuários, produtos e pedidos com controle de acesso baseado em perfis (USER e ADMIN).

## Tecnologias Utilizadas

*   **Backend:** Java 21, Spring Boot 3, Spring Data JPA, Spring Security (JWT)
*   **Frontend:** React, Vite, Zod (para validação de formulários)
*   **Banco de Dados:** PostgreSQL
*   **Contêineres:** Docker, Docker Compose
*   **CI/CD:** GitHub Actions

## Funcionalidades

*   **Autenticação e Autorização:** Login, registro e controle de acesso baseado em JWT e perfis de usuário (ADMIN/USER).
*   **Gerenciamento de Usuários:**
    *   Criação e edição de usuários.
    *   Listagem de usuários (apenas ADMIN).
*   **Gerenciamento de Pedidos/Reservas:**
    *   Criação de pedidos.
    *   Visualização de pedidos do próprio usuário.
    *   Visualização e atualização de status de todos os pedidos/reservas (apenas ADMIN).
*   **Gerenciamento de Produtos:**
    *   Adição de produtos (apenas ADMIN).
*   **Carrinho de Compras/Checkout:** Processo de checkout para finalizar pedidos.

## Estrutura do Projeto

```
reservas-system/
├── backend/    # Aplicação Spring Boot (Java 21)
├── frontend/   # Aplicação React (Vite)
├── docs/       # Documentação (gerenciada por DevOps)
├── infra/      # Configurações de infraestrutura (Docker Compose, gerenciado por DevOps)
└── .github/    # Workflows de CI/CD (gerenciado por DevOps)
```

## Como Contribuir

Consulte o arquivo [`CONTRIBUTING.md`](CONTRIBUTING.md) para detalhes sobre o fluxo de trabalho, padrões de commit e como configurar seu ambiente de desenvolvimento.

## Primeiros Passos (Desenvolvimento Local)

Para rodar o projeto localmente, siga os passos abaixo:

### Pré-requisitos

*   Git
*   Docker e Docker Compose
*   Java Development Kit (JDK) 21
*   Node.js e npm (ou yarn)

### Configuração e Execução

1.  **Clone o repositório:**
    ```bash
    git clone https://github.com/denisdrs/reservas-system # Substitua pelo seu fork ou repositório original
    cd reservas-system
    ```

2.  **Inicie o Backend e o Banco de Dados (Docker Compose):**
    Navegue até a raiz do projeto e execute:
    ```bash
    make run
    ```
    Isso iniciará o serviço de banco de dados PostgreSQL e o backend da aplicação.

3.  **Acesse o Swagger UI do Backend:**
    Após o backend iniciar, você pode acessar a documentação da API em:
    http://localhost:8080/swagger-ui/index.html

4.  **Configure e Inicie o Frontend:**
    Navegue até o diretório `frontend`:
    ```bash
    cd frontend
    ```
    Crie um arquivo `.env` na raiz do diretório `frontend` com a seguinte variável de ambiente:
    ```
    VITE_API_URL=http://localhost:8080
    ```
    Instale as dependências e inicie a aplicação React:
    ```bash
    npm install
    npm run dev
    ```
    O frontend estará disponível em `http://localhost:5173` (ou outra porta padrão do Vite).

## Ambientes

| Ambiente | URL da API (Exemplo)               | URL do Swagger (Exemplo)            |
| :------- | :--------------------------------- | :---------------------------------- |
| Local    | `http://localhost:8080`            | `http://localhost:8080/swagger-ui/index.html` |

*Para os IPs dos ambientes de Dev e Prod, por favor, solicite ao time de DevOps.*

## Usuários de Teste

Utilize as seguintes credenciais para testar a aplicação:

| Role  | Email             | Senha    |
| :---- | :---------------- | :------- |
| ADMIN | admin@sistema.com | admin123 |
| USER  | joao@sistema.com  | user123  |

## Autenticação

A autenticação é feita via JWT (JSON Web Tokens).

1.  **Login:** Envie uma requisição `POST` para `/auth/login` com as credenciais do usuário.
2.  **Token:** O endpoint retornará um token JWT.
3.  **Autorização:** Inclua o token JWT no cabeçalho `Authorization` de suas requisições protegidas: `Authorization: Bearer <token>`.|

## Variáveis de Ambiente (Backend)

As seguintes variáveis de ambiente são necessárias para o funcionamento do backend:

| Variável                   | Descrição                               |
| :------------------------- | :-------------------------------------- |
| `SPRING_DATASOURCE_URL`    | URL JDBC para conexão com o PostgreSQL  |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco de dados               |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco de dados                 |
| `SPRING_PROFILES_ACTIVE`   | Perfil ativo do Spring (ex: `local`, `dev`, `prod`) |
| `JWT_SECRET`               | Chave secreta para assinatura de tokens JWT |
| `SERVER_PORT`              | Porta em que o servidor backend será executado (padrão: `8080`) |

## CI/CD

O projeto utiliza GitHub Actions para automação de CI/CD. Os workflows são configurados para:

*   **`ci.yml`:** Executa build e testes em pull requests para `develop` e `main`, e em pushes para `develop` e `main`.

---
