CREATE TABLE usos (
    id SERIAL PRIMARY KEY,
    cliente_id INTEGER NOT NULL,
    CONSTRAINT usos_cliente_id_fkey FOREIGN KEY (cliente_id) REFERENCES clientes (id),
    puntaje_utilizado INTEGER NOT NULL,
    fecha DATE NOT NULL,
    concepto_id INTEGER NOT NULL,
    CONSTRAINT usos_concepto_id_fkey FOREIGN KEY (concepto_id) REFERENCES conceptos (id)
);

CREATE TABLE uso_detalles (
    id SERIAL PRIMARY KEY,
    uso_id INTEGER NOT NULL,
    CONSTRAINT uso_detalles_uso_id_fkey FOREIGN KEY (uso_id) REFERENCES usos (id),
    bolsa_id INTEGER NOT NULL,
    CONSTRAINT uso_detalles_bolsa_id_fkey FOREIGN KEY (bolsa_id) REFERENCES bolsas (id),
    puntaje_utilizado INTEGER NOT NULL
);