package principal;

import java.util.List;
import java.util.Scanner;

public class Operation {

	private Scanner leitorInt = new Scanner(System.in);
	private Scanner leitorStr = new Scanner(System.in);
	
	/* Vari�veis Cadastro */
	private String nome, cpf, rg, dtNasc, turno, sala, senha;
	private Disciplina disc;
	private int serie;
	
	public void cadastrarAluno(){
		InterfaceGrafica.cadastrarAluno();
		
		System.out.println("Digite nome:");
		nome = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite cpf:");
		cpf = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite rg:");
		rg = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite data de nascimento:");
		dtNasc = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite serie:");
		serie = leitorInt.nextInt();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite turno:");
		turno = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite sala:");
		sala = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		boolean check = Sala.checarSalaCerta(serie, sala);
		while(!check) {
			System.out.println("Digite uma sala v�lida:");
			sala = leitorStr.nextLine();
			InterfaceGrafica.lineBreaker();
		}
		
		System.out.println("Digite senha:");
		senha = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		//Arrumar a verifica��o dos problemas relacionado com serie e sala
		
		Diretor.cadastrarAluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
		
	}
	
	public void cadastrarProfessor() {
		InterfaceGrafica.cadastrarProfessor();
		
		System.out.println("Digite nome:");
		nome = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite cpf:");
		cpf = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite rg:");
		rg = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite data de nascimento:");
		dtNasc = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite senha:");
		senha = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite a disciplina a ser lecionada:");
		String check;
		check = leitorStr.nextLine();
		for(int i = 0; i < Disciplina.listaDisciplinas.size(); i++) {
			if(Disciplina.listaDisciplinas.get(i).getNomeDisc() == check) {
				disc = Disciplina.listaDisciplinas.get(i);
			}
		}
		InterfaceGrafica.lineBreaker();
		
		Diretor.cadastrarProfessor(nome, cpf, rg, dtNasc, senha, disc);
	}
	
	public void verAlunos() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Aluno> listaAlunos = Aluno.getLista();
		
		System.out.println("+Nomes dos Alunos:");
		InterfaceGrafica.lineBreaker();
		for(Aluno x : listaAlunos) {
			System.out.println(x.getNome());
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
	}
	
	public void verProfessores() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Professor> listaProfessores = Professor.getLista();
		
		System.out.println("+Nomes dos Professores:");
		InterfaceGrafica.lineBreaker();
		for(Professor x : listaProfessores) {
			System.out.println(x.getNome());
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
	}
	
	public void closeScanners() {
		leitorInt.close();
		leitorStr.close();
	}
	
}
