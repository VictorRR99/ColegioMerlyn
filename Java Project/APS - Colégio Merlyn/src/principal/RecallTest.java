package principal;

import java.util.*;

public class RecallTest {
	public static void main(String[] args) {
		
		/* Principal */
		
		Professor.desserialization();
		Aluno.desserialization();
		
		System.out.println("Desserialização completa!");
		
		List<Aluno> listaAlunos = Aluno.getLista();
		
		List<Professor> listaProfessores = Professor.getLista();
		
		System.out.println("+Nomes dos Alunos:");
		for(Aluno x : listaAlunos) {
			System.out.println(x.getNome());
		}
		
		System.out.println("==============");
		
		System.out.println("+Nomes dos Professores:");
		for(Professor x : listaProfessores) {
			System.out.println(x.getNome());
		}
		
		System.out.println("End.");
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\
		
		/*ClasseTeste1 e SubClasseTeste2*/
		/* Teste Serialization */
//		
//		ClasseTeste1.desserialization();
//		SubClasseTeste2.desserialization();
//		System.out.println("Desserialização completa!");
//		
//		
//		
//		List<ClasseTeste1> listaClasse = ClasseTeste1.getLista();
//		List<SubClasseTeste2> listaSubClasse = SubClasseTeste2.getLista();
//		
//		for(int x=0; x<listaClasse.size(); x++) {
//			System.out.println("Nomes Classe");
//			System.out.println(listaClasse.get(x).getNome());
//		}
//		
//		System.out.println("===========");
//		
//		for(int y=0; y<listaSubClasse.size(); y++) {
//			System.out.println("Nomes SubClasse");
//			System.out.println(listaSubClasse.get(y).getNome());
//		}
		
//-----------------------------------------------------------------------------------------------------------------------------------------------------\\

		/* Main de Teste para puxar os arquivos serializados */
		
		//List<Professor> listaAlunosRecall = new ArrayList<>(Professor.readObjectList("Alunos"));
//		System.out.println("Desserializado!\n");
//		
//		System.out.println("Nomes dos Alunos:\n");
		
		//listaAlunosRecall.get(0).cadastrarAluno("Perna Longa", "65478932145", "1568749", "10/11/2005");
		
//		for(Pessoa x : listaAlunosRecall) {
//			System.out.println(x.getNome());
//		}
		
		
	}
}
