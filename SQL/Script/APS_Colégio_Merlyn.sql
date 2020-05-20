CREATE TABLE Pessoa (
    cd_pessoa INTEGER UNIQUE NOT NULL, /* [PK] */
	nome VARCHAR(70) NOT NULL,
	dt_nasc DATE NOT NULL,
	RG VARCHAR(10) NOT NULL,
	CPF VARCHAR(15) NOT NULL
);

CREATE TABLE Sala (
	cd_sala INTEGER UNIQUE NOT NULL /* [PK] */
);

CREATE TABLE Aluno (
	cd_aluno INTEGER UNIQUE NOT NULL,         /* [PK] */
	pessoa_cd_pessoa INTEGER UNIQUE NOT NULL, /* [FK] */
	cd_sala INTEGER NOT NULL                  /* [FK] */
);

CREATE TABLE Notas (
	cd_nota VARCHAR(5) UNIQUE NOT NULL, /* [PK] */ -- VERIFICAR SE VARCHAR
    cd_aluno INTEGER NOT NULL,          /* [FK] */
    NP1 FLOAT NOT NULL,
    NP2 VARCHAR(5) NOT NULL -- VERIFICAR SE VARCHAR																						
);

CREATE TABLE Disciplina (
	cd_disc INTEGER UNIQUE NOT NULL, /* [PK] */
	nm_disc VARCHAR(30) NOT NULL 	 /* [FK] */
);

CREATE TABLE Sala_disc (
	cd_sala_disc INTEGER UNIQUE NOT NULL, /* [PK] */
	cd_sala INTEGER NOT NULL, 			  /* [FK] */
	cd_disc INTEGER NOT NULL              /* [FK] */
);

CREATE TABLE Professor (
	cd_prof INTEGER UNIQUE NOT NULL,   /* [PK] */
	cd_pessoa INTEGER UNIQUE NOT NULL, /* [FK] */
	cd_disc INTEGER UNIQUE NOT NULL	   /* [FK] */
);

CREATE TABLE Diretor (
	cd_dir INTEGER UNIQUE NOT NULL, /* [PK] */
	cd_prof INTEGER UNIQUE NOT NULL /* [FK] */
);

-- CHECAR RELACIONAMENTOS DE CARDINALIDADE
