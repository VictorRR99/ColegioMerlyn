package principal;

public class Call {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
//		try {
//			@SuppressWarnings("unused")
//			Connection conexao = Conexao.getConnection();
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
			
		Aluno victor = new Aluno("Victor", "08618351903", "6021469", "22/02/2020");
		
		System.out.println(victor.getPessoaCount());
		
		System.out.println(victor.getPessoaDtNasc());
		
	}
	
}
