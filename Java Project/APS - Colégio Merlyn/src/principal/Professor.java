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
public class Professor extends Pessoa implements Serializable{
	
	private static List<Professor> listaProfessores = new ArrayList<>();
	
	private Disciplina disciplina;
	
	Professor(String nome, String cpf, String rg, String dtNasc) {
		super(nome, cpf, rg, dtNasc);
	}

	/* Serialization Handler */

	public static void serialization() {
		Professor.saveObjectList(listaProfessores, "Professores");
	}
	
	public static void desserialization() {
		listaProfessores = Professor.readObjectList("Professores");
	}
	
	private static void saveObjectList(List<Professor> lista, String nomeArq) {
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
	private static List<Professor> readObjectList(String nomeArq) {
		
		ArrayList<Professor> lista = new ArrayList<Professor>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (ArrayList<Professor>) objInput.readObject();
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
	
	public void cadastrarAluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno) {
		 Aluno.colocarNaLista(new Aluno(nome, cpf, rg, dtNasc, serie, turno));
	}
}
