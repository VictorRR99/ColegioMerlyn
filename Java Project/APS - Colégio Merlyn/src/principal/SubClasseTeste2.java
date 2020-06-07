package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SubClasseTeste2 implements Serializable {

	private String nome, data;
	
	private static List<SubClasseTeste2> listaSubClasseTeste2 = new ArrayList<>();
	
	SubClasseTeste2(String nome, String data){
		this.nome = nome;
		this.data = data;
	}
	
	/* Métodos Básicos */
	
	public String getNome() {
		return nome;
	}
	
	public static List<SubClasseTeste2> getLista(){
		return listaSubClasseTeste2;
	}
	
	/* Serialization Handler */

	public static void serialization() {
		SubClasseTeste2.saveObjectListSub(listaSubClasseTeste2, "SubClasse");
	}
	
	public static void desserialization() {
		listaSubClasseTeste2 = SubClasseTeste2.readObjectListSub("SubClasse");
	}
	
	private static void saveObjectListSub(List<SubClasseTeste2> lista, String nomeArq) {
	      File arq = new File(nomeArq);
	      try {
	    	  arq.delete();
	    	  arq.createNewFile();
	    
	    	  ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
	        
	    	  objOutput.writeObject(lista);
	    	  objOutput.close();
	    
	      } catch(IOException erro) {
	    	  System.out.printf("Erro: %s", erro.getMessage());
	      }
	}
	
	@SuppressWarnings("all")
	public static List<SubClasseTeste2> readObjectListSub(String nomeArq) {
		
		List<SubClasseTeste2> lista = new ArrayList<SubClasseTeste2>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<SubClasseTeste2>) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
		
	}
	
	/* Cadastro Aluno */
	
	public static void cadastrarSubClasse(String nome, String data) {
		listaSubClasseTeste2.add(new SubClasseTeste2(nome, data));
	}
	
}
