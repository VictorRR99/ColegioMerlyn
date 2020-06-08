package principal;

/* Verificar se é possível usar um ENUM para não utilizar IFs */
/* Verificar se é possível deixar este ENUM útil */

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
