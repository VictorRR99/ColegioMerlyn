package principal;

public abstract class InterfaceGrafica {

	/* Criar outra Classe para fazer a leitura de entradas do teclado */
	
	public static void welcome() {
		System.out.println("Bem vindo(a), digite o numero da operação desejada:");
	}
	
	public static void loginMode() {
		System.out.println("Você deseja logar como:");
		System.out.println("1 - Aluno");
		System.out.println("2 - Professor");
		System.out.println("3 - Diretor");
		System.out.println("0 - Sair do Sistema");
	}
	
	public static void operations() {
		System.out.println("1 - Cadastrar Aluno;");
		System.out.println("2 - Cadastrar Professor;");
		System.out.println("5 - Ver todos os Alunos;");
		System.out.println("6 - Ver todos os Professores;");
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
		System.out.println("Por favor crie um Diretor:");
		
	}

	

	
	
}
