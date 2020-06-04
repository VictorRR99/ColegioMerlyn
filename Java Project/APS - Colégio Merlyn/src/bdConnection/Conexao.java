package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String url = "jdbc:postgresql://localhost:5432/APSColegioMerlyn";
	private static final String user = "Colegio";
	private static final String password = "4444";
	
	//JDBC utiliza do serviço aberto do postgres, não é necessário abrir o banco para testar
	
	public static Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Classe não encontrada!");
			e.printStackTrace();
		}
		
		Connection conecta;
		
		try {
			conecta = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			conecta = null;
			e.printStackTrace();
		}
		
		//Se houver algum problema com a conexão retorna uma mensagem
		if(conecta != null) {
			return conecta;
		}else {
			System.out.println("Conexão não encontrada!");
			return null;
		}
	}
}