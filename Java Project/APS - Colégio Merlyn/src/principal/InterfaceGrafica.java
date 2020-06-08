package principal;

import java.util.List;

public abstract class InterfaceGrafica {

	/* Criar outra Classe para fazer a leitura de entradas do teclado */
	
	public static void welcome() {
		System.out.println("Bem vindo(a), digite o numero da opera��o desejada:");
	}
	
	public static void loginMode() {
		System.out.println("Voc� deseja logar entrar:");
		System.out.println("1 - Aluno");
		System.out.println("2 - Professor");
		System.out.println("3 - Diretor");
		System.out.println("0 - Sair do Sistema");
	}
	
	public static void operationsDiretor() {
		System.out.println("1 - Cadastrar Aluno;");
		System.out.println("2 - Cadastrar Professor;");
		System.out.println("5 - Ver todos os Alunos;");
		System.out.println("6 - Ver todos os Professores;");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
	}
	
	public static void operationsProfessor() {
		System.out.println("1 - ****;");
		System.out.println("2 - ****;");
		System.out.println("5 - Ver todos os Alunos;");
		System.out.println("6 - ****;");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
	}
	
	public static void operationsAluno() {
		System.out.println("1 - ****");
		System.out.println("2 - ****");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
	}
	
	public static void cadastrarAluno() {
		System.out.println("Digite as informa��es do Aluno:");
	}
	
	public static void cadastrarProfessor() {
		System.out.println("Digite as informa��es do Professor:");
	}
	
	public static void cadastrarDiretor() {
		System.out.println("Digite as informa��es do Diretor:");	
	}
	
	public static void cadastrarDisciplina() {
		System.out.println("Digite a informa��o da Disciplina:");
	}
	
	public static void cadastrarSala() {
		System.out.println("Digite a informa��o da Sala:");		
	}
	
	public static void resultAutenticacao(String result) {
		System.out.println(result);
	}
	
	public static void lineBreaker() {
		System.out.println("");
	}
	
	public static void separator() {
		System.out.println("<>============<>");
	}
	
	public static void end() {
		System.out.println("Altera��es salvas, fechando programa...");
	}

	public static void semDiretor() {
		System.out.println("Este sistema ainda n�o possui nenhum Diretor.");
		System.out.println("Por favor cadastre um Diretor:");
		
	}
	
	public static void semDisciplina() {
		System.out.println("Este sistema ainda n�o possui nenhuma Disciplina.");
		System.out.println("Por favor cadastre uma Disciplina:");
		
	}

	public static void mostrarDisciplinas() {

		List<Disciplina> disciplinas = Disciplina.getLista();
		
		InterfaceGrafica.separator();
		System.out.println("As disciplinas existentes s�o:");
		InterfaceGrafica.lineBreaker();
		for(Disciplina x : disciplinas) {
			System.out.println(x.getNomeDisc());
		}
		InterfaceGrafica.separator();
		
	}

	

	

	

	
	
}
