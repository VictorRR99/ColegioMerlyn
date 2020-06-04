package principal;

import java.util.ArrayList;
import java.util.List;

public class Call {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
//		try {
//			@SuppressWarnings("unused")
//			Connection conexao = Conexao.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
			
		Aluno victor = new Aluno("Victor", "08618351903", "6021469", "22/02/2020");
		
		System.out.println("\n*Quantidade de Pessoas");
		System.out.println(victor.getPessoaCount());
		
		System.out.println("\n===============");
		
		//All Pessoas
		System.out.println("\n*Nome de todas as pessoas cadastradas na Escola");
		List<String> allPessoas = new ArrayList<String>();
		
		allPessoas.addAll(victor.getAllPessoa());
		
		for(String x : allPessoas) {
			System.out.println(x);
		}
		//All Pessoas
		
		System.out.println("\n===============");
		
		System.out.println("\n*Data de Nascimento de: " + victor.getNome());
		System.out.println(victor.getPessoaDtNasc());
		
	}
	
}
