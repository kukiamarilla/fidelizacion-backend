CREATE TABLE vencimientos (
    id SERIAL PRIMARY KEY,
    fecha_inicio DATE,
    fecha_fin DATE,
    dias_validez INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL
);