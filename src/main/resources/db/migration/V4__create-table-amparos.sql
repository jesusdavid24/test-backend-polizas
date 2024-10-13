create TABLE amparos(
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo INT NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO amparos (codigo, nombre) VALUES
(1, 'Muerte accidental'),
(2, 'Desmembración'),
(3, 'Auxilio funerario'),
(4, 'Renta vitalicia');