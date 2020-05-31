-- CRIAÇÃO DE TABELAS
CREATE TABLE CURSO(
	cod_curso INTEGER NOT NULL, --PK
	nome_curso VARCHAR(50)
);

CREATE TABLE ALUNO(
	ra SERIAL NOT NULL, --PK
	nome_aluno VARCHAR(50),
	cod_curso INTEGER NOT NULL --FK
);
-->END<-- CRIAÇÃO DE TABELAS

-- PRIMARY KEYS
ALTER TABLE curso ADD PRIMARY KEY (cod_curso);
ALTER TABLE aluno ADD PRIMARY KEY (ra);
-->END<-- PRIMARY KEYS

-- FOREIGN KEYS
ALTER TABLE aluno 
ADD CONSTRAINT FK_aluno_cod_curso 
FOREIGN KEY (cod_curso) REFERENCES curso(cod_curso)
ON UPDATE CASCADE
ON DELETE NO ACTION;
-->END<-- FOREIGN KEYS

-- INSERTS
INSERT INTO curso (cod_curso, nome_curso) 
VALUES 
	(11, 'Word'),
	(12, 'Excel'),
	(13, 'Power Point'),
	(14, 'Acess');

INSERT INTO aluno (ra, nome_aluno, cod_curso) 
VALUES 
	(1001, 'Antonio Alvares', 11),
	(1002, 'Beatriz Barbosa', 12),
	(1003, 'Claudio Cardoso', 11),
	(1004, 'Daniela Damasio', 12);
-->END<-- INSERTS

-- CONSULTAS

-- 1º Questão --> Resposta, Letra (E)

SELECT NOME_ALUNO FROM ALUNO 
WHERE COD_CURSO = (SELECT COD_CURSO FROM CURSO WHERE NOME_CURSO = 'Excel');

-- 2º Questão --> Resposta, Letra (C)

SELECT NOME_CURSO FROM CURSO 
WHERE COD_CURSO NOT IN (SELECT COD_CURSO FROM ALUNO);
