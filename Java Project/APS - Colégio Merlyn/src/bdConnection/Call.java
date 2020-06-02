package bdConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class Call {
	
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		try {
			@SuppressWarnings("unused")
			Connection conexao = Conexao.getConnection();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	
		Conexao utilitario = new Conexao();
		
		System.out.println(utilitario.getPessoaCount());
		
		System.out.println(utilitario.getPessoaDtNasc());
		
		//Teste pra ver se atualiza

		//Tentando

		//Teste VS CODe
		
		//Teste no Java

		/*VS CODE DENOVO*/
	}
	
}
