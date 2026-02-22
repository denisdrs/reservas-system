CREATE TABLE IF NOT EXISTS usuarios (
    id          SERIAL PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) UNIQUE NOT NULL,
    senha       VARCHAR(255) NOT NULL,
    role        VARCHAR(20)  NOT NULL DEFAULT 'USER',
    ativo       BOOLEAN      NOT NULL DEFAULT TRUE,
    criado_em   TIMESTAMP    NOT NULL DEFAULT NOW(),
    alterado_em TIMESTAMP    NOT NULL DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS reservas (
    id          SERIAL PRIMARY KEY,
    usuario_id  INT          NOT NULL REFERENCES usuarios(id) ON DELETE CASCADE,
    descricao   VARCHAR(255) NOT NULL,
    data_inicio DATE         NOT NULL,
    data_fim    DATE         NOT NULL,
    status      VARCHAR(20)  NOT NULL DEFAULT 'PENDENTE',
    criado_em   TIMESTAMP    NOT NULL DEFAULT NOW(),
    alterado_em TIMESTAMP    NOT NULL DEFAULT NOW(),
    CONSTRAINT data_valida CHECK (data_fim >= data_inicio)
);

-- Senhas: admin123 e user123 (hasheadas com bcrypt)
INSERT INTO usuarios (nome, email, senha, role) VALUES
    ('Administrador', 'admin@sistema.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'ADMIN'),
    ('Joao Silva', 'joao@sistema.com', '$2a$10$GRLdNijSQMUvl/au9ofL.eDwmoohzzS7.rmNSJZ.0FxO1ohk7q36G', 'USER')
ON CONFLICT (email) DO NOTHING;
