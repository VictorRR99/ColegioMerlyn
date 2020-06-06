package principal;

import java.util.ArrayList;
import java.util.List;

public class RecallTest {
	public static void main(String[] args) {
		
		/* Main de Teste para puxar os arquivos serializados */
		
		List<Pessoa> listaAlunosRecall = new ArrayList<>(Pessoa.readObjectList("Alunos"));
		System.out.println("Desserializado!\n");
		
		System.out.println("Nomes dos Alunos:\n");
		
		for(Pessoa x : listaAlunosRecall) {
			System.out.println(x.getNome());
		}
		
		
	}
}
