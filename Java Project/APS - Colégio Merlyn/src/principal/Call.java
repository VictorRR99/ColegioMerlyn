package principal;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Call {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		/*Principal*/
		
		/* Leitura dos arquivos */
		Professor.desserialization();
		Diretor.desserialization();
		Aluno.desserialization();
		Sala.desserialization();
		Disciplina.desserialization();
		/* Leitura dos arquivos */
		
		System.out.println("Desserialização completa!");
		InterfaceGrafica.separator();
	
		System.out.println();
		
		
		/* Leitor do menu de login */
		Scanner leitorLogin = new Scanner(System.in);
		/* Leitor do menu */
		Scanner leitorSelection = new Scanner(System.in);

		//Arrumar o método do getLista de diretor pra dar Override na de Professor
		/* Checagem da existencia de um Diretor no sistema */
		List<Diretor> checagemDiretor = Diretor.getListaDir();
		/* Checagem da existencia de um Diretor no sistema */
		List<Disciplina> checagemDisciplina = Disciplina.getLista();
		
		/* Instancia para chamada das operacoes*/
		Operation operacao = new Operation();

		/* Usuarios */
		Diretor userDiretor = null;
		Professor userProfessor;
		Aluno userAluno;
		
		/* Login */
		String cpf, senha;
		boolean isLogged = false;
		/* SeleÃ§Ã£o nos menus */
		String userSelect;
		String userLogin;
		/* CondiÃ§Ã£o de parada While */
		boolean repStop = false;
		boolean userStop = false;
		
		InterfaceGrafica.welcome();
		
		/* Menu Principal das Operacoes */
		while(!repStop) {
			
			//Checagem se existe um Diretor no sistema
			if(!checagemDiretor.isEmpty()) {
				InterfaceGrafica.loginMode();
				
				userLogin = leitorSelection.nextLine();
				
				switch(userLogin) {
				case "1":
					
					break;
				case "2":
					
					break;
				case "3":
					
					//Login Diretor

					isLogged = false;
					
					while(!isLogged) {
						Diretor call = Diretor.getListaDir().get(0);
						boolean userCheck = false;
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite o cpf:");
						cpf = leitorLogin.nextLine();
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite a senha:");
						senha = leitorLogin.nextLine();
						
						userCheck = call.autenticar(cpf, senha);
						
						if(userCheck == true) {
							isLogged = true;
							
							InterfaceGrafica.lineBreaker();
							InterfaceGrafica.separator();
							System.out.println("Acesso Garantido!");
							for(Diretor x : Diretor.getListaDir()) {
								if(cpf.equals(x.getCpf())) {
									userDiretor = x;
									break;
								}
							}
							System.out.println("Bem vindo, " + userDiretor.getNome() +"!");
							InterfaceGrafica.separator();
							InterfaceGrafica.lineBreaker();
						}

						if(isLogged) {
							while(!userStop) {
								InterfaceGrafica.operationsDiretor();
								
								userSelect = leitorSelection.nextLine();
								
								switch(userSelect) {
								case "1":
									
									operacao.cadastrarAluno();
									operacao.serializeAll();
									
									break;
								case "2":
									
									operacao.cadastrarProfessor();
									operacao.serializeAll();
									
									break;
								case "3":
									
									operacao.cadastrarSala();
									operacao.serializeAll();
									
									break;
								case "4":
									
									operacao.cadastrarProfessor();
									operacao.serializeAll();
									
									break;
								case "5":
									
									operacao.verAlunos();
									
									break;
								case "6":
									
									operacao.verProfessores();
									
									break;
								case "0":
									
									repStop = true;
									userStop = true;
									
									break;
								case "/":
									
									userStop = true;
									
									break;
								default:
									
									System.out.println("Operação selecionada não existe.");
									
								}
								//Switch end
							}
							//While end
						}
						
					}
					//IF end
					
					break;
				case "0":
					repStop = true;
					userStop = true;
				}
				
				
				//Main Switch end
			}else {
				
				if(!checagemDisciplina.isEmpty()) {
					InterfaceGrafica.separator();
					InterfaceGrafica.semDiretor();
					InterfaceGrafica.separator();
					
					operacao.cadastrarDiretor();
					
					/*Serializar*/
					operacao.serializeAll();
				}else {
					InterfaceGrafica.separator();
					InterfaceGrafica.semDisciplina();
					InterfaceGrafica.separator();
					
					operacao.cadastrarDisciplina();
					
					InterfaceGrafica.separator();
					InterfaceGrafica.semDiretor();
					InterfaceGrafica.separator();
					
					operacao.cadastrarDiretor();
					
					/*Serializar*/
					operacao.serializeAll();
				}
				
				
				
			}
			
		}
		
		leitorSelection.close();
		
		operacao.closeScanners();
			
		/* Salvamento dos arquivos */
		operacao.serializeAll();
		/* Salvamento dos arquivos */
		
		InterfaceGrafica.separator();
		System.out.println("Serialização completa!");
		InterfaceGrafica.separator();
		InterfaceGrafica.end();
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\
		
		/*ClasseTeste1 e SubClasseTeste2*/
		/* Debugando serialization */
		
//		ClasseTeste1.desserialization();
//		SubClasseTeste2.desserialization();
//		System.out.println("Desserializaï¿½ï¿½o Completa!");
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
//				System.out.println("OP nï¿½o exite");
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
//		System.out.println("Serializaï¿½ï¿½o Completa!");
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\

		/* Serialization Test*/
		
//		Aluno victor = new Aluno("Victor Rodrigues", "08618351903", "6021469", "22/02/2001");
//		Aluno vinicius = new Aluno("Vinicius Amorim", "12345678911", "1234567", "04/08/2000");
//		Aluno leticia = new Aluno("Letï¿½cia Oliveira", "11987654321", "7654321", "16/07/2001");
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
