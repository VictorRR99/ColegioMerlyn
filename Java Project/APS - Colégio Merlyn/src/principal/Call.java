package principal;

import java.util.Scanner;

public class Call {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		/*Principal*/
		
		/* Leitura dos arquivos */
		Professor.desserialization();
		Diretor.desserialization();
		Aluno.desserialization();
//		Sala.desserialization();
//		Disciplina.desserialization();
		/* Leitura dos arquivos */
		
		System.out.println("Desserialização completa!");
		
		
		/* PROBLEMA DE SERIALIZAÇÃO COM O CONSTRUTOR */
		
		int rep=0;
		
		Scanner leitorInt = new Scanner(System.in);
		Scanner leitorStr = new Scanner(System.in);
		
		Disciplina disciplina = new Disciplina("Programação");
		
		Diretor diretor = new Diretor("SuperDiretor", "11", "11", "11", disciplina);
		
		InterfaceGrafica.welcome();
		
		/*Variáveis Cadastro Aluno*/
		String nome, cpf, rg, dtNasc, turno, sala, senha;
		int serie;
		
		/**/
		int userSelect = leitorInt.nextInt();
		
		while(rep == 0) {
			
			InterfaceGrafica.operations();
			
			if(userSelect == 1 ) {
				InterfaceGrafica.cadastrarAluno();
				
				System.out.println("Digite nome:");
				nome = leitorStr.nextLine();
				
				System.out.println("Digite cpf:");
				cpf = leitorStr.nextLine();
				
				System.out.println("Digite rg:");
				rg = leitorStr.nextLine();
				
				System.out.println("Digite data de nascimento:");
				dtNasc = leitorStr.nextLine();
				
				System.out.println("Digite serie:");
				serie = leitorInt.nextInt();
				
				System.out.println("Digite turno:");
				turno = leitorStr.nextLine();
				
				System.out.println("Digite sala:");
				sala = leitorStr.nextLine();
				
				System.out.println("Digite senha:");
				senha = leitorStr.nextLine();
				
				Professor.cadastrarAluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
				
			}else if(userSelect == 9) {
				rep = 1;
				
			}else {
				System.out.println("Operação não existente");
			}
				
			//Sair repetição
			
		}
	
		leitorInt.close();
		leitorStr.close();
			
		/* Salvamento dos arquivos */
		Professor.serialization();
		Diretor.serialization();
		Aluno.serialization();
//		Sala.serialization();
//		Disciplina.serialization();
		/* Salvamento dos arquivos */
		
		System.out.println("Serialização completa!");
		
		InterfaceGrafica.end();
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\
		
		/*ClasseTeste1 e SubClasseTeste2*/
		/* Debugando serialization */
		
//		ClasseTeste1.desserialization();
//		SubClasseTeste2.desserialization();
//		System.out.println("Desserialização Completa!");
//				
//		int rep=0;
//		
//		Scanner leitorInt = new Scanner(System.in);
//		Scanner leitorStr = new Scanner(System.in);
//		
//		int select;
//		
//		String nome, dataNasc;
//		
//		while(rep == 0) {
//			
//			
//			System.out.println("1 - Cadastrar Classe");
//			System.out.println("2 - Cadastrar SubClasse");
//			System.out.println("5 - Sair");
//			
//			select = leitorInt.nextInt();
//			
//			if(select == 1) {
//				System.out.println("Digite nome da Classe:");
//				nome = leitorStr.nextLine();
//				
//				System.out.println("Digite a data de nascimento da Classe:");
//				dataNasc = leitorStr.nextLine();
//				
//				ClasseTeste1.cadastrarClasse(nome, dataNasc);
//				
//			}else if(select == 2) {
//				System.out.println("Digite nome da SubClasse:");
//				nome = leitorStr.nextLine();
//				
//				System.out.println("Digite a data de nascimento da Classe:");
//				dataNasc = leitorStr.nextLine();
//				
//				SubClasseTeste2.cadastrarSubClasse(nome, dataNasc);
//			}else if(select == 5){
//				rep++;
//			}else {
//				System.out.println("OP não exite");
//				rep++;
//				break;
//			}
//			
//		}
//		
//		leitorInt.close();
//		leitorStr.close();
//		
//		ClasseTeste1.serialization();
//		SubClasseTeste2.serialization();
//		System.out.println("Serialização Completa!");
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\

		/* Serialization Test*/
		
//		Aluno victor = new Aluno("Victor Rodrigues", "08618351903", "6021469", "22/02/2001");
//		Aluno vinicius = new Aluno("Vinicius Amorim", "12345678911", "1234567", "04/08/2000");
//		Aluno leticia = new Aluno("Letícia Oliveira", "11987654321", "7654321", "16/07/2001");
//		Aluno samuel = new Aluno("Samuel Costa", "12345612345", "1234123", "01/01/2001");
//		Aluno altair = new Aluno("Altair Vega", "99224852158", "9874563", "09/05/1995");
//
//		List<Pessoa> listaAlunos = new ArrayList<>();
//		
//		listaAlunos.add(victor);
//		listaAlunos.add(vinicius);
//		listaAlunos.add(leticia);
//		listaAlunos.add(samuel);
//		listaAlunos.add(altair);
//		
//		List<Pessoa> listaProfessores = new ArrayList<>();
//		
//		Pessoa.saveObjectList(listaAlunos, "Alunos");
//		System.out.println("Serializado!");
		
		/* Original */
        
//		Aluno victor = new Aluno("Victor", "08618351903", "6021469", "22/02/2020");
//		
//		System.out.println("\n*Quantidade de Pessoas");
//		System.out.println(victor.getPessoaCount());
//		
//		System.out.println("\n===============");
//		
//		//All Pessoas
		
//		System.out.println("\n*Nome de todas as pessoas cadastradas na Escola");
//		List<String> allPessoas = new ArrayList<String>();
//		
//		allPessoas.addAll(victor.getAllPessoa());
//		
//		for(String x : allPessoas) {
//			System.out.println(x);
//		}
		
//		//All Pessoas
//		
//		System.out.println("\n===============");
//		
//		System.out.println("\n*Data de Nascimento de: " + victor.getNome());
//		System.out.println(victor.getPessoaDtNasc());
		
	}
	
}
