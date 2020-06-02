package bdConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static final String url = "jdbc:postgresql://localhost:5432/APSColegioMerlyn";
	private static final String user = "postgres";
	private static final String password = "9922";
	
	//Este metodo funcionou mesmo sem que o servidor do Postgres estivesse aberto
	//Provavelmente ele est� ocorrendo alguma das exce��es que est�o no throws
	//Importante entender o que est� acontecendo
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("org.postgresql.Driver");
		Connection conecta = DriverManager.getConnection(url, user, password);
		if(conecta != null) {
			System.out.println("Conex�o com Sucesso");
			return conecta;
		}else {
			return null;
		}
	}
}