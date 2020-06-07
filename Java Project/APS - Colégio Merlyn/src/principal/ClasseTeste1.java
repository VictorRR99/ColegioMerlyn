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

public class ClasseTeste1 implements Serializable {

	private String nome, data;
	
	private static List<ClasseTeste1> listaClasseTeste1 = new ArrayList<>();
	
	ClasseTeste1(String nome, String dataNasc){
		this.nome = nome;
		this.data = dataNasc;
	}
	
	/*Métodos Básicos*/
	
	public String getNome() {
		return nome;
	}
	
	public static List<ClasseTeste1> getLista(){
		return listaClasseTeste1;
	}
	
	/* Serialization Handler */

	public static void serialization() {
		ClasseTeste1.saveObjectList(listaClasseTeste1, "ClasseTeste");
	}
	
	public static void desserialization() {
		listaClasseTeste1 = ClasseTeste1.readObjectList("ClasseTeste");

	}
	
	private static void saveObjectList(List<ClasseTeste1> lista, String nomeArq) {
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
	public static List<ClasseTeste1> readObjectList(String nomeArq) {
		
		List<ClasseTeste1> lista = new ArrayList<ClasseTeste1>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<ClasseTeste1>) objInput.readObject();
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
	
	public static void cadastrarClasse(String nome, String data) {
		listaClasseTeste1.add(new ClasseTeste1(nome, data));
	}
	
}
