package principal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import bdConnection.*;

public abstract class Pessoa {
	private String nome, cpf, rg, dtNasc;
	
	Pessoa(String nome, String cpf, String rg, String dtNasc){
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dtNasc = dtNasc;
	}
	
	String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getDtNasc() {
		return dtNasc;
	}

	public int getPessoaCount() throws ClassNotFoundException {
        String SQL = "SELECT count(*) FROM Pessoa";
        int count = 0;

        try (Connection conn = Conexao.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
	
	/*Este método está incorreto*/
	//Seu intuito é pegar todos os nomes das pessoas que tem no banco
	public int getAllPessoa() throws ClassNotFoundException {
	String SQL = "SELECT nome FROM Pessoa";

	int count = 0;
	
	
	try (Connection conn = Conexao.getConnection();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(SQL)) {
	      rs.next();
	      count = rs.getInt(1);
	      
	} catch (SQLException ex) {
	      System.out.println(ex.getMessage());
	}
	
		return count;
	}
	
	public String getPessoaDtNasc() throws ClassNotFoundException {
		
		//Faz o Select da data de nascimento e assegura que a pesquisa será feito corretamente
        String SQL = "SELECT dt_nasc FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        String queryPessoa = null;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            queryPessoa = rs.getString(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return queryPessoa;
    }
}
