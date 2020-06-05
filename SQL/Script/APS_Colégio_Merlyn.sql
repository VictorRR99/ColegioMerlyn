--Arrumar Data
SHOW datestyle;
SET datestyle TO ISO, DMY;

--BLOCO DE CRIAÇÃO DE TABELAS--

--CRIANDO TABELA PESSOA
CREATE TABLE Pessoa (
    cd_pessoa SERIAL UNIQUE NOT NULL, /* [PK] */ --(1,1)
	nome VARCHAR(70) NOT NULL,
	dt_nasc DATE NOT NULL,
	RG VARCHAR(10) NOT NULL,
	CPF VARCHAR(15) NOT NULL
);

--CRIANDO TABELA ALUNO
CREATE TABLE Aluno (
	cd_aluno SERIAL UNIQUE NOT NULL,          /* [PK] */
	pessoa_cd_pessoa INTEGER UNIQUE NOT NULL, /* [FK] */ --(0,1)
	cd_sala INTEGER NOT NULL,                  /* [FK] */ --(1,N)
	serie INTEGER NOT NULL,
	turno VARCHAR(10) NOT NULL
);

--CRIANDO TABELA SALA
CREATE TABLE Sala (
	cd_sala SERIAL UNIQUE NOT NULL /* [PK] */ --(1,1) 
);

--CRIANDO TABELA NOTAS
CREATE TABLE Notas (
	cd_nota SERIAL UNIQUE NOT NULL,  /* [PK] */
    cd_aluno INTEGER NOT NULL,       /* [FK] */
	cd_disciplina INTEGER NOT NULL,  /* [FK] */
    NP1 FLOAT,
    NP2 FLOAT,
	bimestre INTEGER NOTNULL
);

--CRIANDO TABELA DISCIPLINA
CREATE TABLE Disciplina (
	cd_disc SERIAL UNIQUE NOT NULL,  /* [PK] */
	nm_disc VARCHAR(60) NOT NULL 	 /* [FK] */
);

--CRIANDO TABELA SALA_DISCIPLINA
CREATE TABLE Sala_disc (
	cd_sala_disc SERIAL UNIQUE NOT NULL,  /* [PK] */
	cd_sala INTEGER NOT NULL, 			  /* [FK] */
	cd_disc INTEGER NOT NULL              /* [FK] */
);

--CRIANDO TABELA PROFESSOR
CREATE TABLE Professor (
	cd_prof SERIAL UNIQUE NOT NULL,    /* [PK] */
	cd_pessoa INTEGER UNIQUE NOT NULL, /* [FK] */
	cd_disc INTEGER UNIQUE NOT NULL    /* [FK] */
);

--CRIANDO TABELA DIRETOR
CREATE TABLE Diretor (
	cd_dir SERIAL UNIQUE NOT NULL,  /* [PK] */
	cd_prof INTEGER UNIQUE NOT NULL /* [FK] */
);

--END-- BLOCO DE CRIAÇÃO DE TABELAS--

-- CHECAR RELACIONAMENTOS DE CARDINALIDADE

