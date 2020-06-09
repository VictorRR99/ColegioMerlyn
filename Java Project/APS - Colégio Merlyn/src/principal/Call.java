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
		Professor userProfessor = null;
		Aluno userAluno = null;
		
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
					
					//Login Aluno
					
					isLogged = false;
					
					while(!isLogged) {
						if(Aluno.getLista().isEmpty()) {
							InterfaceGrafica.semAluno();
							break;
						}
						Aluno call = Aluno.getLista().get(0);
						boolean userCheck = false;
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite o Cpf:");
						cpf = leitorLogin.nextLine();
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite a Senha:");
						senha = leitorLogin.nextLine();
						
						userCheck = call.autenticar(cpf, senha);
						
						if(userCheck == true) {
							isLogged = true;
							
							InterfaceGrafica.lineBreaker();
							InterfaceGrafica.separator();
							System.out.println("Acesso Garantido!");
							for(Aluno x : Aluno.getLista()) {
								if(cpf.equals(x.getCpf())) {
									userAluno = x;
									break;
								}
							}
							System.out.println("Bem vindo, " + userAluno.getNome() +"!");
							InterfaceGrafica.separator();
							InterfaceGrafica.lineBreaker();
						}

						if(isLogged) {
							
							while(!userStop) {
								InterfaceGrafica.operationsAluno();
								
								userSelect = leitorSelection.nextLine();
								
								switch(userSelect) {
								case "1":

									System.out.println("Operação não Implementada.");
			
									break;
								case "2":

									System.out.println("Operação não Implementada.");
									
									break;
								case "3":

									System.out.println("Operação não Implementada.");
									
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
						//IF isLogged end
					}
					//Main While end
					
					break;
				case "2":
					
					//Login Professor
					
					isLogged = false;
					
					while(!isLogged) {
						if(Professor.getLista().isEmpty()) {
							InterfaceGrafica.semProfessor();
							break;
						}
						Professor call = Professor.getLista().get(0);
						boolean userCheck = false;
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite o Cpf:");
						cpf = leitorLogin.nextLine();
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite a Senha:");
						senha = leitorLogin.nextLine();
						
						userCheck = call.autenticar(cpf, senha);
						
						if(userCheck == true) {
							isLogged = true;
							
							InterfaceGrafica.lineBreaker();
							InterfaceGrafica.separator();
							System.out.println("Acesso Garantido!");
							for(Professor x : Professor.getLista()) {
								if(cpf.equals(x.getCpf())) {
									userProfessor = x;
									break;
								}
							}
							System.out.println("Bem vindo, " + userProfessor.getNome() +"!");
							InterfaceGrafica.separator();
							InterfaceGrafica.lineBreaker();
						}

						if(isLogged) {
							
							while(!userStop) {
								InterfaceGrafica.operationsProfessor();
								
								userSelect = leitorSelection.nextLine();
								
								switch(userSelect) {
								case "1":

									System.out.println("Operação não Implementada.");
			
									break;
								case "2":

									System.out.println("Operação não Implementada.");
									
									break;
								case "11":

									System.out.println("Operação não Implementada.");
									
									break;
								case "22":

									System.out.println("Operação não Implementada.");
									
									break;
								case "33":

									System.out.println("Operação não Implementada.");
									
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
						//IF isLogged end
					}
					//Main While end
					
					break;
				case "3":
					
					//Login Diretor

					isLogged = false;
					
					while(!isLogged) {
						Diretor call = Diretor.getListaDir().get(0);
						boolean userCheck = false;
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite o Cpf:");
						cpf = leitorLogin.nextLine();
						
						InterfaceGrafica.lineBreaker();
						System.out.println("Digite a Senha:");
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
									
									operacao.cadastrarDiretor();
									operacao.serializeAll();
									
									break;
								case "5":
									
									operacao.cadastrarDisciplina();
									operacao.serializeAll();
									
									break;
								case "11":
									
									operacao.verAlunos();
									
									break;
								case "22":
									
									operacao.verProfessores();
									
									break;
								case "33":
									
									operacao.verSalas();
									
									break;
								case "44":
									
									operacao.verDiretores();
									
									break;
								case "55":
									
									operacao.verDisciplinas();
									
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
						//IF isLogged end
					}
					//Main While end
					
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
				
	}
	
}
