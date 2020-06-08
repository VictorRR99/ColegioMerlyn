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
	private String nome, nomeDisc, cpf, rg, dtNasc, turno, sala, senha, sql;
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
		
		System.out.println("Digite data de nascimento:");
		dtNasc = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite serie:");
		serie = leitorInt.nextInt();
		InterfaceGrafica.lineBreaker();
		
		System.out.println("Digite turno:");
		turno = leitorStr.nextLine();
		InterfaceGrafica.lineBreaker();
		
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
		
		sql = "INSERT INTO Pessoa(nome, dt_nasc, cpf, rg, senha) VALUES('"+ nome + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		
		Diretor.cadastrarAluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
		
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
		String check;
		check = leitorStr.nextLine();
		for(int i = 0; i < Disciplina.getLista().size(); i++) {
			if(Disciplina.getLista().get(i).getNomeDisc() == check) {
				disc = Disciplina.getLista().get(i);
			}
		}
		InterfaceGrafica.lineBreaker();
		
		Diretor.cadastrarProfessor(nome, cpf, rg, dtNasc, senha, disc);
		
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
		
		/* Lidar com digitação incorreta de disciplina */
		// Talvez fazer um menu enumerado igual ao main
		System.out.println("Digite a disciplina a ser lecionada:");
		InterfaceGrafica.mostrarDisciplinas();
		String check;
		check = leitorStr.nextLine();
			
		for(Disciplina x : Disciplina.getLista()) {
			if(x.getNomeDisc().equals(check)) {
				disc = x;
				System.out.println("Opa to aqui");
			}
		}
		
		Diretor.cadastrarDiretor(nome, cpf, rg, dtNasc, senha, disc);
		
		InterfaceGrafica.lineBreaker();
		
//		Inserindo na tabela Pessoa
		sql = "INSERT INTO Pessoa(nome, dt_nasc, cpf, rg, senha) VALUES('"+ nome + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		
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
        sql = "SELECT cd_disc FROM Pessoa WHERE nm_disc = '" + disc.getNomeDisc().toLowerCase() + "'";
        
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
        
        sql = "INSERT INTO Professor(cd_pessoa, cd_disc) VALUES('"+ cd_pessoa + "', '"+ cd_disc + "')";
				
		conexao = Conexao.getConnection();
				
		ps = conexao.prepareStatement(sql);
				
		ps.execute();
        
	}

	public void cadastrarDisciplina() throws SQLException {
		InterfaceGrafica.cadastrarDisciplina();
		
		System.out.println("Digite nome da Disciplina:");
		nomeDisc = leitorStr.nextLine();
		
		Diretor.cadastrarDisciplina(nomeDisc);
		
		InterfaceGrafica.lineBreaker();
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
	
	/* Permitido por: Diretor*/
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
	
	/* Operação Geral */
	public void serializeAll() {
		Professor.serialization();
		Diretor.serialization();
		Aluno.serialization();
		Sala.serialization();
		Disciplina.desserialization();
	}
	
	public void closeScanners() {
		leitorInt.close();
		leitorStr.close();
	}
	
}
