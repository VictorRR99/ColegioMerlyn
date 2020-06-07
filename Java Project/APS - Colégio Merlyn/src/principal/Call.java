package principal;

import java.util.Scanner;

public class Call {
	
	public static void main(String[] args) throws ClassNotFoundException {
		
		/* Leitura dos arquivos */
		Professor.desserialization();
		Diretor.desserialization();
		Aluno.desserialization();
		Sala.desserialization();
		Disciplina.desserialization();
		/* Leitura dos arquivos */
		
		int rep = 0;
		
		Scanner leitor = new Scanner(System.in);
		Scanner leitor2 = new Scanner(System.in);
		
		Professor professor = new Professor("Valério Silva", "96587412355", "3588799", "22/08/1990");
		
		InterfaceGrafica.welcome();
		
		while(rep == 0) {
			
			InterfaceGrafica.operations();
			
			int userSelect = leitor.nextInt();
			
			if(userSelect == 1 ) {
				InterfaceGrafica.cadastrarAluno();
				
				String nome, cpf, rg, dtNasc, turno;
				int serie;
				
				System.out.println("Digite nome:");
				nome = leitor2.nextLine();
				
				System.out.println("Digite cpf:");
				cpf = leitor2.nextLine();
				
				System.out.println("Digite rg:");
				rg = leitor2.nextLine();
				
				System.out.println("Digite data de nascimento:");
				dtNasc = leitor2.nextLine();
				
				System.out.println("Digite serie:");
				serie = leitor.nextInt();
				
				System.out.println("Digite turno:");
				turno = leitor2.nextLine();
				
				professor.cadastrarAluno(nome, cpf, rg, dtNasc, serie, turno);
				
			}else if(userSelect == 9) {
				rep = 1;
				
			}else {
				System.out.println("Operação não existente");
			}
				
			//Sair repetição
			
		}
	
		leitor.close();
		leitor2.close();
		
		Aluno wow = new Aluno("victor", "123", "123", "22/02/2200", 5, "noturn");
		
		/* Salvamento dos arquivos */
		Professor.serialization();
		Diretor.serialization();
		Aluno.serialization();
		Sala.serialization();
		Disciplina.serialization();
		/* Salvamento dos arquivos */
		
		InterfaceGrafica.end();
		
//-----------------------------------------------------------------------------------------------------------------
		
		
		/* Serialization Test*/
		
//		Aluno victor = new Aluno("Victor Rodrigues", "08618351903", "6021469", "22/02/2001");
//		Aluno vinicius = new Aluno("Vinicius Amorim", "12345678911", "1234567", "04/08/2000");
//		Aluno leticia = new Aluno("Letícia Oliveira", "11987654321", "7654321", "16/07/2001");
//		Aluno samuel = new Aluno("Samuel Costa", "12345612345", "1234123", "01/01/2001");
//		Aluno altair = new Aluno("Altair Vega", "99224852158", "9874563", "09/05/1995");
//
//		List<Pessoa> listaAlunos = new ArrayList<>();
//		
//		listaAlunos.add(victor);
//		listaAlunos.add(vinicius);
//		listaAlunos.add(leticia);
//		listaAlunos.add(samuel);
//		listaAlunos.add(altair);
//		
//		List<Pessoa> listaProfessores = new ArrayList<>();
//		
//		Pessoa.saveObjectList(listaAlunos, "Alunos");
//		System.out.println("Serializado!");
		
		/* Original */
        
//		Aluno victor = new Aluno("Victor", "08618351903", "6021469", "22/02/2020");
//		
//		System.out.println("\n*Quantidade de Pessoas");
//		System.out.println(victor.getPessoaCount());
//		
//		System.out.println("\n===============");
//		
//		//All Pessoas
		
//		System.out.println("\n*Nome de todas as pessoas cadastradas na Escola");
//		List<String> allPessoas = new ArrayList<String>();
//		
//		allPessoas.addAll(victor.getAllPessoa());
//		
//		for(String x : allPessoas) {
//			System.out.println(x);
//		}
		
//		//All Pessoas
//		
//		System.out.println("\n===============");
//		
//		System.out.println("\n*Data de Nascimento de: " + victor.getNome());
//		System.out.println(victor.getPessoaDtNasc());
		
	}
	
}
