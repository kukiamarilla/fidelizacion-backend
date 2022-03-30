CREATE TABLE clientes (
  id SERIAL PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  apellido VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  telefono VARCHAR(11) NOT NULL,
  documento_tipo VARCHAR(11) NOT NULL,
  documento_numero VARCHAR(11) NOT NULL,
  fecha_nacimiento DATE NOT NULL,
  nacionalidad VARCHAR(255) NOT NULL
);