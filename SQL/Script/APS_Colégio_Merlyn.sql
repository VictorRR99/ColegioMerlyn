--Arrumar Data
SHOW datestyle;
set datestyle to ISO, DMY;

--Facilitador SELECT
SELECT * FROM Pessoa;
SELECT * FROM Sala;
SELECT * FROM Aluno;
SELECT * FROM Notas;
SELECT * FROM Disciplina;
SELECT * FROM Sala_Disc;
SELECT * FROM Professor;
SELECT * FROM Diretor;
--END-- Facilitador DROP TABLE

--Facilitador DROP TABLE
DROP TABLE Pessoa;
DROP TABLE Sala;
DROP TABLE Aluno;
DROP TABLE Notas;
DROP TABLE Disciplina;
DROP TABLE Sala_Disc;
DROP TABLE Professor;
DROP TABLE Diretor;
--END-- Facilitador DROP TABLE



CREATE TABLE Pessoa (
    cd_pessoa SERIAL UNIQUE NOT NULL, /* [PK] */ --(1,1)
	nome VARCHAR(70) NOT NULL,
	dt_nasc DATE NOT NULL,
	RG VARCHAR(10) NOT NULL,
	CPF VARCHAR(15) NOT NULL
);

CREATE TABLE Aluno (
	cd_aluno SERIAL UNIQUE NOT NULL,         /* [PK] */
	pessoa_cd_pessoa INTEGER UNIQUE NOT NULL, /* [FK] */ --(0,1)
	cd_sala INTEGER NOT NULL                  /* [FK] */ --(1,N)
);

CREATE TABLE Sala (
	cd_sala SERIAL UNIQUE NOT NULL /* [PK] */ --(1,1) 
);

CREATE TABLE Notas (
	cd_nota SERIAL UNIQUE NOT NULL, /* [PK] */
    cd_aluno INTEGER NOT NULL,       /* [FK] */
    NP1 FLOAT NOT NULL,
    NP2 FLOAT NOT NULL
);

CREATE TABLE Disciplina (
	cd_disc SERIAL UNIQUE NOT NULL, /* [PK] */
	nm_disc VARCHAR(30) NOT NULL 	 /* [FK] */
);

CREATE TABLE Sala_disc (
	cd_sala_disc SERIAL UNIQUE NOT NULL, /* [PK] */
	cd_sala INTEGER NOT NULL, 			  /* [FK] */
	cd_disc INTEGER NOT NULL              /* [FK] */
);

CREATE TABLE Professor (
	cd_prof SERIAL UNIQUE NOT NULL,   /* [PK] */
	cd_pessoa INTEGER UNIQUE NOT NULL, /* [FK] */
	cd_disc INTEGER UNIQUE NOT NULL    /* [FK] */
);

CREATE TABLE Diretor (
	cd_dir SERIAL UNIQUE NOT NULL, /* [PK] */
	cd_prof INTEGER UNIQUE NOT NULL /* [FK] */
);

-- CHECAR RELACIONAMENTOS DE CARDINALIDADE

SELECT * FROM Diretor;

DELETE FROM Pessoa

INSERT INTO Pessoa(nome, dt_nasc, rg, cpf) VALUES
('Victor', '22/02/2001', '098223', '086183'),
('Vinicius', '04/08/2000', '098223', '086183'),
('Leticia', '16/07/2001', '098223', '086183'),
('Samuel', '01/01/2001', '098223', '086183');

INSERT INTO Pessoa(nome, dt_nasc, rg, cpf) VALUES
('32321', '22/02/2001', '098223', '086183'),
('4343', '04/08/2000', '098223', '086183'),
('5445', '16/07/2001', '098223', '086183'),
('6666', '01/01/2001', '098223', '086183');

INSERT INTO Pessoa(nome, dt_nasc, rg, cpf) VALUES
('José', '22/02/2001', '098223', '086183'),
('Mauricio', '04/08/2000', '098223', '086183'),
('Mariana', '16/07/2001', '098223', '086183'),
('Clara', '01/01/2001', '098223', '086183');

INSERT INTO Aluno(pessoa_cd_pessoa, cd_sala) VALUES
(3232, 34);

INSERT INTO Sala(cd_sala) VALUES
(12);
