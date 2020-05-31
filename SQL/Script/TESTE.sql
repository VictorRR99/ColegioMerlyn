-- Facilitador SELECT
--  SELECT * FROM ASSINANTE;
--  SELECT * FROM RAMO_ATIVIDADE;
--  SELECT * FROM TIPO_ASSINANTE;
--  SELECT * FROM ENDERECO;
--  SELECT * FROM TELEFONE;
--  SELECT * FROM MUNICIPIO;
--END-- Facilitador SELECT

-- Facilitador DROP CONSTRAINT
--  ALTER TABLE ASSINANTE DROP CONSTRAINT FK_ASS_RAMO;
--  ALTER TABLE ASSINANTE DROP CONSTRAINT FK_ASS_TIPO;
--  ALTER TABLE ENDERECO DROP CONSTRAINT FK_ASS_end;
--  ALTER TABLE ENDERECO DROP CONSTRAINT FK_END_MUNIC;
--  ALTER TABLE TELEFONE DROP CONSTRAINT FK_END_FONE;
--END-- Facilitador DROP CONSTRAINT

-- Facilitador DROP TABLE
--  DROP TABLE ASSINANTE;
--  DROP TABLE RAMO_ATIVIDADE;
--  DROP TABLE TIPO_ASSINANTE;
--  DROP TABLE ENDERECO;
--  DROP TABLE TELEFONE;
--  DROP TABLE MUNICIPIO;
--END-- Facilitador DROP TABLE

-- Criação de Tabelas
CREATE TABLE ASSINANTE (
	CD_ASSINANTE SERIAL NOT NULL,
	NM_ASSINANTE VARCHAR(255)
);

CREATE TABLE RAMO_ATIVIDADE(
	CD_RAMO SERIAL NOT NULL,
	DS_RAMO VARCHAR(255)
);

CREATE TABLE TIPO_ASSINANTE(
	CD_TIPO SERIAL NOT NULL,
	DS_TIPO VARCHAR(255)
);

CREATE TABLE ENDERECO (
	CD_ENDERECO SERIAL NOT NULL,
	DS_ENDERECO VARCHAR(255) UNIQUE,
	COMPLEMENTO VARCHAR(100),
	BAIRRO VARCHAR(50),
	CEP VARCHAR(10)
);

CREATE TABLE TELEFONE(
	CD_FONE SERIAL NOT NULL,
	DDD VARCHAR(4),
	N_FONE VARCHAR(30)
);

CREATE TABLE MUNICIPIO(
	CD_MUNICIPIO SERIAL NOT NULL,
	DS_MUNICIPIO VARCHAR(255)
);
--END-- Criação de Tabelas

-- Criar Colunas de Relacões
ALTER TABLE ASSINANTE ADD COLUMN CD_RAMO INT;
ALTER TABLE ASSINANTE ADD COLUMN CD_TIPO INT NOT NULL;

ALTER TABLE ENDERECO ADD COLUMN CD_ASSINANTE INT NOT NULL;
ALTER TABLE ENDERECO ADD COLUMN CD_MUNICIPIO INT NOT NULL;

ALTER TABLE TELEFONE ADD COLUMN CD_ENDERECO INT NOT NULL;
--END-- Criar Colunas de Relacões

-- Primary Keys
ALTER TABLE ASSINANTE ADD PRIMARY KEY (CD_ASSINANTE);
ALTER TABLE RAMO_ATIVIDADE ADD PRIMARY KEY (CD_RAMO);
ALTER TABLE TIPO_ASSINANTE ADD PRIMARY KEY (CD_TIPO);
ALTER TABLE ENDERECO ADD PRIMARY KEY (CD_ENDERECO);
ALTER TABLE MUNICIPIO ADD PRIMARY KEY (CD_MUNICIPIO);
ALTER TABLE TELEFONE ADD PRIMARY KEY (CD_FONE);
--END-- Primary Keys

-- Adicionando Foreign Keys
ALTER TABLE ASSINANTE ADD CONSTRAINT FK_ASS_RAMO FOREIGN KEY (CD_RAMO) REFERENCES RAMO_ATIVIDADE(CD_RAMO)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE ASSINANTE ADD CONSTRAINT FK_ASS_TIPO FOREIGN KEY (CD_TIPO) REFERENCES TIPO_ASSINANTE(CD_TIPO)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE ENDERECO ADD CONSTRAINT FK_ASS_END FOREIGN KEY (CD_ASSINANTE) REFERENCES ASSINANTE(CD_ASSINANTE)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE ENDERECO ADD CONSTRAINT FK_END_MUNIC FOREIGN KEY (CD_MUNICIPIO) REFERENCES MUNICIPIO(CD_MUNICIPIO)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE TELEFONE ADD CONSTRAINT FK_END_FONE FOREIGN KEY (CD_ENDERECO) REFERENCES ENDERECO(CD_ENDERECO)
ON UPDATE CASCADE
ON DELETE NO ACTION;
--END-- Adicionando Foreign Keys

-- Inserção dos Dados
INSERT INTO RAMO_ATIVIDADE (DS_RAMO) VALUES ('Atividade1');
INSERT INTO RAMO_ATIVIDADE (DS_RAMO) VALUES ('Atividade2');

INSERT INTO TIPO_ASSINANTE (DS_TIPO) VALUES ('Residencial');
INSERT INTO TIPO_ASSINANTE (DS_TIPO) VALUES ('Comercial');

INSERT INTO ASSINANTE (NM_ASSINANTE, CD_RAMO, CD_TIPO) VALUES ('Victor', 2, 2);
INSERT INTO ASSINANTE (NM_ASSINANTE, CD_RAMO, CD_TIPO) VALUES ('Vinicius', 1, 2);
INSERT INTO ASSINANTE (NM_ASSINANTE, CD_RAMO, CD_TIPO) VALUES ('Jorge', 2, 2);
INSERT INTO ASSINANTE (NM_ASSINANTE, CD_RAMO, CD_TIPO) VALUES ('Paul', 1, 1);

INSERT INTO MUNICIPIO (DS_MUNICIPIO) VALUES ('Pelotas');
INSERT INTO MUNICIPIO (DS_MUNICIPIO) VALUES ('São José');

INSERT INTO ENDERECO (DS_ENDERECO, COMPLEMENTO, BAIRRO, CEP, CD_ASSINANTE, CD_MUNICIPIO) VALUES ('Natal', 'Casa', 'Bairro A', '88119-367', 1, 1);
INSERT INTO ENDERECO (DS_ENDERECO, COMPLEMENTO, BAIRRO, CEP, CD_ASSINANTE, CD_MUNICIPIO) VALUES ('Lugar A', 'Apartamento', 'Bairro B', '88160-000', 2, 2);
INSERT INTO ENDERECO (DS_ENDERECO, COMPLEMENTO, BAIRRO, CEP, CD_ASSINANTE, CD_MUNICIPIO) VALUES ('João Câmara', 'Depósito', 'Bairro C', '88798-659', 3, 1);
INSERT INTO ENDERECO (DS_ENDERECO, COMPLEMENTO, BAIRRO, CEP, CD_ASSINANTE, CD_MUNICIPIO) VALUES ('Lugar B', 'Escritório', 'Bairro D', '35987-365', 4, 1);

INSERT INTO TELEFONE (DDD, N_FONE, CD_ENDERECO) VALUES ('048', '999250781', 1);
INSERT INTO TELEFONE (DDD, N_FONE, CD_ENDERECO) VALUES ('047', '991250792', 2);
INSERT INTO TELEFONE (DDD, N_FONE, CD_ENDERECO) VALUES ('041', '999250792', 2);
INSERT INTO TELEFONE (DDD, N_FONE, CD_ENDERECO) VALUES ('011', '996840159', 3);
INSERT INTO TELEFONE (DDD, N_FONE, CD_ENDERECO) VALUES ('051', '991412176', 4);
INSERT INTO TELEFONE (DDD, N_FONE, CD_ENDERECO) VALUES ('054', '992451176', 4);
--END-- Inserção dos Dados

-- Questão 1 -- JOINS

-- RESPOSTA -- Letra (A) Listar os nomes dos assinantes, seguido dos dados do endereço e os telefones correspondentes.

SELECT assinante.nm_assinante, endereco.ds_endereco, endereco.complemento, endereco.bairro, endereco.cep, telefone.ddd, telefone.n_fone FROM assinante
INNER JOIN endereco ON endereco.cd_assinante = assinante.cd_assinante
INNER JOIN telefone ON telefone.cd_endereco = endereco.cd_endereco;

-- RESPOSTA -- Letra (B) Listar os nomes dos assinantes, seguido do seu ramo, ordenados por ramo e posteriormente por nome.

SELECT assinante.nm_assinante, ramo_atividade.ds_ramo FROM assinante
INNER JOIN ramo_atividade ON assinante.cd_ramo = ramo_atividade.cd_ramo
ORDER BY ramo_atividade.ds_ramo, assinante.nm_assinante;

-- RESPOSTA -- Letra (C) Listar os assinantes do município de Pelotas que são do tipo residencial.

SELECT assinante.nm_assinante, tipo_assinante.ds_tipo FROM assinante

-- RESPOSTA -- Letra (D) Listar os nomes dos assinantes que possuem mais de um telefone.


-- RESPOSTA -- Letra (E) Listar os nomes dos assinantes seguido do número do telefone, tipo de assinante comercial, com endereço em Natal ou João Câmara.

