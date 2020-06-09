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
	
	/* Variáveis Cadastro */
	private String nome, nomeDisc, numSala, cpf, rg, dtNasc, turno, sala, senha, sql;
	private Disciplina disc;
	private int serie;
	
	/* Permitido por: Diretor */
	public void cadastrarAluno() throws SQLException{
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
		
		System.out.println("Digite turno:");
		turno = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		InterfaceGrafica.mostrarSalas(serie);
		System.out.println("Digite sala:");
		sala = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		//Checagem se não há inconsistência nas séries e salas
		boolean check = Sala.checarSalaCerta(serie, sala);
		while(!check) {
			System.out.println("Digite uma sala válida:");
			sala = leitorStr.nextLine();
			InterfaceGrafica.lineBreaker();
		}
		
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

//		insert na tabela Aluno
		sql = "INSERT INTO Aluno(cd_aluno, pessoa_cd_pessoa, cd_sala, serie, turno) VALUES("+ matProvi + ", '"+ cd_pessoa +"', '" + 
				Integer.parseInt(sala) + "', '" + serie + "', '" + turno +"')";
				
		conexao = Conexao.getConnection();
				
		ps = conexao.prepareStatement(sql);
				
		ps.execute();
		
		System.out.println("Cadastro concluído com sucesso!");
	}
	
	
	/* Permitido por: Diretor */
	public void cadastrarProfessor() throws SQLException {
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
		
//		/* Lidar com digitação incorreta de disciplina */
//		// Talvez fazer um menu enumerado igual ao main
//		System.out.println("Digite a disciplina a ser lecionada:");
//		InterfaceGrafica.mostrarDisciplinas();
//		String check;
//		check = leitorStr.nextLine();
//			
//		for(Disciplina x : Disciplina.getLista()) {
//			if(x.getNomeDisc().equals(check)) {
//				disc = x;
//			}
//		}
		
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
	public void cadastrarDisciplina() throws SQLException {
		InterfaceGrafica.cadastrarDisciplina();
		
		System.out.println("Digite nome da Disciplina:");
		nomeDisc = leitorStr.nextLine();
		
		Diretor.cadastrarDisciplina(nomeDisc);
		
		InterfaceGrafica.lineBreaker();
		
		sql = "INSERT INTO Disciplina(nm_disc) VALUES('" + nomeDisc + "')";
		
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		ps.close();
	}
	
	/* Permitido por: Diretor */
	public void cadastrarSala() throws SQLException {
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
		
		System.out.println("Aluno deletado com sucesso!");
		
		InterfaceGrafica.lineBreaker();
	}	
	
	public void deletarProfessor() throws SQLException {
		cpf = leitorStr.nextLine();
		
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
	
	public void deletarDiretor() throws SQLException {
		cpf = leitorStr.nextLine();
		
		Diretor.deletarDiretor(cpf);
		
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
	}
	
	public void updateAluno() {
		System.out.println("Digite a matrícula do aluno que deseja alterar:");
		
		
	}
	
	/* Permitido por: Diretor, Professor */
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
	
	/* Permitido por: Diretor */
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
	
	/* Permitido por: Diretor */
	public void verSalas() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Sala> listaSalas = Sala.getLista();
		
		System.out.println("+Nomes das Salas:");
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
		InterfaceGrafica.lineBreaker();
		for(Diretor x : listaDiretores) {
			System.out.println(x.getNome());
		}
		
		InterfaceGrafica.separator();
		InterfaceGrafica.lineBreaker();
		
	}
	
	/* Permitido por: Diretor */
	public void verDisciplinas() {
		InterfaceGrafica.lineBreaker();
		InterfaceGrafica.separator();
		
		List<Disciplina> listaDisciplinas = Disciplina.getLista();
		
		System.out.println("+Nomes das Disciplinas:");
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
	
	/* Operação Geral */
	public void serializeAll() {
		Professor.serialization();
		Diretor.serialization();
		Aluno.serialization();
		Sala.serialization();
		Disciplina.serialization();
	}
	
	public void keepUpToDate() throws SQLException {
		sql = "SELECT cd_aluno FROM Pessoa";
		
		Connection con = Conexao.getConnection();
		
		PreparedStatement ps = con.prepareStatement(sql);
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			for(Aluno x : Aluno.getLista()) {
				
			}
		}
	}
	
	public void closeScanners() {
		leitorInt.close();
		leitorStr.close();
	}
	
}
