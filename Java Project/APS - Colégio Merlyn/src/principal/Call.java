package principal;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Call {
	
	public static void main(final String[] args) throws ClassNotFoundException, SQLException, IOException {

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

		/* Instancia para chamada das operacoes */
		Operation operacao = new Operation();
		
		/* Leitor do menu de login */
		Scanner leitorLogin = new Scanner(System.in);
		/* Leitor do menu */
		Scanner leitorSelection = new Scanner(System.in);
		/* Leitor dos Relatórios */
		Scanner leitorRelatorios = new Scanner(System.in);

		// Arrumar o Método do getLista de diretor pra dar Override na de Professor
		// Se for possivel claro
		//Talvez não seja possível no atual design do professor
		/* Checagem da existencia de um Diretor no sistema */
		List<Diretor> checagemDiretor = Diretor.getListaDir();
		/* Checagem da existencia de um Diretor no sistema */
		List<Disciplina> checagemDisciplina = Disciplina.getLista();
		/* Checagem da existencia de sala no sistema antes da criação de um aluno */
		List<Sala> checagemSala = Sala.getLista();

		if(Disciplina.getLista().isEmpty()) {
			operacao.pegarDisciplinas();
			operacao.serializeAll();
		}

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
		String relatorios;
		/* Condição de parada While */
		boolean repStop = false;
		boolean userStop = false;
		boolean relatorioStop = false;

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
								
								if(!operacao.usuarioVoltar()) {
									isLogged = true;
									userStop = true;
									break;
								}
								
							}

							if (isLogged) {

								while (!userStop) {
									InterfaceGrafica.operationsAluno();

									userSelect = leitorSelection.nextLine();

									switch (userSelect) {
										case "1":

											InterfaceGrafica.getNP1();
											operacao.getNP1(userAluno);
											operacao.serializeAll();
											
											break;
										case "2":

											InterfaceGrafica.getNP1();
											operacao.getNP2(userAluno);
											operacao.serializeAll();
											
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

							//Checagem de Autenticação
							//Else para poder tirar o usuário do loop caso o mesmo deseje
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
								
								if(!operacao.usuarioVoltar()) {
									isLogged = true;
									userStop = true;
									break;
								}
							}

							if (isLogged) {
								
								while (!userStop) {
									InterfaceGrafica.operationsProfessor();

									userSelect = leitorSelection.nextLine();

									switch (userSelect) {
										case "1":

											InterfaceGrafica.setNP1();
											operacao.setNP1(userProfessor);
											operacao.serializeAll();

											break;
										case "2":

											InterfaceGrafica.setNP2();
											operacao.setNP2(userProfessor);
											operacao.serializeAll();

											break;
										case "11":

											
											InterfaceGrafica.getAllNP1();
											operacao.getAllNP1(userProfessor);
											operacao.serializeAll();
										
											break;
										case "22":

											InterfaceGrafica.getAllNP2();
											operacao.getAllNP2(userProfessor);
											operacao.serializeAll();

											break;
										case "33":

											operacao.verAlunosDoProfessor(userProfessor);
											operacao.serializeAll();

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

							//Checagem de Autenticação
							//Else para poder tirar o usuário do loop caso o mesmo deseje
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
							}else {
								
								if(!operacao.usuarioVoltar()) {
									isLogged = true;
									userStop = true;
									break;
								}
								
						}

						if(isLogged) {
							while(!userStop) {
								InterfaceGrafica.operationsDiretor();
								
								userSelect = leitorSelection.nextLine();
								
								switch(userSelect) {
								case "1":
									
									if(!Sala.getLista().isEmpty()) {
										if(operacao.cadastrarAluno()) {
											System.out.println("Cadastro concluído com sucesso!");
										}
										operacao.serializeAll();
									}else {
										InterfaceGrafica.semSala();
									}
									
									
									break;
								case "2":
									
									if(operacao.cadastrarProfessor()) {
										System.out.println("Cadastro concluído com sucesso!");
									}
									operacao.serializeAll();
									
									break;
								case "3":
									
									if(operacao.cadastrarSala()) {
										System.out.println("Cadastro concluído com sucesso!");
									}
									operacao.serializeAll();
									
									break;
								case "4":
									
									if(operacao.cadastrarDiretor()) {
										System.out.println("Cadastro concluído com sucesso!");
									}
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
									System.out.println("Aluno deletado com sucesso!");
									operacao.serializeAll();
									
									break;
								case "222":
									
									InterfaceGrafica.deletarProfessor();
									operacao.deletarProfessor();
									System.out.println("Professor deletado com sucesso!");
									operacao.serializeAll();
									
									break;
								case "444":

									InterfaceGrafica.deletarDiretor();
									operacao.deletarDiretor(userDiretor.getCpf());
									System.out.println("Diretor deletado com sucesso!");
									operacao.serializeAll();
									
									break;
								case "1111":

									InterfaceGrafica.updateAluno();
									operacao.updateAluno();
									operacao.serializeAll();
									
									break;
								case "2222":

									InterfaceGrafica.updateProfessor();
									operacao.updateProfessor();
									operacao.serializeAll();
									
									break;
								case "4444":

									InterfaceGrafica.updateDiretor();
									operacao.updateDiretor();
									operacao.serializeAll();
									
									break;
								case "8":
									
									
									while(!relatorioStop) {
										
										InterfaceGrafica.relatoriosMenu();
										
										relatorios = leitorRelatorios.nextLine();
										
										try {
											switch(relatorios) {
											case "1":
												
												operacao.alunosComMaisDeTantosAnos();
												
												break;
											case "2":
												
												operacao.nomeAlunosComNota();
												
												break;
											case "3":
												
												operacao.alunosSemNotas();
												
												break;
											case "4":
												
												operacao.alunosAcimaMedia();
												
												break;
											case "/":
												
												relatorioStop = true;
												
												break;
											default:
												
												InterfaceGrafica.separator();
												System.out.println("Operação selecionada não existe.");
												InterfaceGrafica.separator();
											
											}
										} catch (IOException e) {
											e.printStackTrace();
										}
									}
									
									
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
			
				InterfaceGrafica.separator();
				InterfaceGrafica.semDiretor();
				InterfaceGrafica.separator();
					
				if(operacao.cadastrarDiretor()) {
					System.out.println("Cadastro concluído com sucesso!");
				}
					
				/*Serializar*/
				operacao.serializeAll();

			}
		}
		
		leitorSelection.close();
		leitorLogin.close();
		leitorRelatorios.close();
		
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
