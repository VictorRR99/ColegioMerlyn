package principal;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Call {
	
	public static void main(final String[] args) throws ClassNotFoundException, SQLException {

		/* Principal */

		/* Leitura dos arquivos */
		Professor.desserialization();
		Diretor.desserialization();
		Aluno.desserialization();
		Sala.desserialization();
		Disciplina.desserialization();
		/* Leitura dos arquivos */

		System.out.println("Desserialização completa!");
		InterfaceGrafica.separator();

		/* Leitor do menu de login */
		final Scanner leitorLogin = new Scanner(System.in);
		/* Leitor do menu */
		final Scanner leitorSelection = new Scanner(System.in);

		// Arrumar o Método do getLista de diretor pra dar Override na de Professor
		// Se for possivel claro
		//Talvez não seja possível no atual design do professor
		/* Checagem da existencia de um Diretor no sistema */
		final List<Diretor> checagemDiretor = Diretor.getListaDir();
		/* Checagem da existencia de um Diretor no sistema */
		final List<Disciplina> checagemDisciplina = Disciplina.getLista();

		/* Instancia para chamada das operacoes */
		final Operation operacao = new Operation();

		/* Usuarios */
		Diretor userDiretor = null;
		Professor userProfessor = null;
		Aluno userAluno = null;

		/* Login */
		String cpf, senha;
		boolean isLogged = false;
		/* Seleção nos menus */
		String userSelect;
		String userLogin;
		/* Condição de parada While */
		boolean repStop = false;
		boolean userStop = false;

		InterfaceGrafica.welcome();

		/* Menu Principal das Operacoes */
		while (!repStop) {

			// Checagem se existe um Diretor no sistema
			if (!checagemDiretor.isEmpty()) {
				InterfaceGrafica.loginMode();

				userLogin = leitorSelection.nextLine();

				switch (userLogin) {
					case "1":

						// Login Aluno

						isLogged = false;
						userStop = false;

						while (!isLogged) {
							if (Aluno.getLista().isEmpty()) {
								InterfaceGrafica.semAluno();
								break;
							}
							final Aluno call = Aluno.getLista().get(0);
							boolean userCheck = false;

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite o Cpf:");
							cpf = leitorLogin.nextLine();

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite a Senha:");
							senha = leitorLogin.nextLine();

							userCheck = call.autenticar(cpf, senha);

							if (userCheck == true) {
								isLogged = true;

								InterfaceGrafica.lineBreaker();
								InterfaceGrafica.separator();
								System.out.println("Acesso Garantido!");
								for (final Aluno x : Aluno.getLista()) {
									if (cpf.equals(x.getCpf())) {
										userAluno = x;
										break;
									}
								}
								System.out.println("Bem vindo, " + userAluno.getNome() + "!");
								InterfaceGrafica.separator();
								InterfaceGrafica.lineBreaker();
							}else {
								String continuar;
								
								System.out.println("Você deseja:");
								InterfaceGrafica.backLogin();
								
								continuar = leitorSelection.next();
								
								switch(continuar) {
								case "1":
									
									
									break;
								case "0":
									
									isLogged = true;
									userStop = true;
									
									break;
								default:
									
									InterfaceGrafica.separator();
									System.out.println("Operação selecionada não existe.");
									InterfaceGrafica.separator();
									
								}
							}

							if (isLogged) {

								while (!userStop) {
									InterfaceGrafica.operationsAluno();

									userSelect = leitorSelection.nextLine();

									switch (userSelect) {
										case "1":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "2":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "3":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "0":

											repStop = true;
											userStop = true;

											break;
										case "/":

											userStop = true;

											break;
										default:
											
											InterfaceGrafica.separator();
											System.out.println("Operação selecionada não existe.");
											InterfaceGrafica.separator();

									}
									// Switch end
								}
								// While end
							}
							// IF isLogged end
						}
						// Main While end

						break;
					case "2":

						// Login Professor

						isLogged = false;
						userStop = false;

						while (!isLogged) {
							if (Professor.getLista().isEmpty()) {
								InterfaceGrafica.semProfessor();
								break;
							}
							final Professor call = Professor.getLista().get(0);
							boolean userCheck = false;

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite o Cpf:");
							cpf = leitorLogin.nextLine();

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite a Senha:");
							senha = leitorLogin.nextLine();

							userCheck = call.autenticar(cpf, senha);

							if (userCheck == true) {
								isLogged = true;

								InterfaceGrafica.lineBreaker();
								InterfaceGrafica.separator();
								System.out.println("Acesso Garantido!");
								for (final Professor x : Professor.getLista()) {
									if (cpf.equals(x.getCpf())) {
										userProfessor = x;
										break;
									}
								}
								System.out.println("Bem vindo(a), " + userProfessor.getNome() + "!");
								InterfaceGrafica.separator();
								InterfaceGrafica.lineBreaker();
							}else {
								String continuar;
								
								InterfaceGrafica.separator();
								System.out.println("Você deseja:");
								InterfaceGrafica.separator();
								InterfaceGrafica.backLogin();
								
								
								continuar = leitorSelection.next();
								
								switch(continuar) {
								case "1":
									
									
									break;
								case "0":
									
									isLogged = true;
									userStop = true;
									
									break;
								default:
									
									InterfaceGrafica.separator();
									System.out.println("Operação selecionada não existe.");
									InterfaceGrafica.separator();
									
								}
							}

							if (isLogged) {
								
								while (!userStop) {
									InterfaceGrafica.operationsProfessor();

									userSelect = leitorSelection.nextLine();

									switch (userSelect) {
										case "1":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "2":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "11":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "22":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "33":

											InterfaceGrafica.separator();
											System.out.println("Operação não Implementada.");
											InterfaceGrafica.separator();

											break;
										case "0":

											repStop = true;
											userStop = true;

											break;
										case "/":

											userStop = true;

											break;
										default:

											InterfaceGrafica.separator();
											System.out.println("Operação selecionada não existe.");
											InterfaceGrafica.separator();

									}
									// Switch end
								}
								// While end
							}
							// IF isLogged end
						}
						// Main While end

						break;
					case "3":

						// Login Diretor

						isLogged = false;
						userStop = false;

						while (!isLogged) {
							final Diretor call = Diretor.getListaDir().get(0);
							boolean userCheck = false;

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite o Cpf:");
							cpf = leitorLogin.nextLine();

							InterfaceGrafica.lineBreaker();
							System.out.println("Digite a Senha:");
							senha = leitorLogin.nextLine();

							userCheck = call.autenticar(cpf, senha);

							if (userCheck == true) {
								isLogged = true;

								InterfaceGrafica.lineBreaker();
								InterfaceGrafica.separator();
								System.out.println("Acesso Garantido!");
								for (final Diretor x : Diretor.getListaDir()) {
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
									
									//Colocar um IF aqui para verificar se a lista de salas nï¿½o estï¿½ vazia
									operacao.cadastrarAluno();
									operacao.serializeAll();
									
									break;
								case "2":
									
									//Colocar um IF aqui para verificar se a lista de disciplinas nï¿½o estï¿½ vazia
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
								case "111":

									InterfaceGrafica.deletarAluno();
									operacao.deletarAluno();
									
									break;
								case "222":
									
									InterfaceGrafica.deletarProfessor();
									operacao.deletarProfessor();
									
									break;
								case "444":

									InterfaceGrafica.deletarDiretor();
									operacao.deletarDiretor(userDiretor.getCpf());
									
									break;
								case "0":
									
									repStop = true;
									userStop = true;
									
									break;
								case "/":
									
									userStop = true;
									
									break;
								default:
									
									InterfaceGrafica.separator();
									System.out.println("Operação selecionada não existe.");
									InterfaceGrafica.separator();
									
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
					break;
				default:
					
					InterfaceGrafica.separator();
					System.out.println("Operação selecionada não existe.");
					InterfaceGrafica.separator();
					
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
