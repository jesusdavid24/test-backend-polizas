CREATE TABLE asegurados (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nro_identificacion VARCHAR(20),
    tipo_identificacion_id BIGINT NOT NULL,
    apellidos VARCHAR(100),
    nombres VARCHAR(100),
    sexo_id BIGINT NOT NULL,
    fecha_nacimiento DATE,
    PRIMARY KEY (id),

    CONSTRAINT fk_asegurados_tipo_identificacion_id FOREIGN KEY(tipo_identificacion_id) REFERENCES tipos_identificacion(id),
    CONSTRAINT fk_asegurados_sexo_id FOREIGN KEY(sexo_id) REFERENCES sexos(id)
);

INSERT INTO asegurados (nro_identificacion, tipo_identificacion_id, apellidos, nombres, sexo_id, fecha_nacimiento)
VALUES
    ('79000001', 1, 'APELLIDO1', 'NOMBRE1', 1, '1945-01-10'),
    ('79000002', 1, 'APELLIDO2', 'NOMBRE2', 1, '1950-01-10'),
    ('79000003', 1, 'APELLIDO3', 'NOMBRE3', 1, '1955-01-10'),
    ('51000001', 2, 'APELLIDO4', 'NOMBRE4', 2, '1960-01-10'),
    ('51000002', 2, 'APELLIDO5', 'NOMBRE5', 2, '1965-01-10'),
    ('51000003', 2, 'APELLIDO6', 'NOMBRE6', 2, '1970-01-10');
