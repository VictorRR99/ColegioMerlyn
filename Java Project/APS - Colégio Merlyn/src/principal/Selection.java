package principal;

/* Verificar se � poss�vel usar um ENUM para n�o utilizar IFs */
/* Verificar se � poss�vel deixar este ENUM �til */

public enum Selection {
	cadastroAluno(1), cadastroProfessor(2), verAlunos(5), verProfessores(6), exit(0);
	
	private int operacao;
	
	Selection(int x){
		operacao = x;
	}
	
	public int metodo() {
		
		return operacao;
		
	}
	
}
