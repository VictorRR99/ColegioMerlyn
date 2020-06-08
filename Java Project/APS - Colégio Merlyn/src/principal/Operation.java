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
	private String nome, cpf, rg, dtNasc, turno, sala, senha, sql;
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
		
		//Checar se existe alguma disciplina
		if(!Disciplina.getLista().isEmpty()) {
		System.out.println("Digite a disciplina a ser lecionada:");
		String check;
		check = leitorStr.nextLine();
		
			for(int i = 0; i < Disciplina.getLista().size(); i++) {
				if(Disciplina.getLista().get(i).getNomeDisc() == check) {
					disc = Disciplina.getLista().get(i);
				}
			}
		}else {
			//Pode dar problema 
			disc = new Disciplina("SuperUsuário");
		}
		
		InterfaceGrafica.lineBreaker();
		
//		Inserindo na tabela Pessoa
		sql = "INSERT INTO Pessoa(nome, dt_nasc, cpf, rg, senha) VALUES('"+ nome + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		Connection conexao = Conexao.getConnection();
				
		PreparedStatement ps = conexao.prepareStatement(sql);
				
		ps.execute();
		
//		Inserindo na tabela Professor
		sql = "SELECT cd_pessoa FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        int queryPessoa = 0;
        
        sql = "INSERT INTO Professor(cd_pessoa, cd_disc) VALUES('"+ queryPessoa + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		conexao = Conexao.getConnection();
				
		ps = conexao.prepareStatement(sql);
				
		ps.execute();
		
		sql = "SELECT cd_pessoa FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        int queryPessoa = 0;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            rs.next();
            queryPessoa = rs.getInt(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        sql = "INSERT INTO Professor(cd_pessoa, cd_disc) VALUES('"+ queryPessoa + "', '"+ dtNasc +"', '" + 
				cpf + "', '" + rg + "', '" + senha +"')";
				
		conexao = Conexao.getConnection();
				
		ps = conexao.prepareStatement(sql);
				
		ps.execute();
        
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
	}
	
	public void closeScanners() {
		leitorInt.close();
		leitorStr.close();
	}
	
}
