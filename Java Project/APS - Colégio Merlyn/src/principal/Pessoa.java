package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import bdConnection.*;

public abstract class Pessoa implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String nome, cpf, rg, dtNasc;
	
	Pessoa(String nome, String cpf, String rg, String dtNasc){
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dtNasc = dtNasc;
	}
	
	/* Serialization Handler */
	
	public static void saveObjectList(List<Pessoa> lista, String nomeArq) {
	      File arq = new File(nomeArq);
	      try {
	    	  arq.delete();
	    	  arq.createNewFile();
	    
	    	  ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
	        
	    	  objOutput.writeObject(lista);
	    	  objOutput.close();
	    
	      } catch(IOException erro) {
	    	  System.out.printf("Erro: %s", erro.getMessage());
	      }
	}
	
	
	@SuppressWarnings("all")
	public static List<Pessoa> readObjectList(String nomeArq) {
		
		ArrayList<Pessoa> lista = new ArrayList<Pessoa>();
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (ArrayList<Pessoa>)objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
	}
	
	//----
	
	
	//----
	
	/* Métodos de Relatório [Pesquisa] JAVA */
	
	//Talvez fazer métodos que procuram igual a pesquisa do SQL
	//Para tal realmente é possível a necessidade de umaa lista estática
	//Porém haverá complicações a respeito de como será chamado os métodos
	
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

	/* Métodos de Relatório [Pesquisa] PostgreSQL */
	
	//Quantidade de Pessoas que estão cadastradas na Escola
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
	
	//Todas os nomes das Pessoas que estão cadastradas na Escola
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
	
	//Dado o cpf que estiver na String cpf será retornado a data de nascimento desta pessoa
	//Através da pesquisa no Banco de Dados
	public String getPessoaDtNasc() throws ClassNotFoundException {
		
		//Faz o Select da data de nascimento e assegura que a pesquisa será feito corretamente
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