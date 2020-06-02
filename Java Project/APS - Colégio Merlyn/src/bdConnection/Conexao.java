package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Conexao {
	
	private static final String url = "jdbc:postgresql://localhost:5432/APSColegioMerlyn";
	private static final String user = "postgres";
	private static final String password = "9922";
	
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