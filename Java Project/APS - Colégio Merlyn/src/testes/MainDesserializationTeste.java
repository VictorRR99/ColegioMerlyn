package testes;

import java.util.*;

public class MainDesserializationTeste {
	public static void main(String[] args) {
		
		/*ClasseTeste1 e SubClasseTeste2*/
		/* Teste Serialization */
		
		/* Leitura de Arquivos */
		ClasseTeste1.desserialization();
		SubClasseTeste2.desserialization();
		/* Leitura de Arquivos */
		
		System.out.println("Desserialização completa!");
		
		List<ClasseTeste1> listaClasse = ClasseTeste1.getLista();
		List<SubClasseTeste2> listaSubClasse = SubClasseTeste2.getListaSub();
		
		System.out.println("+Nomes Classe");
		for(int x=0; x<listaClasse.size(); x++) {
			System.out.println(listaClasse.get(x).getNome());
		}
		
		System.out.println("===========");
		
		System.out.println("+Nomes SubClasse");
		for(int y=0; y<listaSubClasse.size(); y++) {

			System.out.println(listaSubClasse.get(y).getNome());
		}
	}
}
