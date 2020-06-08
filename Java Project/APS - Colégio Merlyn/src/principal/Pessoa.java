package principal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import bdConnection.*;

@SuppressWarnings("serial")
public abstract class Pessoa implements Serializable{
	
	private String nome, cpf, rg, dtNasc;
	private String senha;
	
	Pessoa(String nome, String cpf, String rg, String dtNasc, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dtNasc = dtNasc;
		this.senha = senha;
	}
	
	/* M�todos B�sicos*/
	
	public String getNome() {
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
	
	public String getSenha() {
		return senha;
	}
	
	/* M�todos de Relat�rio [Pesquisa] JAVA */
	
	//Talvez fazer m�todos que procuram igual a pesquisa do SQL
	//Para tal realmente � poss�vel a necessidade de umaa lista est�tica
	//Por�m haver� complica��es a respeito de como ser� chamado os m�todos
	
	// INSERIR METODOS DE RELATORIO

	/* M�todos de Relat�rio [Pesquisa] PostgreSQL */
	
	//Quantidade de Pessoas que est�o cadastradas na Escola
	public int getPessoaCount() throws ClassNotFoundException {
        String SQL = "SELECT count(*) FROM Pessoa";
        int count = 0;

        try (Connection conn = Conexao.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }
	
	//Todas os nomes das Pessoas que est�o cadastradas na Escola
	public List<String> getAllPessoa() throws ClassNotFoundException {
		String SQL = "SELECT nome FROM Pessoa";
		
		List<String> lista = new ArrayList<>();
		
		try (Connection conn = Conexao.getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(SQL)) {
			while(rs.next()) {
					lista.add(rs.getString("nome"));
			}
		      rs.next();
		      rs.close();
	          stmt.close();
	          conn.close();
		} catch (SQLException ex) {
		      System.out.println(ex.getMessage());
		}
	
			return lista;
	}
	
	//Dado o cpf que estiver na String cpf ser� retornado a data de nascimento desta pessoa
	//Atrav�s da pesquisa no Banco de Dados
	public String getPessoaDtNasc() throws ClassNotFoundException {
		
		//Faz o Select da data de nascimento e assegura que a pesquisa ser� feito corretamente
        String SQL = "SELECT TO_CHAR(dt_nasc :: DATE, 'dd/mm/yyyy') FROM Pessoa WHERE LOWER(cpf) = '" + cpf.toLowerCase() + "'";
        
        String queryPessoa = null;

        try (Connection conn = Conexao.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            queryPessoa = rs.getString(1);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return queryPessoa;
    }
}


