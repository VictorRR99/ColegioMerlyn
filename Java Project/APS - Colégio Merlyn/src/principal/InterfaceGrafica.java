package principal;

public abstract class InterfaceGrafica {

	/* Criar outra Classe para fazer a leitura de entradas do teclado */
	
	public static void welcome() {
		
		System.out.println("Bem vindo(a), digite o numero da operação desejada:");
		
	}
	
	public static void operations() {
		
		System.out.println("1 - Cadastrar Aluno");
		System.out.println("9 - Sair do Sistema");
		
	}
	
	public static void cadastrarAluno() {
		
		System.out.println("Digite as informações do Aluno:");
		
	}
	
	public static void spacer() {
		
		System.out.println("\n");
		
	}
	
	public static void end() {
		
		System.out.println("Alterações salvas, fechando programa...");
		
	}
	
}
