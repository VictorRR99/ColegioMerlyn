package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String url = "jdbc:postgresql://localhost:5432/APSColegioMerlyn";
	private static final String user = "Colegio";
	private static final String password = "4444";
	
//	JDBC utiliza do serviço aberto do postgres, não é necessário abrir o banco para testar
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection(url, user, password);
		if(conecta != null) {
			System.out.println("Conexão com Sucesso");
			return conecta;
		}else {
			return null;
		}
	}
}