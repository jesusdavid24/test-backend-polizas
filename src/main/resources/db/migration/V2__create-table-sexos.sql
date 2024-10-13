CREATE TABLE sexos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO sexos (nombre) VALUES
('Masculino'),
('Femenino');