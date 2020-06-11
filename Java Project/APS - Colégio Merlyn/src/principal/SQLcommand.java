package principal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdConnection.Conexao;

public class SQLcommand {
	
	@SuppressWarnings("resource")
	public static void insertAluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) throws SQLException {

		String sql;
		
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
		
		if(serie < 6) {
			sql = "INSERT INTO Notas(cd_aluno, cd_disc) VALUES("+ matProvi +", 1), ("+ matProvi +", 2), ("+ matProvi +", 3), "
					+ "("+ matProvi +", 9)";
			
			conexao = Conexao.getConnection();
			
			ps = conexao.prepareStatement(sql);
			
			ps.execute();
			ps.cancel();
			
		}else if(serie > 5) {
			
			sql = "INSERT INTO Notas(cd_aluno, cd_disc) VALUES("+ matProvi +", 1), ("+ matProvi +", 2), ("+ matProvi +", 3), "
					+ "("+ matProvi +", 4), ("+ matProvi +", 5), ("+ matProvi +",6), ("+ matProvi +",7), "
							+ "("+ matProvi +",8), ("+ matProvi +",9)";
			
			conexao = Conexao.getConnection();
			
			ps = conexao.prepareStatement(sql);
			
			ps.execute();
			ps.cancel();
			
		}
	}

	
	public static void insertProfessor(String nome, String cpf, String rg, String dtNasc, String senha, Disciplina disc) throws SQLException {
		
		String sql;
		
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
		
	}


	
	@SuppressWarnings("resource")
	public static void insertSala(String numSala) throws SQLException {
		String sql;
		
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


	
	public static void insertDiretor(String nome, String cpf, String rg, String dtNasc, String senha) throws SQLException {
		String sql;
		

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



	public static void deletarAluno(int matDeleta) throws SQLException {
		String sql;
		
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
		
	}


	public static void deletarProfessor(String cpf) throws SQLException {
		String sql;
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


	public static void deletarDiretor(String cpf) throws SQLException {
		String sql;
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
	}
	
	
}
