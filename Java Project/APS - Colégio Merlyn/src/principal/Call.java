package principal;

import java.util.List;
import java.util.Scanner;

public class Call {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		/*Principal*/
		
		/* Leitura dos arquivos */
		Professor.desserialization();
		Diretor.desserialization();
		Aluno.desserialization();
		Sala.desserialization();
		/* Leitura dos arquivos */
		
		System.out.println("Desserialização completa!");
		
		Scanner leitorSelection = new Scanner(System.in);
		
		Operation operacao = new Operation();

		/* Seleção */
		String userSelect;
		String userLogin;
		/* Condição de parada While */
		int rep=0;
		
		InterfaceGrafica.welcome();
		
		/* Menu das Operações */
		while(rep == 0) {
			
			InterfaceGrafica.loginMode();
			
			userLogin = leitorSelection.nextLine();
			
			switch(userLogin) {
			case "1":
				
				break;
			case "2":
				
				break;
			case "3":
				
				
				InterfaceGrafica.operations();
				
				userSelect = leitorSelection.nextLine();
				
				switch(userSelect) {
				case "1":
					
					operacao.cadastrarAluno();
					
					break;
				case "2":
					
					operacao.cadastrarProfessor();
					
					break;
				case "5":
					
					operacao.verAlunos();
					
					break;
				case "6":
					
					operacao.verProfessores();
					
					break;
				case "0":
					
					rep++;
					
					break;
				default:
					System.out.println("Operação não existe.");
					
				}
				
				break;
			
			}
			
			
			
		}
		
		leitorSelection.close();
		
		operacao.closeScanners();
			
		/* Salvamento dos arquivos */
		Professor.serialization();
		Diretor.serialization();
		Aluno.serialization();
		Sala.serialization();
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
