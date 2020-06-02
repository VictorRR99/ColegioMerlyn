--Arrumar Data
SHOW datestyle;
set datestyle to ISO, DMY;
--Problema em arrumar a data --Infomações no Trello


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
