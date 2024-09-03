-- Crear tabla tipo_programa
CREATE TABLE tipo_programa (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(255),
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    fecha_modificacion TIMESTAMP
);

-- Crear tabla programa con campos de tipo TIMESTAMP
CREATE TABLE programa (
    id UUID PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    descripcion VARCHAR(500),
    clasificacion VARCHAR(50),
    canal VARCHAR(100),
    fecha_inicio TIMESTAMP,
    fecha_fin TIMESTAMP,
    semanal BOOLEAN,
    dias VARCHAR(50),
    tipo_programa_id UUID,
    favorito BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (tipo_programa_id) REFERENCES tipo_programa(id)
);