# Como Contribuir – Sistema de Reservas

## Onde cada dev trabalha

```
reservas-system/
├── backend/    ← Dev Back-end (Java 21 + Spring Boot 3)
├── frontend/   ← Dev Front-end (React + Vite)
├── docs/       → NÃO MEXER (DevOps)
├── infra/      → NÃO MEXER (DevOps)
└── .github/    → NÃO MEXER (DevOps)
```

## Fluxo de Branches

```
feature/xxx → PR → develop → deploy DEV automático
                       ↓
               PR + aprovação → main → deploy PROD automático
```

## Passo a Passo

```bash
# Clone e crie sua branch
git clone https://github.com/SEU_USER/reservas-system
cd reservas-system
git checkout develop
git checkout -b feature/nome-da-feature

# Rode localmente (back + banco em Docker)
docker compose -f infra/docker-compose.local.yml up

# Swagger: http://localhost:8080/swagger-ui/index.html

# Commite e abra PR para develop
git add .
git commit -m "feat: minha feature"
git push origin feature/nome-da-feature
```

## Padrão de Commits

```
feat:     nova funcionalidade
fix:      correção de bug
docs:     documentação
refactor: refatoração
test:     testes
chore:    build, configs
```

## Ambientes

| Ambiente | URL |
|----------|-----|
| Local    | http://localhost:8080/swagger-ui/index.html |
| Dev      | http://<IP>/dev/swagger-ui/index.html |
| Prod     | http://<IP>/prod/swagger-ui/index.html |

> Solicite o IP da EC2 ao DevOps.

## Usuários de Teste

| Role  | Email             | Senha    |
|-------|-------------------|----------|
| ADMIN | admin@sistema.com | admin123 |
| USER  | joao@sistema.com  | user123  |

> Emails fictícios — não precisam ser reais.

## Autenticação JWT

```
POST /auth/login → retorna token JWT
Header: Authorization: Bearer <token>
```

## Permissões

| Endpoint                          | USER | ADMIN |
|-----------------------------------|------|-------|
| POST /auth/login                  | ✅   | ✅    |
| POST /auth/cadastro               | ✅   | ✅    |
| GET  /reservas/minhas             | ✅   | ✅    |
| POST /reservas                    | ✅   | ✅    |
| GET  /admin/reservas              | ❌   | ✅    |
| PUT  /admin/reservas/{id}/status  | ❌   | ✅    |
| GET  /admin/usuarios              | ❌   | ✅    |

## Schema do Banco

```sql
usuarios: id, nome, email, senha(bcrypt), role(USER|ADMIN), ativo, criado_em
reservas: id, usuario_id(FK), descricao, data_inicio, data_fim,
          status(PENDENTE|APROVADA|CANCELADA), criado_em
```

## Variáveis de Ambiente (back-end)

| Variável                   | Descrição              |
|----------------------------|------------------------|
| SPRING_DATASOURCE_URL      | URL JDBC do PostgreSQL |
| SPRING_DATASOURCE_USERNAME | Usuário do banco       |
| SPRING_DATASOURCE_PASSWORD | Senha do banco         |
| SPRING_PROFILES_ACTIVE     | local / dev / prod     |
| JWT_SECRET                 | Chave de assinatura    |
| SERVER_PORT                | Porta (padrão: 8080)   |

## Para o front-end (React + Vite)

A URL da API vem de variável de ambiente:
```js
const API_URL = import.meta.env.VITE_API_URL
// Local:  http://localhost:8080
// Dev:    http://<IP>/dev
// Prod:   http://<IP>/prod
```
