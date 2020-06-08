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
public class Sala implements Serializable {
	
	private static List<Sala> listaSalas = new ArrayList<>();
	
	private List<Professor> professoresQueLecionam = new ArrayList<Professor>();
	
	private List<Aluno> alunosDaSala = new ArrayList<Aluno>();
	
	String numSala;
	
	Sala(String numSala){
		if(this.checarSalas(numSala)) System.out.println("Sala já existe");
		else this.numSala = numSala;
		
	}
	
	/* Métodos Básicos */
	
	boolean checarSalas(String a) {
		for(int i = 0; i < listaSalas.size(); i++) {
			if(listaSalas.get(i).getSala() == a) return true;
		}
		return false;
	}
	
	public String getSala() {
		return numSala;
	}
	
	boolean addProfessor(Professor nmProf) {
		for(int i = 0; i < professoresQueLecionam.size(); i++) {
			if(professoresQueLecionam.get(i).getDisc() == nmProf.getDisc()) {
				System.out.println("Sala já possui tal matéria!");
				return false;
			}
		}
		nmProf.setSala(this);
		professoresQueLecionam.add(nmProf);
		return true;
	}
	
	boolean addAluno(Aluno aluno) {
		for(int i = 0; i < alunosDaSala.size(); i++) {
			if(alunosDaSala.get(i).getMat() == aluno.getMat()) {
				System.out.println("Aluno já está na sala");
				alunosDaSala.add(aluno);
				return false;
			}
		}
		return true;
	}
	
	List<String> getDiscDaSalas(){
		List<String> discDaSalas = new ArrayList<String>();
		for(int i = 0; i < professoresQueLecionam.size(); i++) {
			discDaSalas.add(professoresQueLecionam.get(i).getDisc());
		}
		return discDaSalas;
	}
	
	static boolean checarSalaCerta(int x, String sala) {
		if("" + sala.charAt(0) == Integer.toString(x)) {
			System.out.println("Sala errada para série escolhida!");
			return false;
		}
		return true;
	}
	
	/* Método Lista */
	
	public static List<Sala> getLista(){
		return listaSalas;
	}
	
	public static void colocarNaLista(Sala sala){
		listaSalas.add(sala);
	}
	
	/* Serialization Handler */

	public static void serialization() {
		Sala.saveObjectList(listaSalas, "Salas");
	}
	
	public static void desserialization() {
		listaSalas = Sala.readObjectList("Salas");
	}
	
	private static void saveObjectList(List<Sala> lista, String nomeArq) {
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
	private static List<Sala> readObjectList(String nomeArq) {
		
		List<Sala> lista = new ArrayList<Sala>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<Sala>) objInput.readObject();
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