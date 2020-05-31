-- Facilitador SELECT
SELECT * FROM Pesquisador;
SELECT * FROM Artigo;
SELECT * FROM Referencia;
SELECT * FROM Autoria;
--END-- Facilitador SELECT

-- Facilitador DROP CONSTRAINT
ALTER TABLE Referencia DROP CONSTRAINT FK_Referencia_codArt;
ALTER TABLE Autoria DROP CONSTRAINT FK_Autoria_codArt;
ALTER TABLE Autoria DROP CONSTRAINT FK_Autoria_codPes;
--END-- Facilitador DROP CONSTRAINT

-- Facilitador DROP TABLE
DROP TABLE Pesquisador;
DROP TABLE Artigo;
DROP TABLE Referencia;
DROP TABLE Autoria;
--END-- Facilitador DROP TABLE

-- Tabelas
CREATE TABLE Pesquisador (
	codPes SERIAL NOT NULL, --Primary key
	nome VARCHAR(100)
);

CREATE TABLE Artigo (
	codArt SERIAL NOT NULL, --Primary key
	titulo VARCHAR(50),
	veiculo VARCHAR(50),
	ano VARCHAR(10)
);

CREATE TABLE Referencia (
	codRef SERIAL NOT NULL, --Primary key
	codArt INT NOT NULL --Foreign key
);

CREATE TABLE Autoria (
	codAut SERIAL NOT NULL, --Primary Key
	codArt INT NOT NULL, --Foreign Key
	codPes INT NOT NULL, --Foreign Key
	posicao INTEGER
);
--END-- Tabelas

-- Primary Keys
ALTER TABLE Pesquisador ADD PRIMARY KEY (codPes);
ALTER TABLE Artigo ADD PRIMARY KEY (codArt);
ALTER TABLE Referencia ADD PRIMARY KEY (codRef);
ALTER TABLE Autoria ADD PRIMARY KEY (codAut);
--END-- Primary Keys

-- Adicionando Foreign Keys
ALTER TABLE Referencia ADD CONSTRAINT FK_Referencia_codArt FOREIGN KEY (codArt) REFERENCES Artigo(codArt)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE Autoria ADD CONSTRAINT FK_Autoria_codArt FOREIGN KEY (codArt) REFERENCES Artigo(codArt)
ON UPDATE CASCADE
ON DELETE NO ACTION;

ALTER TABLE Autoria ADD CONSTRAINT FK_Autoria_codPes FOREIGN KEY (codPes) REFERENCES Pesquisador(codPes)
ON UPDATE CASCADE
ON DELETE NO ACTION;
--END-- Adicionando Foreing Keys

-- Inserção dos Dados
INSERT INTO Pesquisador (nome) VALUES ('Joao');
INSERT INTO Pesquisador (nome) VALUES ('Maria');
INSERT INTO Pesquisador (nome) VALUES ('Makise');
INSERT INTO Pesquisador (nome) VALUES ('Itaru');
INSERT INTO Pesquisador (nome) VALUES ('Jorge');
INSERT INTO Pesquisador (nome) VALUES ('Karen');

INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('Dinâmica de Superfluidos', 'VLDB Journal', '2006');
INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('Como Criar Buracos Negros', 'Science Magazine', '2005');
INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('A luz na verdade tem mais uma propriedade', 'Science Magazine', '2010');
INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('Como Guardar Memórias', 'Scientific American', '1995');
INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('Massa Alternante dos Gases', 'Revista Brasileira de Ciência', '2010');
INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('Estava Turing Errado', 'Revista Brasileira de Ciência', '2015');
INSERT INTO Artigo (titulo, veiculo, ano) VALUES ('Teoria do Multiverso', 'VLDB Journal', '2006');

INSERT INTO Referencia (codArt) VALUES (1);
INSERT INTO Referencia (codArt) VALUES (2);
INSERT INTO Referencia (codArt) VALUES (3);
INSERT INTO Referencia (codArt) VALUES (4);
INSERT INTO Referencia (codArt) VALUES (5);
INSERT INTO Referencia (codArt) VALUES (6);
INSERT INTO Referencia (codArt) VALUES (7);

INSERT INTO Autoria (codArt, codPes, posicao) VALUES (1, 1, 1);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (1, 6, 2);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (2, 3, 1);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (2, 4, 2);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (2, 5, 3);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (3, 2, 1);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (4, 3, 1);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (4, 4, 2);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (5, 1, 1);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (5, 2, 2);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (6, 6, 1);
INSERT INTO Autoria (codArt, codPes, posicao) VALUES (7, 6, 1);

--END-- Inserção de Dados

-- Pesquisas do Exercício 2

-- Letra (A) Obter a quantidade de artigos publicados.

SELECT COUNT(codArt) FROM Artigo;

-- Letra (B) Obter a quantidade de artigos publicados por ano.

SELECT COUNT(ano), ano From Artigo
GROUP BY ano
ORDER BY ano ASC;

-- Letra (C) Obter os nomes dos pesquisadores que, em 2006, publicaram artigos em veículo intitulado “VLDB Journal”, constando como primeiro autor.

SELECT Pesquisador.nome FROM Pesquisador
INNER JOIN Autoria ON Pesquisador.codPes = Autoria.codPes AND (posicao = 1)
INNER JOIN Artigo ON Autoria.codArt = Artigo.codArt AND (veiculo = 'VLDB Journal');

-- Letra (D) Para cada pesquisador, retornar seu nome e a quantidade de artigos publicados.

SELECT Pesquisador.nome, COUNT(Autoria.codPes) FROM Pesquisador
INNER JOIN Autoria ON Pesquisador.codPes = Autoria.codPes
GROUP BY Pesquisador.nome
ORDER BY COUNT(Autoria.codArt) ASC;

-- Letra (E) Para cada artigo, retornar seu título e o número de autores.

SELECT Artigo.titulo, COUNT(Autoria.codArt) FROM Artigo
INNER JOIN Autoria ON Artigo.codArt = Autoria.codArt
GROUP BY Artigo.codArt
ORDER BY COUNT(Artigo.codArt) ASC;

-- Letra (F) Obter os nomes dos pesquisadores que não foram primeiro autor de artigos.

SELECT Pesquisador.nome, Autoria.posicao FROM Pesquisador
INNER JOIN Autoria ON Pesquisador.codPes = Autoria.codPes AND (Autoria.posicao <> 1)
ORDER BY Pesquisador.nome ASC;

-- Letra (G)  Para cada pesquisador, retornar seu nome e o número de coautores (sem duplicatas) – PONTO EXTRA.

SELECT Pesquisador.nome, COUNT(Autoria.codArt)-1 FROM Autoria
INNER JOIN Pesquisador ON Autoria.codPes = Pesquisador.codPes 
INNER JOIN Artigo ON Autoria.codArt = Artigo.codArt
GROUP BY Pesquisador.nome;
