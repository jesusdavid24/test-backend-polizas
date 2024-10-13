CREATE TABLE tipos_identificacion (
    id BIGINT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO tipos_identificacion (nombre) VALUES
('CC'),
('CE');