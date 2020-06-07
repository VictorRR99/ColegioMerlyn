package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Disciplina {

	private static List<Disciplina> listaDisciplinas = new ArrayList<>();
	
	Disciplina(){
		
	}
	
	/* Serialization Handler */

	public static void serialization() {
		Disciplina.saveObjectList(listaDisciplinas, "Disciplinas");
	}
	
	public static void desserialization() {
		listaDisciplinas = Disciplina.readObjectList("Disciplinas");
	}
	
	private static void saveObjectList(List<Disciplina> lista, String nomeArq) {
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
	private static List<Disciplina> readObjectList(String nomeArq) {
		
		ArrayList<Disciplina> lista = new ArrayList<Disciplina>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (ArrayList<Disciplina>) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
		
	}
	
	
	
}
