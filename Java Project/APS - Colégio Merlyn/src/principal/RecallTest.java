package principal;

import java.util.ArrayList;
import java.util.List;

public class RecallTest {
	public static void main(String[] args) {
		
		/* Main de Teste para puxar os arquivos serializados */
		
		List<Pessoa> listaAlunosRecall = new ArrayList<>(Aluno.readObjectList("Alunos"));
		System.out.println("Desserializado!\n");
		
		System.out.println("Nomes dos Alunos:\n");
		
		listaAlunosRecall.get(0).c
		
		for(Pessoa x : listaAlunosRecall) {
			System.out.println(x.getNome());
		}
		
		
	}
}
