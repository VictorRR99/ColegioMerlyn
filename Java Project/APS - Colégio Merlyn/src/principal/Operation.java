package principal;

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
	
	/* Permitido por: Diretor */
	public boolean cadastrarAluno() throws SQLException{
		InterfaceGrafica.cadastrarAluno();
		
		String nome, cpf, rg, dtNasc = null, sala, turno, senha;
		int serie;
		
		
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
		
		System.out.println("Digite serie:");
		serie = leitorInt.nextInt();
		InterfaceGrafica.lineBreaker();
		
		InterfaceGrafica.mostrarSalas(serie);
		System.out.println("Digite sala:");
		sala = leitorStr.nextLine();
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
		
		Diretor.cadastrarAluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
		
//		Inserindo na tabela Pessoa
		sql = "INSERT INTO Pessoa(nome, dt_nasc, cpf, rg, senha) VALUES('"+ nome + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();
		
		int matProvi = 0;
		
		for(Aluno x : Aluno.getLista()) {
			if(x.getCpf().equals(cpf)) {
				matProvi = x.getMat();
			}
		}
		
		sql = "SELECT cd_pessoa FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        int cd_pessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_pessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        //insert na tabela Aluno
		sql = "INSERT INTO Aluno(cd_aluno, pessoa_cd_pessoa, cd_sala, serie, turno) VALUES("+ matProvi + ", '"+ cd_pessoa +"', '" + 
				Integer.parseInt(sala) + "', '" + serie + "', '" + turno +"')";
				
		conexao = Conexao.getConnection();
				
		ps = conexao.prepareStatement(sql);
				
		ps.execute();
		
		System.out.println("Cadastro concluído com sucesso!");
		return true;
	}
	
	/* Permitido por: Diretor */
	public void cadastrarProfessor() throws SQLException {
		
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
		
		Diretor.cadastrarProfessor(nome, cpf, rg, dtNasc, senha, disc);
		
		System.out.println("Digite a sala para qual o professor irá lecionar:");
		this.verSalas();
		String sala = leitorStr.nextLine();

		
		for(Professor x : Professor.getLista()) {
			if(x.getCpf().equals(cpf)) {
				x.setSala(Sala.getSalaSala(sala));
				x.receberAlunos();
			}
		}
		
		
//		Inserindo na tabela Pessoa
		sql = "INSERT INTO Pessoa(nome, dt_nasc, cpf, rg, senha) VALUES('"+ nome + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();
		
//		Select na tabela pessoa para pegar o cd_pessoa
		sql = "SELECT cd_pessoa FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        int cd_pessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_pessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
//        Select na tabela Disciplina para pegar cd_disc
        sql = "SELECT cd_disc FROM Disciplina WHERE nm_disc = '" + disc.getNomeDisc() + "'";
        
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
        
//		Inserindo na tabela Professor
        
        sql = "INSERT INTO Professor(cd_pessoa, cd_disc) VALUES("+ cd_pessoa + ", "+ cd_disc + ")";
        
		conexao = Conexao.getConnection();
				
		ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();

		System.out.println("Cadastro concluído com sucesso!");
	}
	
	/* Permitido por: Diretor */
	public void cadastrarDiretor() throws SQLException {
		
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
		
		Diretor.cadastrarDiretor(nome, cpf, rg, dtNasc, senha);
		
		InterfaceGrafica.lineBreaker();
		
//		Inserindo na tabela Pessoa
		sql = "INSERT INTO Pessoa(nome, dt_nasc, cpf, rg, senha) VALUES('"+ nome + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();
		
//		Select na tabela pessoa para pegar o cd_pessoa
		sql = "SELECT cd_pessoa FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        int cd_pessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_pessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        

		sql = "INSERT INTO Diretor(cd_pessoa) VALUES(" + cd_pessoa + ")";
		
		conexao = Conexao.getConnection();
		
		ps = conexao.prepareStatement(sql);
		
		ps.execute();
		ps.close();
        
	}

	/* Permitido por: Diretor */
	@SuppressWarnings("resource")
	public void cadastrarSala() throws SQLException {
		
		String numSala;
		
		InterfaceGrafica.cadastrarSala();
		
		System.out.println("Digite numero da Sala:");
		numSala = leitorStr.nextLine();
		
		Diretor.cadastrarSala(numSala);
		
		InterfaceGrafica.lineBreaker();
		
		sql = "INSERT INTO Sala(cd_Sala) VALUES('" + Integer.parseInt(numSala) + "')";
		
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();
		
		if(Integer.parseInt("" + numSala.charAt(0)) < 6) {
			
			sql = "INSERT INTO sala_disc(cd_sala, cd_disc) VALUES("+ Integer.parseInt(numSala) +", 1),"
					+ " ("+ Integer.parseInt(numSala) +", 2), ("+ Integer.parseInt(numSala) +", 3), "
							+ "("+ Integer.parseInt(numSala) +", 9)";
			
			conexao = Conexao.getConnection();
			
			ps = conexao.prepareStatement(sql);
			
			ps.execute();
			ps.close();
			
		}else if(Integer.parseInt("" + numSala.charAt(0)) > 5) {
			
			sql = "INSERT INTO sala_disc(cd_sala, cd_disc) VALUES("+ Integer.parseInt(numSala) +", 1),"
					+ " ("+ Integer.parseInt(numSala) +", 2), ("+ Integer.parseInt(numSala) +", 3), "
					+ "("+ Integer.parseInt(numSala) +", 4), ("+ Integer.parseInt(numSala) +", 5), "
							+ "("+ Integer.parseInt(numSala) +", 6), ("+ Integer.parseInt(numSala) +", 7), "
									+ "("+ Integer.parseInt(numSala) +", 8), ("+ Integer.parseInt(numSala) +", 9)";
			
			conexao = Conexao.getConnection();
			
			ps = conexao.prepareStatement(sql);
			
			ps.execute();
			ps.close();
			
		}
		
	}
	
	//Deletar Aluno
	public void deletarAluno() throws SQLException {
		int matDeleta = leitorInt.nextInt();
		
		Aluno.deletarAluno(matDeleta);
		
		sql = "DELETE FROM Notas WHERE cd_aluno = " + matDeleta;
		
		Connection con = Conexao.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		
		sql = "SELECT pessoa_cd_pessoa FROM Aluno WHERE cd_aluno =" + matDeleta;
		
		int pessoa_cd_pessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            pessoa_cd_pessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        con = Conexao.getConnection();
        
        ps = con.prepareStatement(sql);
        
        ps.execute();
        ps.close();
		
		sql = "DELETE FROM Aluno WHERE pessoa_cd_pessoa = " + pessoa_cd_pessoa;
		
		con = Conexao.getConnection();
		
		ps = con.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		
		sql = "DELETE FROM Pessoa WHERE cd_pessoa = " + pessoa_cd_pessoa;
		
		con = Conexao.getConnection();
		
		ps = con.prepareStatement(sql);
		
		ps.execute();
		ps.close();
		
		InterfaceGrafica.lineBreaker();
	}	
	
	public void deletarProfessor() throws SQLException {
		
		String cpf;
		
		cpf = leitorStr.nextLine();
		
		Professor.deletarProfessor(cpf);
		
		sql = "SELECT cd_pessoa FROM Pessoa WHERE cpf =" + cpf;
		
		int cd_pessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_pessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        sql = "DELETE FROM Professor WHERE cd_pessoa =" + cd_pessoa;
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.execute();
        ps.close();
        
        sql = "DELETE FROM Pessoa WHERE cd_pessoa =" + cd_pessoa;
        
        con = Conexao.getConnection();
        
        ps = con.prepareStatement(sql);
        
        ps.execute();
        ps.close();
        
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
		
		
		Diretor.deletarDiretor(cpf);
		
		sql = "SELECT Pessoa.cd_pessoa FROM Diretor INNER JOIN Pessoa ON Pessoa.cd_pessoa = Diretor.cd_pessoa AND Pessoa.cpf = '" + cpf + "'";
		
		int cd_pessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            cd_pessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        sql = "DELETE FROM Diretor WHERE cd_pessoa =" + cd_pessoa;
        
        Connection con = Conexao.getConnection();
        
        PreparedStatement ps = con.prepareStatement(sql);
        
        ps.execute();
        ps.close();
        
        sql = "DELETE FROM Pessoa WHERE cd_pessoa =" + cd_pessoa;
        
        con = Conexao.getConnection();
        
        ps = con.prepareStatement(sql);
        
        ps.execute();
        ps.close();
        
        return true;
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
	
	public void getNP1(Aluno aluno) {
		
		System.out.print("Disciplina");
		InterfaceGrafica.spaceInLine();
		System.out.print("NP1");
		
		InterfaceGrafica.lineBreaker();
		
		for(Disciplina x : Disciplina.getLista()) {
			System.out.print(x.getNomeDisc());
			InterfaceGrafica.spaceInLine();
			
			if(aluno.getNP1(x.getNomeDisc()) == 0) {
				System.out.println("S/Nota");
			}else {
				System.out.print(aluno.getNP1(x.getNomeDisc()));
			}
			
			InterfaceGrafica.lineBreaker();
		}
		
	}
	
	public void getNP2(Aluno aluno) {
		
		System.out.print("Disciplina");
		InterfaceGrafica.spaceInLine();
		System.out.print("NP2");
		
		for(Disciplina x : Disciplina.getLista()) {
			System.out.print(x.getNomeDisc());
			InterfaceGrafica.spaceInLine();
			
			if(aluno.getNP2(x.getNomeDisc()) == 0) {
				System.out.println("S/Nota");
			}else {
				System.out.print(aluno.getNP2(x.getNomeDisc()));
			}
			
			InterfaceGrafica.lineBreaker();
			
		}
		
	}
	
	public void getMedia() {
		
	}
	
	public void setNP1(Professor professor) {
		
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
		
	}
	
	public void setNP2(Professor professor) {
		
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
		System.out.print("Sala");
		
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separatorLight();
		
		InterfaceGrafica.lineBreaker();
		for(Aluno x : listaAlunosDoProfessor) {
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
	
	/* Permitido por: Diretor */
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
	
	/* Permitido por: Diretor */
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
	
	/* Permitido por: Diretor */
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
	
	/* Permitido por: Diretor */
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
	
	public void alunosComMaisDeTantosAnos() {
		sql = "SELECT Pessoa.nome FROM Pessoa INNER JOIN Aluno ON Aluno.pessoa_cd_pessoa = Pessoa.cd_pessoa \r\n" + 
				"AND DATE_PART('year', NOW()) - DATE_PART('year', dt_nasc) > 15;";
        
        String nomeAlunoMaior;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
            while(rs.next()) {
            	nomeAlunoMaior = rs.getString(1);
            	System.out.println("Nome: " + nomeAlunoMaior);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public void nomeAlunosComNota() {
		sql = "SELECT Aluno.cd_aluno, Disciplina.nm_disc, Notas.np1, Notas.np2 FROM Aluno\r\n" + 
				"	LEFT OUTER JOIN Notas ON Aluno.cd_aluno = Notas.cd_aluno\r\n" + 
				"		INNER JOIN Disciplina ON Notas.cd_disc = Disciplina.cd_disc\r\n" + 
				"				UNION\r\n" + 
				"SELECT Aluno.cd_aluno, Disciplina.nm_disc, Notas.np1, Notas.np2 FROM Aluno \r\n" + 
				"	RIGHT OUTER JOIN Notas ON Aluno.cd_aluno = Notas.cd_aluno\r\n" + 
				"		INNER JOIN Disciplina ON Notas.cd_disc = Disciplina.cd_disc;";
        
        String nomeAluno, disciplina;
        
        float np1, np2;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
            while(rs.next()) {
            	nomeAluno = rs.getString(1);
            	disciplina = rs.getString(2);
            	np1 = rs.getFloat(3);
            	np2 = rs.getFloat(4);
            	System.out.println("Nome: " + nomeAluno + "| Disciplina: " + disciplina + "| NP1: " + np1 + "| NP2: " + np2);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public void alunosSemNotas() {
		sql = "SELECT Pessoa.nome FROM Pessoa\r\n" + 
				"INNER JOIN Aluno ON Pessoa.cd_pessoa = Aluno.pessoa_cd_pessoa\r\n" + 
				"LEFT JOIN Notas ON NP1 = NULL OR NP2 = NULL;";
        
        String nomeAluno;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
            while(rs.next()) {
            	nomeAluno = rs.getString(1);
            	System.out.println("Nome: " + nomeAluno);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
	}
	
	public void alunosAcimaMedia() {
		sql = "SELECT pessoa.nome FROM Aluno \r\n" + 
				"INNER JOIN Pessoa ON Pessoa.cd_pessoa = Aluno.pessoa_cd_pessoa\r\n" + 
				"	WHERE cd_aluno = (SELECT cd_aluno FROM Notas WHERE (NP1 + NP2)/2 >= 7);";
        
        String nomeAluno;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
        	
            while(rs.next()) {
            	nomeAluno = rs.getString(1);
            	System.out.println("Nome: " + nomeAluno);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
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
