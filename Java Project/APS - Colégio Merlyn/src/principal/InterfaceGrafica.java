package principal;

public abstract class InterfaceGrafica {

	/* Criar outra Classe para fazer a leitura de entradas do teclado */
	
	public static void welcome() {
		System.out.println("Bem vindo(a), digite o numero da operação desejada:");
	}
	
	public static void loginMode() {
		System.out.println("{}----------{}");
		System.out.println("Você deseja entrar como:");
		System.out.println("1 - Aluno");
		System.out.println("2 - Professor");
		System.out.println("3 - Diretor");
		System.out.println("0 - Sair do Sistema");
		System.out.println("{}----------{}");
	}
	
	public static void operationsDiretor() {
		System.out.println("{}----------{}");
		System.out.println("1 - Cadastrar Aluno;");
		System.out.println("2 - Cadastrar Professor;");
		System.out.println("3 - Cadastrar Sala;");
		System.out.println("4 - Cadastrar Diretor;");
		System.out.println("--------------");
		System.out.println("11 - Ver todos os Alunos;");
		System.out.println("22 - Ver todos os Professores;");
		System.out.println("33 - Ver todos as Salas;");
		System.out.println("44 - Ver todos os Diretores;");
		System.out.println("55 - Ver todas as Disciplinas;");
		System.out.println("--------------");
		System.out.println("111 - Deletar Aluno;");
		System.out.println("222 - Deletar Professor;");
		System.out.println("444 - Deletar Diretor;");
		System.out.println("--------------");
		System.out.println("1111 - Atualizar Aluno;");
		System.out.println("2222 - Atualizar Professor;");
		System.out.println("4444 - Atualizar Diretor;");
		System.out.println("--------------");
		System.out.println("8 - Relatórios;");
		System.out.println("--------------");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
		System.out.println("{}----------{}");
	}
	
	public static void operationsProfessor() {
		System.out.println("{}----------{}");
		System.out.println("1 - Colocar NP1 de uma Aluno;");
		System.out.println("2 - Colocar NP2 de uma Aluno;");
		System.out.println("--------------");
		System.out.println("11 - Ver todos as notas da NP1;");
		System.out.println("22 - Ver todos as notas da NP2;");
		System.out.println("33 - Ver todos os Alunos;");
		System.out.println("--------------");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
		System.out.println("{}----------{}");
	}
	
	public static void operationsAluno() {
		System.out.println("{}----------{}");
		System.out.println("1 - Ver NP1 de todas as Disciplinas;");
		System.out.println("2 - Ver NP2 de todas as Disciplinas;");
		System.out.println("--------------");
		System.out.println("0 - Sair do Sistema.");
		System.out.println("/ - Voltar ao Login.");
		System.out.println("{}----------{}");
	}
	
	/* CADASTROS */
	public static void cadastrarAluno() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite as informações do Aluno:");
		System.out.println("<-<==========>->");
	}
	
	public static void cadastrarSala() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite a informação da Sala:");
		System.out.println("<-<==========>->");
	}
	
	public static void cadastrarProfessor() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite as informações do Professor:");
		System.out.println("<-<==========>->");
	}
	
	public static void cadastrarDiretor() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite as informações do Diretor:");	
		System.out.println("<-<==========>->");
	}
	
	public static void cadastrarDisciplina() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite a informação da Disciplina:");
		System.out.println("<-<==========>->");
	}
	/* END * CADASTROS */
	
	/* DELETES */
	public static void deletarAluno() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite a matrícula do aluno que deseja deletar:");
		System.out.println("<-<==========>->");
	}
	
	public static void deletarProfessor() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite o CPF do Professor que deseja deletar:");
		System.out.println("<-<==========>->");
	}
	
	public static void deletarDiretor() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite o CPF do Diretor que deseja deletar:");
		System.out.println("<-<==========>->");
	}
	/* END * DELETES */
	
	/* UPDATES */
	public static void updateAluno() {
		System.out.println("<-<====<>====>->");
		System.out.println("Digite as novas informações para o Aluno:");
		System.out.println("<-<==========>->");
	}

	public static void updateProfessor() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite as novas informações para o Professor:");	
		System.out.println("<-<==========>->");
	}

	public static void updateDiretor() {
		System.out.println("<-<====<>====>->");
		System.out.println("	>Digite as novas informações para o Diretor:");
		System.out.println("<-<==========>->");
	}
	/* END * UPDATES */
	
	public static void resultAutenticacao(String result) {
		System.out.println(result);
	}
	
	public static void lineBreaker() {
		System.out.println("");
	}
	
	public static void separator() {
		System.out.println("<>============<>");
	}
	
	public static void separatorLight() {
		System.out.println("----------------");
		
		
	}
	
	public static void end() {
		System.out.println("Alterações salvas, fechando programa...");
	}

	public static void semDiretor() {
		System.out.println("Este sistema ainda não possui nenhum Diretor.");
		System.out.println("Por favor cadastre um Diretor:");
		
	}
	
	public static void semProfessor() {
		InterfaceGrafica.separatorLight();
		System.out.println("Este sistema ainda não possui nenhum Professor.");
		System.out.println("Voltando ao Login.");
		InterfaceGrafica.separatorLight();
	}
	
	public static void semAluno() {
		InterfaceGrafica.separator();
		System.out.println("Este sistema ainda não possui nenhum Aluno.");
		System.out.println("Voltando ao Login.");
		InterfaceGrafica.separator();
	}

	public static void semSala() {
		InterfaceGrafica.separatorLight();
		System.out.println("Este sistema ainda não possui nenhuma Sala.");
		System.out.println("Cadastre uma sala antes de cadastrar um Aluno");
		InterfaceGrafica.separatorLight();
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
			String l = "" + x.getSala().charAt(0);
			if(l.equals(Integer.toString(serie))) {
				System.out.println(x.getSala());
			}
		}
		
		System.out.println("Se não houver nenhuma, digite 0.");
		InterfaceGrafica.separator();
		
	}

	public static void backLogin() {
		System.out.println("Você deseja:");
		System.out.println("1 - Tentar Novamente;");
		System.out.println("0 - Voltar.");
	}

	public static void spaceInLine() {
		System.out.print("  |  ");
		
	}

	public static void getNP1() {
		System.out.println("Mostrando todas as NP1:");	
	}
	
	public static void getNP2() {
		System.out.println("Mostrando todas as NP2:");	
	}
	
	public static void setNP1() {
		System.out.println("Adicionar ou alterar NP1 de um Aluno:");
	}
	
	public static void setNP2() {
		System.out.println("Adicionar ou alterar NP1 de um Aluno:");
	}

	public static void getAllNP1() {
		System.out.println("Todas as NP1 dos seus Alunos:");
	}
	
	public static void getAllNP2() {
		System.out.println("Todas as NP2 dos seus Alunos:");
	}

	public static void relatoriosMenu() {
		System.out.println("{}----------{}");
		System.out.println("1 - Alunos com mais de 15 anos;");
		System.out.println("2 - Nomes dos Alunos com nota;");
		System.out.println("3 - Alunos sem notas;");
		System.out.println("4 - Alunos acima da média.");
		System.out.println("/ - Voltar.");
		System.out.println("{}----------{}");
	}
	

	public static void remindAlunoException() {
		InterfaceGrafica.separatorLight();
		System.out.println("Lembre-se que:");
		System.out.println("O nome é um texto, cpf são 11 números, rg são 7 números,");
		System.out.println("série somente números, data de nascimento está no fomato Dia/Mês/Ano,");
		System.out.println("turno é um texto e sala só pode ser uma das exibidas.");
	}

	public static void remindProfessorException() {
		InterfaceGrafica.separatorLight();
		System.out.println("Lembre-se que:");
		System.out.println("O nome é um texto, cpf são 11 números, rg são 7 números, ");
		System.out.println("data de nascimento está no fomato Dia/Mês/Ano e");
		System.out.println("a disciplina só pode ser uma das selecionadas.");
	}

	public static void mostrarSerie() {
		InterfaceGrafica.separatorLight();
		System.out.println("Series disponíveis");
		System.out.println("1 - 1a Serie;");
		System.out.println("2 - 2a Serie;");
		System.out.println("3 - 3a Serie;");
		System.out.println("4 - 4a Serie;");
		System.out.println("5 - 5a Serie;");
		System.out.println("6 - 6a Serie;");
		System.out.println("7 - 7a Serie;");
		System.out.println("8 - 8a Serie;");
		System.out.println("9 - 9a Serie.");
		InterfaceGrafica.separatorLight();
	}


	
	
}
