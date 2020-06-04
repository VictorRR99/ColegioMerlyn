package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String url = "jdbc:postgresql://localhost:5432/APSColegioMerlyn";
	private static final String user = "Colegio";
	private static final String password = "4444";
	
	//JDBC utiliza do servi�o aberto do postgres, n�o � necess�rio abrir o banco para testar
	
	public static Connection getConnection(){
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Classe n�o encontrada!");
			e.printStackTrace();
		}
		
		Connection conecta;
		
		try {
			conecta = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			conecta = null;
			e.printStackTrace();
		}
		
		//Se houver algum problema com a conex�o retorna uma mensagem
		if(conecta != null) {
			return conecta;
		}else {
			System.out.println("Conex�o n�o encontrada!");
			return null;
		}
	}
}