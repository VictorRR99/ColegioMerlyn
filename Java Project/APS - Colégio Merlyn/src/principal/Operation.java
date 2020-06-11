package principal;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Scanner;

import bdConnection.Conexao;

public class Operation {

	private Scanner leitorInt = new Scanner(System.in);
	private Scanner leitorStr = new Scanner(System.in);
	private Scanner leitorFloat = new Scanner(System.in);
	private Scanner leitorVoltar = new Scanner(System.in);
	
	private String sql;
	
	/* Operações do Diretor*/
	@SuppressWarnings("all")
	public boolean cadastrarAluno() throws SQLException{
		InterfaceGrafica.cadastrarAluno();
		
		String nome, cpf, rg, dtNasc = null, sala, turno, senha;
		int serie = 0;
		
		
		System.out.println("Digite nome:");
		nome = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite cpf:");
		cpf = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite rg:");
		rg = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		boolean lock = true;
		
		System.out.println("Digite data de nascimento:");
		while(lock) {
			dtNasc = leitorStr.nextLine();
			InterfaceGrafica.lineBreaker();
			if(dtNasc.length() != 10) {
				System.out.println("Data inserida errada, por favor insira corretamente!");
				System.out.println("Modelo de data: 31/12/2020");
				InterfaceGrafica.lineBreaker();
			}else {
				lock = false;
			}
		}
		
		try {
			System.out.println("Digite serie:");
			InterfaceGrafica.mostrarSerie();
			
			boolean serieValida = false;
			int a[] = new int[9];
			int numSerie=1;
			
			for(int i=0; i<9; i++) {
				a[i] = numSerie;
				numSerie++;
			}
			
			
			while(!serieValida) {
				serie = leitorInt.nextInt();
				for(int i = 0; i<9; i++) {
					if(serie == a[i]) {
						serieValida = true;
						break;
					}
				}
				System.out.println("Serie inválida.");
				if(!this.usuarioVoltar()) {
					return false;
				}
			}
			
			InterfaceGrafica.lineBreaker();
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente, serie deve ser somente números.");
			InterfaceGrafica.separatorLight();
			
			return false;
		}
		
		
		InterfaceGrafica.mostrarSalas(serie);
		System.out.println("Digite sala:");
		sala = leitorStr.nextLine();
		if(sala.equals("0")) {
			System.out.println("Cadastre uma sala que comece com o número da série:" + serie);
			return false;
		}
		InterfaceGrafica.lineBreaker();
		
		//Checagem se não há inconsistência nas séries e salas
		boolean check = Sala.checarSalaCerta(serie, sala);
		while(!check) {
			
			System.out.println("Sala errada para série escolhida!");
			System.out.println("Digite uma sala válida:");
			sala = leitorStr.nextLine();
			InterfaceGrafica.lineBreaker();
			check = Sala.checarSalaCerta(serie, sala);
			
			//Voltar
			
			if(!this.usuarioVoltar()) {
				return false;
			}
			
		}
		
		System.out.println("Digite turno:");
		turno = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite senha:");
		senha = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		boolean excecao = false;
		try {
			
			SQLcommand.insertAluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
			Diretor.cadastrarAluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
			
			Aluno provi = null;
			
			for(Aluno x : Aluno.getLista()) {
				if(x.getCpf().equals(cpf)) {
					provi = x;
				}
			}	
			
			if(provi == null) {
				
			}else {
				for(Professor x: Professor.getLista()) {
					if(x.getSala().equals(provi.getSala())) {
						
					}
				}
			}
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindAlunoException();
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindAlunoException();
			InterfaceGrafica.separatorLight();
		}
		
		if(!excecao) {
			System.out.println("Cadastro concluído com sucesso!");
			return true;
		}
		
		return false;
	}
	
	public boolean cadastrarProfessor() throws SQLException {
		
		String nome, cpf, rg, dtNasc, senha;
		Disciplina disc = null;
		
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
		InterfaceGrafica.mostrarDisciplinas();
		String check;
		check = leitorStr.nextLine();
			
		for(Disciplina x : Disciplina.getLista()) {
			if(x.getNomeDisc().equals(check)) {
				disc = x;
			}
		}
		InterfaceGrafica.lineBreaker();
		
		
		System.out.println("Digite a sala para qual o professor irá lecionar:");
		this.verSalas();
		String sala = leitorStr.nextLine();

		
		for(Professor x : Professor.getLista()) {
			if(x.getCpf().equals(cpf)) {
				x.setSala(Sala.getSalaSala(sala));
				x.receberAlunos();
			}
		}

		boolean excecao = false;
		try {
			
			SQLcommand.insertProfessor(nome, cpf, rg, dtNasc, senha, disc);
			
			Diretor.cadastrarProfessor(nome, cpf, rg, dtNasc, senha, disc);
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindProfessorException();
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindProfessorException();
			InterfaceGrafica.separatorLight();
			excecao = true;
		}
		
		if(!excecao) {
			System.out.println("Cadastro concluído com sucesso!");
			return true;
		}
		
		return false;
		
	}
	
	@SuppressWarnings("all")
	public boolean cadastrarSala() throws SQLException {
		
		String numSala;
		
		InterfaceGrafica.cadastrarSala();
		
		System.out.println("Digite numero da Sala:");
		numSala = leitorStr.nextLine();
		
		boolean serieValida = false;
		int a[] = new int[9];
		int numSerie=1;
		
		for(int i=0; i<9; i++) {
			a[i] = numSerie;
			numSerie++;
		}
		
		while(!serieValida) {
			
			String comparar = "" + numSala.charAt(0);
			
			for(int i = 0; i < 9; i++) {
				if(comparar.equals(Integer.toString(a[i]))) {
					serieValida = true;
					break;
				}
			}
			
			//Voltar
			
			if(!serieValida) {
				System.out.println("Sala inválida.");
				if(!this.usuarioVoltar()) {
					return false;
				}
				System.out.println("Digite numero da sala");
				numSala = leitorStr.nextLine();
			}
			
		}

		InterfaceGrafica.lineBreaker();
		
		boolean excecao = false;
		try {
			
			SQLcommand.insertSala(numSala);
			
			Diretor.cadastrarSala(numSala);
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindProfessorException();
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindProfessorException();
			InterfaceGrafica.separatorLight();
		}
		
		if(!excecao) {
			System.out.println("Cadastro concluído com sucesso!");
			return true;
		}
		
		return false;
	}
	
	public boolean cadastrarDiretor() throws SQLException {
		
		String nome, cpf, rg, dtNasc, senha;
		
		InterfaceGrafica.cadastrarDiretor();
		
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
		
		boolean excecao = false;
		try {
			
			SQLcommand.insertDiretor(nome, cpf, rg, dtNasc, senha);
			
			Diretor.cadastrarDiretor(nome, cpf, rg, dtNasc, senha);
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindProfessorException();
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.remindProfessorException();
			InterfaceGrafica.separatorLight();
		}
		
		if(!excecao) {
			System.out.println("Cadastro concluído com sucesso!");
			return true;
		}
		
		return false;
        
	}

	public boolean deletarAluno() throws SQLException {
		int matDeleta = leitorInt.nextInt();
		
		boolean excecao = false;
		try {
			
			Aluno.deletarAluno(matDeleta);
			
			SQLcommand.deletarAluno(matDeleta);
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.separatorLight();
		}
		
		if(!excecao) {
			return true;
		}
		
		InterfaceGrafica.lineBreaker();
		return false;
	}	
	
	public boolean deletarProfessor() throws SQLException {
		
		String cpf;
		
		cpf = leitorStr.nextLine();
		
		boolean excecao = false;
		try {
			
			SQLcommand.deletarProfessor(cpf);
			
			Professor.deletarProfessor(cpf);
			
			
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.separatorLight();
		}
		
		if(!excecao) {
			return true;
		}
		
		return false;
        
	}
	
	public boolean deletarDiretor(String cpfDigitado) throws SQLException {
		
		String cpf;
		
		cpf = leitorStr.nextLine();
		boolean check = true;
		
		while(check) {
			if(cpfDigitado.equals(cpf)) {
				System.out.println("Você não pode se deletar!");
				
				if(!this.usuarioVoltar()) {
					return false;
				}
				
				System.out.println("Digite um CPF válido:");
				cpf = leitorStr.nextLine();
				
			}else {
				check = false;
			}
		}
		
		boolean excecao = false;
		try {
			
			SQLcommand.deletarDiretor(cpf);
			
			Diretor.deletarDiretor(cpf);
			
		}catch(SQLException e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro ao inserir os dados no Banco.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.separatorLight();
			
			excecao = true;
		}catch(Exception e) {
			InterfaceGrafica.separatorLight();
			System.out.println("Ocorreu um erro inesperado.");
			System.out.println("Por favor, tente novamente.");
			InterfaceGrafica.separatorLight();
		}
		
		if(!excecao) {
			return true;
		}
		
		InterfaceGrafica.lineBreaker();
		return false;
	}
	
	public void updateAluno() throws SQLException {
		System.out.println("Digite a matrícula do aluno que deseja alterar:");
		this.deletarAluno();
		InterfaceGrafica.lineBreaker();
		
		this.cadastrarAluno();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Aluno alterado com sucesso!");
		InterfaceGrafica.lineBreaker();
	}
	
	public void updateProfessor() throws SQLException {
		System.out.println("Digite o CPF do Professor que deseja alterar:");
		this.deletarProfessor();
		InterfaceGrafica.lineBreaker();
		
		this.cadastrarProfessor();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Professor alterado com sucesso!");
		InterfaceGrafica.lineBreaker();
	}
	
	public void updateDiretor() throws SQLException {
		System.out.println("Digite o CPF do Diretor que deseja alterar:");
		String a = null;
		this.deletarDiretor(a);
		InterfaceGrafica.lineBreaker();
		
		this.cadastrarDiretor();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Diretor alterado com sucesso!");
		InterfaceGrafica.lineBreaker();
	}
	
	public void verProfessores() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Professor> listaProfessores = Professor.getLista();
		
		System.out.println("+Nomes dos Professores:");
		
		System.out.print("Nome");
		InterfaceGrafica.spaceInLine();
		System.out.print("CPF");
		InterfaceGrafica.spaceInLine();
		System.out.print("Disciplina");
		InterfaceGrafica.spaceInLine();
		System.out.print("Sala");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Professor x : listaProfessores) {
			System.out.print(x.getNome());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getCpf());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getDisc());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getSala());
			
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
	}
	
	public void verSalas() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Sala> listaSalas = Sala.getLista();
		
		System.out.println("+Numeros das Salas:");
		InterfaceGrafica.lineBreaker();
		for(Sala x : listaSalas) {
			System.out.println(x.getSala());
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	
	public void verDiretores() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Diretor> listaDiretores = Diretor.getListaDir();
		
		System.out.println("+Nomes dos Diretores:");
		
		System.out.print("Nome");
		InterfaceGrafica.spaceInLine();
		System.out.print("CPF");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Diretor x : listaDiretores) {
			System.out.print(x.getNome());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getCpf());
			
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	
	public void verDisciplinas() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Disciplina> listaDisciplinas = Disciplina.getLista();
		
		System.out.println("+Todas das Disciplinas:");
		InterfaceGrafica.lineBreaker();
		for(Disciplina x : listaDisciplinas) {
			System.out.println(x.getNomeDisc());
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	/* END // Operações do Diretor*/
	
	public void getNP1(Aluno aluno) {
		
		InterfaceGrafica.separator();
		System.out.print("Disciplina");
		InterfaceGrafica.spaceInLine();
		System.out.print("NP1");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.lineBreaker();
		
		if(aluno.getSerie() > 5) {
			
			for(Disciplina x : Disciplina.getLista()) {
				System.out.print(x.getNomeDisc());
				InterfaceGrafica.spaceInLine();
			
				if(aluno.getNP1(x.getNomeDisc()) == 0) {
					System.out.print("SemNota");
				}else {
					System.out.print(aluno.getNP1(x.getNomeDisc()));
				}
			
				InterfaceGrafica.lineBreaker();
			}
		
		}else {
			System.out.print("portugues");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP1("portugues") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP1("portugues"));
			}
			InterfaceGrafica.lineBreaker();
			
			System.out.print("matematica");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP1("matematica") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP1("matematica"));
			}
			InterfaceGrafica.lineBreaker();
			
			System.out.print("educacao fisica");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP1("educacao fisica") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP1("educacao fisica"));
			}
			InterfaceGrafica.lineBreaker();
			
			System.out.print("arte");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP1("arte") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP1("arte"));
			}
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
			
	}
	
	public void getNP2(Aluno aluno) {
		
		InterfaceGrafica.separator();
		System.out.print("Disciplina");
		InterfaceGrafica.spaceInLine();
		System.out.print("NP2");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.lineBreaker();
		
		if(aluno.getSerie() > 5) {
			
			for(Disciplina x : Disciplina.getLista()) {
				System.out.print(x.getNomeDisc());
				InterfaceGrafica.spaceInLine();
			
				if(aluno.getNP2(x.getNomeDisc()) == 0) {
					System.out.print("SemNota");
				}else {
					System.out.print(aluno.getNP2(x.getNomeDisc()));
				}
			
				InterfaceGrafica.lineBreaker();
			}
		
		}else {
			System.out.print("portugues");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP2("portugues") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP2("portugues"));
			}
			InterfaceGrafica.lineBreaker();
			
			System.out.print("matematica");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP2("matematica") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP2("matematica"));
			}
			InterfaceGrafica.lineBreaker();
			
			System.out.print("educacao fisica");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP2("educacao fisica") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP2("educacao fisica"));
			}
			InterfaceGrafica.lineBreaker();
			
			System.out.print("arte");
			InterfaceGrafica.spaceInLine();
			if(aluno.getNP2("arte") == 0) {
				System.out.print("SemNota");
			}else {
				System.out.print(aluno.getNP2("arte"));
			}
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
			
	}
	
	public void setNP1(Professor professor) throws SQLException {
		
		String matricula;
		Float np1;
		
		this.verAlunosDoProfessor(professor);
		
		System.out.println("Digite a matrícula do aluno para alterar/adicionar NP1:");
		matricula = leitorStr.nextLine();
		
		Aluno instanciaAluno = null;
		
		String mat;
		
		for(Aluno x : professor.getListaAluno()) {
			mat = "" + x.getMat();
			
			if(matricula.equals(mat)) {
				instanciaAluno = x;
			}
			
		}
		
		System.out.println("Digite a nota da NP1:");
		np1 = leitorFloat.nextFloat();
		
		instanciaAluno.setNP1(np1, professor);
		
		sql = "SELECT Disciplina.cd_disc FROM Disciplina INNER JOIN Professor ON Disciplina.nm_disc = " + professor.getDisc()+"'";
		
		int cd_disc = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_disc = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		
		sql = "UPDATE Notas SET NP1 = " + np1 + "WHERE cd_aluno = " + instanciaAluno.getMat() + " AND cd_disc = " + cd_disc;
		
		Connection con = Conexao.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		
	}
	
	public void setNP2(Professor professor) throws SQLException {
		
		String matricula;
		Float np2;
		
		this.verAlunosDoProfessor(professor);
		
		System.out.println("Digite a matrícula do aluno para alterar/adicionar NP2");
		matricula = leitorStr.nextLine();
		
		Aluno instanciaAluno = null;
		
		String mat;
		
		for(Aluno x : Aluno.getLista()) {
			mat = "" + x.getMat();
			
			if(matricula.equals(mat)) {
				instanciaAluno = x;
			}
			
		}
		
		System.out.println("Digite a nota da NP2:");
		np2 = leitorFloat.nextFloat();
		
		instanciaAluno.setNP2(np2, professor);
		
		sql = "SELECT Disciplina.cd_disc FROM Disciplina INNER JOIN Professor ON Disciplina.nm_disc = '" + professor.getDisc() +"'";
		
		int cd_disc = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_disc = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
		
		sql = "UPDATE Notas SET NP2 = " + np2 + "WHERE cd_aluno = " + instanciaAluno.getMat() + " AND cd_disc = " + cd_disc;
		
		Connection con = Conexao.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		
	}
	
	public void getAllNP1(Professor professor) {
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Aluno> listaAlunosDoProfessor = professor.getListaAluno();
		
		System.out.println("+Nomes dos Alunos:");
		
		System.out.print("Nome");
		InterfaceGrafica.spaceInLine();
		System.out.print("Matricula");
		InterfaceGrafica.spaceInLine();
		System.out.print("NP1");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Aluno x : listaAlunosDoProfessor) {
			System.out.print(x.getNome());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getMat());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getNP1(professor.getDisc()));
			
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	
	public void getAllNP2(Professor professor) {
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Aluno> listaAlunosDoProfessor = professor.getListaAluno();
		
		System.out.println("+Nomes dos Alunos:");
		
		System.out.print("Nome");
		InterfaceGrafica.spaceInLine();
		System.out.print("Matricula");
		InterfaceGrafica.spaceInLine();
		System.out.print("NP2");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Aluno x : listaAlunosDoProfessor) {
			System.out.print(x.getNome());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getMat());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getNP2(professor.getDisc()));
			
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	
	/* Permitido por: Diretor, Professor */
	public void verAlunos() {
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Aluno> listaAlunos = Aluno.getLista();
		
		System.out.println("+Nomes dos Alunos:");
		
		System.out.print("Nome");
		InterfaceGrafica.spaceInLine();
		System.out.print("Matricula");
		InterfaceGrafica.spaceInLine();
		System.out.print("Sala");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Aluno x : listaAlunos) {
			System.out.print(x.getNome());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getMat());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getSala());
			
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	
	public void verAlunosDoProfessor(Professor professor) {	
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Aluno> listaAlunosDoProfessor = professor.getListaAluno();
		
		System.out.println("+Nomes dos Alunos:");
		
		System.out.print("Nome");
		InterfaceGrafica.spaceInLine();
		System.out.print("Matricula");
		InterfaceGrafica.spaceInLine();
		System.out.println("CPF");
		InterfaceGrafica.spaceInLine();
		System.out.print("Sala");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Aluno x : listaAlunosDoProfessor) {
			System.out.print(x.getNome());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getMat());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getCpf());
			InterfaceGrafica.spaceInLine();
			System.out.print(x.getSala());
			
			InterfaceGrafica.lineBreaker();
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}

	/* Mostra as salas disponíveis por série*/
	public void mostrarSalas(int serie){
		System.out.println("Salas disponíveis pra série em questão: ");
		InterfaceGrafica.lineBreaker();
		for(Sala x : Sala.getLista()) {
			if("" + x.getSala().charAt(0) == Integer.toString(serie)) {
				System.out.println(x.getSala());
			}
		}
	}
	
	/* Relatórios */
	
	public void alunosComMaisDeTantosAnos() throws IOException {
		sql = "SELECT Pessoa.nome FROM Pessoa INNER JOIN Aluno ON Aluno.pessoa_cd_pessoa = Pessoa.cd_pessoa \r\n" + 
				"AND DATE_PART('year', NOW()) - DATE_PART('year', dt_nasc) > 15;";
        
        String nomeAlunoMaior;

        FileWriter arch = new FileWriter("Relatorio_1.txt");
        PrintWriter WriteArch = new PrintWriter(arch);
        
        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
        	System.out.println("O resultado é:");
        	InterfaceGrafica.separator();
            while(rs.next()) {
            	nomeAlunoMaior = rs.getString(1);
            	System.out.println("Nome: " + nomeAlunoMaior);
            	WriteArch.write("Nome: " + nomeAlunoMaior + "\n");
            }
            InterfaceGrafica.separator();
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        arch.close();
        
	}
	
	public void nomeAlunosComNota() throws IOException {
		sql = "SELECT Aluno.cd_aluno, Disciplina.nm_disc, Notas.np1, Notas.np2 FROM Aluno\r\n" + 
				"	LEFT OUTER JOIN Notas ON Aluno.cd_aluno = Notas.cd_aluno\r\n" + 
				"		INNER JOIN Disciplina ON Notas.cd_disc = Disciplina.cd_disc\r\n" + 
				"				UNION\r\n" + 
				"SELECT Aluno.cd_aluno, Disciplina.nm_disc, Notas.np1, Notas.np2 FROM Aluno \r\n" + 
				"	RIGHT OUTER JOIN Notas ON Aluno.cd_aluno = Notas.cd_aluno\r\n" + 
				"		INNER JOIN Disciplina ON Notas.cd_disc = Disciplina.cd_disc;";
        
        String nomeAluno, disciplina;
        
        FileWriter arch = new FileWriter("Relatorio_2.txt");
        PrintWriter WriteArch = new PrintWriter(arch);
        
        float np1, np2;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
        	System.out.println("O resultado é:");
        	InterfaceGrafica.separator();
            while(rs.next()) {
            	nomeAluno = rs.getString(1);
            	disciplina = rs.getString(2);
            	np1 = rs.getFloat(3);
            	np2 = rs.getFloat(4);
            	System.out.println("Nome: " + nomeAluno + "| Disciplina: " + disciplina + "| NP1: " + np1 + "| NP2: " + np2);
            	WriteArch.write("Nome: " + nomeAluno + "| Disciplina: " + disciplina + "| NP1: " + np1 + "| NP2: " + np2 + "\n");
            }
            InterfaceGrafica.separator();
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        arch.close();
        
	}
	
	public void alunosSemNotas() throws IOException {
		sql = "SELECT Pessoa.nome FROM Pessoa\r\n" + 
				"INNER JOIN Aluno ON Pessoa.cd_pessoa = Aluno.pessoa_cd_pessoa\r\n" + 
				"LEFT JOIN Notas ON NP1 = NULL OR NP2 = NULL;";
        
        String nomeAluno;
        
        FileWriter arch = new FileWriter("Relatorio_3.txt");
        PrintWriter WriteArch = new PrintWriter(arch);

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
        	System.out.println("O resultado é:");
        	InterfaceGrafica.separator();
            while(rs.next()) {
            	nomeAluno = rs.getString(1);
            	System.out.println("Nome: " + nomeAluno);
            	WriteArch.write("Nome: " + nomeAluno + "\n");
            }
            InterfaceGrafica.separator();
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        arch.close();
        
	}
	
	public void alunosAcimaMedia() throws IOException {
		sql = "SELECT pessoa.nome FROM Aluno \r\n" + 
				"INNER JOIN Pessoa ON Pessoa.cd_pessoa = Aluno.pessoa_cd_pessoa\r\n" + 
				"	WHERE cd_aluno = (SELECT cd_aluno FROM Notas WHERE (NP1 + NP2)/2 >= 7);";
        
        String nomeAluno;

        FileWriter arch = new FileWriter("Relatorio_4.txt");
        PrintWriter WriteArch = new PrintWriter(arch);
        
        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
        	System.out.println("O resultado é:");
        	InterfaceGrafica.separator();
            while(rs.next()) {
            	nomeAluno = rs.getString(1);
            	
            	System.out.println("Nome: " + nomeAluno);
            	WriteArch.write("Nome: " + nomeAluno + "\n");
            }
            InterfaceGrafica.separator();
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        arch.close();
        
	}
	
	/* END // Relatórios */
	
	/* Operação Geral */
	public void serializeAll() {
		Professor.serialization();
		Diretor.serialization();
		Aluno.serialization();
		Sala.serialization();
		Disciplina.serialization();
	}

	
	public void closeScanners() {
		leitorInt.close();
		leitorStr.close();
		leitorVoltar.close();
	}

	
	public void pegarDisciplinas() throws SQLException {
		
		Disciplina.cadastrarDisciplina("portugues");
		Disciplina.cadastrarDisciplina("matematica");
		Disciplina.cadastrarDisciplina("educacao fisica");
		Disciplina.cadastrarDisciplina("ciencia");
		Disciplina.cadastrarDisciplina("ingles");
		Disciplina.cadastrarDisciplina("filosofia");
		Disciplina.cadastrarDisciplina("geografia");
		Disciplina.cadastrarDisciplina("historia");
		Disciplina.cadastrarDisciplina("arte");

		sql = "INSERT INTO Disciplina(nm_disc) VALUES('portugues'),('matematica'), ('educacao fisica'), ('ciencia'), ('ingles'), ('filosofia'), ('geografia'), ('historia'), ('arte')";

		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();
		
	}
	
	
	public boolean usuarioVoltar() {
		
		
		String continuar;
		
		InterfaceGrafica.separator();
		InterfaceGrafica.backLogin();
		InterfaceGrafica.separator();
		
		continuar = leitorVoltar.nextLine();
			
		switch(continuar) {
		case "1":
			return true;
			
			
		case "0":
			return false;
				
		default:
			
			return false;
				
		}
		
		
	}
	

}
