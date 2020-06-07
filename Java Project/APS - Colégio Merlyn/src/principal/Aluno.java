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
public class Aluno extends Pessoa implements Serializable{

	private static List<Aluno> listaAlunos =  new ArrayList<>();
	
	private static int nmTotalMat;
	
	private int serie;
	private int sala;
	private String mat;
	private String turno;
	
	Aluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno) {
		super(nome, cpf, rg, dtNasc);
		this.serie = serie;
		this.turno = turno;
		nmTotalMat++;
		// implementar escolha de sala
		mat = sala + "" + "" + serie + "" + nmTotalMat; 
	}
	
	/* M�todos B�sicos */
	
	public static void colocarNaLista(Aluno aluno){
		listaAlunos.add(aluno);
	}
	
	/* Serialization Handler */
	
	public static void serialization() {
		Aluno.saveObjectList(listaAlunos, "Alunos");
	}
	
	public static void desserialization() {
		listaAlunos = Aluno.readObjectList("Alunos");
	}
	
	public static void saveObjectList(List<Aluno> lista, String nomeArq) {
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
	public static List<Aluno> readObjectList(String nomeArq) {
		
		ArrayList<Aluno> lista = new ArrayList<Aluno>();
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (ArrayList<Aluno>) objInput.readObject();
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
