CREATE TABLE reglas (
    id SERIAL PRIMARY KEY,
    limite_inferior INTEGER,
    limite_superior INTEGER,
    monto INTEGER NOT NULL,
    created_at DATE NOT NULL
);