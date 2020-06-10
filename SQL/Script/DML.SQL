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

--Facilitador DROP CONSTRAINT
ALTER TABLE Aluno DROP CONSTRAINT aluno_pessoa_cd_pessoa_fk;
ALTER TABLE Aluno DROP CONSTRAINT aluno_cd_sala_fk;
ALTER TABLE Notas DROP CONSTRAINT notas_cd_aluno_fk;
ALTER TABLE Notas DROP CONSTRAINT notas_cd_disc_fk;
ALTER TABLE Professor DROP CONSTRAINT professor_cd_pessoa_fk;
ALTER TABLE Professor DROP CONSTRAINT professor_cd_disc_fk;
ALTER TABLE Diretor DROP CONSTRAINT diretor_cd_pessoa_fk;
ALTER TABLE Sala_Disc DROP CONSTRAINT sala_disc_cd_sala_fk;
ALTER TABLE Sala_Disc DROP CONSTRAINT sala_disc_cd_disc_fk;
--END--Facilitador DROP CONSTRAINT

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

-- Facilitador DELETE
DELETE FROM Diretor;
DELETE FROM Professor;
DELETE FROM Sala_Disc;
DELETE FROM Disciplina;
DELETE FROM Notas;
DELETE FROM Aluno;
DELETE FROM Sala;
DELETE FROM Pessoa;
--END-- Facilitador DELETE

-- PESQUISAS NO SISTEMA

-- ALUNOS COM MAIS DE 15 ANOS
SELECT nome FROM Pessoa INNER JOIN Aluno ON cd_pessoa = cd_aluno 
AND DATE_PART('year', NOW()) - DATE_PART('year', dt_nasc) > 15;

-- NOME DE TODOS OS ALUNOS COM SUAS NOTAS
SELECT Aluno.cd_aluno, Notas.np1, Notas.np2 FROM Aluno LEFT OUTER JOIN Notas ON Aluno.cd_aluno = Notas.cd_aluno
UNION
SELECT Aluno.cd_aluno, Notas.np1, Notas.np2 FROM Aluno RIGHT OUTER JOIN Notas ON Aluno.cd_aluno = Notas.cd_aluno;

-- NOME DOS ALUNOS QUE NÃO POSSUEM NOTAS
SELECT Pessoa.nome FROM Pessoa
INNER JOIN Aluno ON Pessoa.cd_pessoa = Aluno.cd_pessoa
LEFT JOIN Notas ON NP1 = NULL OR NP2 = NULL;

-- NOME DOS ALUNOS QUE ESTÃO ACIMA DA MÉDIA
SELECT pessoa.nome FROM Aluno 
INNER JOIN Pessoa ON Pessoa.cd_pessoa = Aluno.pessoa_cd_pessoa
	WHERE cd_aluno = (SELECT cd_aluno FROM Notas WHERE (NP1 + NP2)/2 >= 7);
