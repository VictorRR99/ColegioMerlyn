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

@SuppressWarnings("serial")
public class Disciplina implements Serializable {

	private static List<Disciplina> listaDisciplinas = new ArrayList<>();
	
	private String nomeDisc;
	
	Disciplina(String nomeDisc){
//		Apenas teste, talvez remover depois
		if(Disciplina.checaDuplicata(nomeDisc)) {
			System.out.println("Disciplina j� existe! >:(");
		}
		else {
			this.nomeDisc = nomeDisc;
		}
	}
	
	/*M�todos B�sicos*/
	
	String getNomeDisc() {
		return nomeDisc;
	}
	
	public static boolean checaDuplicata(String a) {
		for(int i = 0; i < listaDisciplinas.size(); i++) {
			if(listaDisciplinas.get(i).getNomeDisc() == a) {
				return true;
			}
		}
		return false;
	}
	
	public static List<Disciplina> getLista(){
		return listaDisciplinas;
	}
	
	public static void colocarNaLista(Disciplina disciplina){
		listaDisciplinas.add(disciplina);
	}
	
	/* Cadastro Disciplina */
	
	public static void cadastrarDisciplina(String nomeDisc) {
		Disciplina.colocarNaLista(new Disciplina(nomeDisc));
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
		
		List<Disciplina> lista = new ArrayList<Disciplina>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<Disciplina>) objInput.readObject();
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
