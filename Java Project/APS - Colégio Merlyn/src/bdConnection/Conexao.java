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
	
	public int getPessoaCount() throws ClassNotFoundException {
        String SQL = "SELECT count(*) FROM Pessoa";
        int count = 0;

        try (Connection conn = getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(SQL)) {
            rs.next();
            count = rs.getInt(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return count;
    }

//	public int getAllPessoa() throws ClassNotFoundException {
//        String SQL = "SELECT * FROM Pessoa";
//        int cd_pessoa;
//        String 
//
//        try (Connection conn = getConnection();
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery(SQL)) {
//        	
//            rs.next();
//            count = rs.getInt(1);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return count;
//    }
	
	public String getPessoaDtNasc() throws ClassNotFoundException {
		Scanner leitor = new Scanner(System.in);
		
		String nomePessoa = leitor.next();
		leitor.close();
		
        String SQL = "SELECT dt_nasc FROM Pessoa WHERE LOWER(nome) = '" + nomePessoa + "'";
        
        String queryPessoa = null;

        try (Connection conn = getConnection();
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