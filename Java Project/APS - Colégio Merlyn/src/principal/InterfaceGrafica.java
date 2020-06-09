package principal;

import java.util.List;

public abstract class InterfaceGrafica {

	/* Criar outra Classe para fazer a leitura de entradas do teclado */
	
	public static void welcome() {
		System.out.println("Bem vindo(a), digite o numero da operação desejada:");
	}
	
	public static void loginMode() {
		System.out.println("Você deseja entrar como:");
		System.out.println("1 - Aluno");
		System.out.println("2 - Professor");
		System.out.println("3 - Diretor");
		System.out.println("0 - Sair do Sistema");
	}
	
	public static void operationsDiretor() {
		System.out.println("1 - Cadastrar Aluno;");
		System.out.println("2 - Cadastrar Professor;");
		System.out.println("3 - Cadastrar Sala;");
		System.out.println("4 - Cadastrar Diretor;");
		System.out.println("5 - Cadastrar Disciplina;");
		System.out.println("11 - Ver todos os Alunos;");
		System.out.println("22 - Ver todos os Professores;");
		System.out.println("33 - Ver todos as Salas;");
		System.out.println("44 - Ver todos os Diretores;");
		System.out.println("55 - Ver todas as Disciplinas;");
		System.out.println("111 - Deletar Aluno;");
		System.out.println("222 - Deletar Professor;");
		System.out.println("444 - Deletar Diretor;");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
	}
	
	public static void operationsProfessor() {
		System.out.println("1 - Colocar NP1 de uma Aluno;");//Problema sem Alunos
		System.out.println("2 - Colocar NP2 de uma Aluno;");//Problema sem Alunos
		System.out.println("11 - Ver todos as notas da NP1;");
		System.out.println("22 - Ver todos as notas da NP1;");
		System.out.println("33 - Ver todos os Alunos;");//Problema sem Alunos
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
	}
	
	public static void operationsAluno() {
		System.out.println("1 - Ver NP1 de todas as Disciplinas;");
		System.out.println("2 - Ver NP2 de todas as Disciplinas;");
		System.out.println("3 - Ver Média de todas as Disciplinas;");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
	}
	
	public static void cadastrarAluno() {
		System.out.println("Digite as informações do Aluno:");
	}
	
	public static void cadastrarProfessor() {
		System.out.println("Digite as informações do Professor:");
	}
	
	public static void cadastrarDiretor() {
		System.out.println("Digite as informações do Diretor:");	
	}
	
	public static void cadastrarDisciplina() {
		System.out.println("Digite a informação da Disciplina:");
	}
	
	public static void cadastrarSala() {
		System.out.println("Digite a informação da Sala:");		
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
		System.out.println("Alterações salvas, fechando programa...");
	}

	public static void semDiretor() {
		System.out.println("Este sistema ainda não possui nenhum Diretor.");
		System.out.println("Por favor cadastre um Diretor:");
		
	}
	
	public static void semDisciplina() {
		System.out.println("Este sistema ainda não possui nenhuma Disciplina.");
		System.out.println("Por favor cadastre uma Disciplina:");
		
	}
	
	public static void semProfessor() {
		InterfaceGrafica.separator();
		System.out.println("Este sistema ainda não possui nenhum Professor.");
		System.out.println("Voltando ao Login.");
		InterfaceGrafica.separator();
	}
	
	public static void semAluno() {
		InterfaceGrafica.separator();
		System.out.println("Este sistema ainda não possui nenhum Aluno.");
		System.out.println("Voltando ao Login.");
		InterfaceGrafica.separator();
	}

	public static void mostrarDisciplinas() {

		InterfaceGrafica.separator();
		System.out.println("As disciplinas existentes são:");
		InterfaceGrafica.lineBreaker();
		for(Disciplina x : Disciplina.getLista()) {
			System.out.println(x.getNomeDisc());
		}
		InterfaceGrafica.separator();
		
	}

	public static void mostrarSalas(int serie) {
		
		InterfaceGrafica.separator();
		
		System.out.println("Salas disponíveis pra série em questão: ");
		InterfaceGrafica.lineBreaker();
		for(Sala x : Sala.getLista()) {
			if("" + x.getSala().charAt(0) == Integer.toString(serie)) {
				System.out.println(x.getSala());
			}
		}
		
		InterfaceGrafica.separator();
		
	}

	

	

	
	
}
