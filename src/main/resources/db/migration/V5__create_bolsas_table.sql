CREATE TABLE bolsas (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    fecha_asignacion DATE NOT NULL,
    fecha_caducidad DATE NOT NULL,
    puntaje_asignado INTEGER NOT NULL,
    puntaje_utilizado INTEGER NOT NULL,
    saldo INTEGER NOT NULL,
    monto INTEGER NOT NULL,
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT cliente_id_fk FOREIGN KEY (cliente_id) REFERENCES clientes (id) ON DELETE CASCADE
);
