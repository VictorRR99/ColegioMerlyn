package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Diretor extends Professor {
	
	private static List<Diretor> listaDiretores = new ArrayList<>();
	
	Diretor(String nome, String cpf, String rg, String dtNasc, String senha, Disciplina disciplina) {
		super(nome, cpf, rg, dtNasc, dtNasc, disciplina);
		this.senha = senha;
	}
	
	/* Métodos Básicos */

	public static List<Diretor> getListaDir(){
		return listaDiretores;
	}
	
	public static void colocarNaLista(Diretor diretor){
		listaDiretores.add(diretor);
	}
	
	/* Serialization Handler */

	public static void serialization() {
		Diretor.saveObjectListDir(listaDiretores, "Diretores");
	}
	
	public static void desserialization() {
		listaDiretores = Diretor.readObjectListDir("Diretores");
	}
	
	//Alterado nome do readObjectList por causa de conflito com a superclasse
	private static void saveObjectListDir(List<Diretor> lista, String nomeArq) {
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
	
	//Alterado nome do readObjectList por causa de conflito com a superclasse
	@SuppressWarnings("all")
	private static List<Diretor> readObjectListDir(String nomeArq) {
		
		List<Diretor> lista = new ArrayList<Diretor>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<Diretor>) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
		
	}
	
	/* Cadastrar Professor */
	
	public static boolean cadastrarAluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) {

		if("" + sala.charAt(0) == Integer.toString(serie)) {
			System.out.println("Sala errada para série escolhida!");
			return true;
		}
		
		Aluno.colocarNaLista(new Aluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha));
		
		return false;
	}
	
	/* Cadastro Aluno */
	
	public static void cadastrarProfessor(String nome, String cpf, String rg, String dtNasc, String senha, Disciplina disciplina) {

		Professor.colocarNaLista(new Professor(nome, cpf, rg, dtNasc, senha, disciplina));
		
	}
	
}
