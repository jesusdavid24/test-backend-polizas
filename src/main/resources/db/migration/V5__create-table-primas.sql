CREATE TABLE primas(
    id BIGINT NOT NULL AUTO_INCREMENT,
    codigo_amparo_id BIGINT NOT NULL ,
    edad_minima INT NOT NULL ,
    edad_maxima INT NOT NULL ,
    porcentaje_prima DECIMAL(5, 4) NOT NULL ,
    PRIMARY KEY (id),

    constraint fk_primas_codigo_amparo_id foreign key(codigo_amparo_id) references amparos(id)
);

INSERT INTO primas (codigo_amparo_id, edad_minima, edad_maxima, porcentaje_prima) VALUES
(1, 18, 45, 0.02304),
(1, 46, 75, 0.02012),
(2, 18, 50, 0.18090),
(2, 51, 70, 0.16043),
(3, 18, 60, 0.14123),
(3, 61, 70, 0.15450),
(4, 18, 50, 0.12123),
(4, 51, 70, 0.13450);
