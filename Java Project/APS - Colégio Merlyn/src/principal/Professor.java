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
public class Professor extends Pessoa implements Serializable, Autenticacao {
	
	private static List<Professor> listaProfessores = new ArrayList<>();
	
	private List<Aluno> listaAlunosDoProfessor = new ArrayList<Aluno>();
	
	private Disciplina disciplina;
	
	private Sala sala;
	
	Professor(String nome, String cpf, String rg, String dtNasc, String senha, Disciplina disciplina) {
		super(nome, cpf, rg, dtNasc, senha);
		this.disciplina = disciplina;
	}

	/* Autentica��o */
	
	@Override
	public boolean autenticar(String cpf, String senha) {
		
		Professor usuario = null;
		
		for(int x=0; x<listaProfessores.size(); x++) {
			if(cpf.equals(listaProfessores.get(x).getCpf())) {
				usuario = listaProfessores.get(x);
				break;
			}
		}
		
		if(usuario == null) {
			InterfaceGrafica.resultAutenticacao("Usuario n�o Existe.");
			return false;
		}else if(usuario.getSenha().equals(senha)) {
			return true;
		}else if(!usuario.getSenha().equals(senha)){
			InterfaceGrafica.resultAutenticacao("Senha Incorreta.");
			return false;
		}else {
			System.out.println("n sei o motivo");
			return false;
		}
		
	}
	
	
	/* M�todos B�sicos */
	
	String getDisc() {
		return disciplina.getNomeDisc();
	}

	String getSala() {
		return sala.getSala();
	}
	
	void setSala(Sala sala) {
		this.sala = sala;
	}
	
	void addAluno(Aluno aluno) {
		this.getListaAluno().add(aluno);
	}
	
	public static List<Professor> getLista(){
		return listaProfessores;
	}
	
	public static void colocarNaLista(Professor professor){
		listaProfessores.add(professor);
	}
	
	void receberAlunos() {
		for(Aluno x : Aluno.getLista()) {
			if(x.getSala().equals(this.sala.getSala())) {
				this.getListaAluno().add(x);
			}
		}
	}
	
	List<Aluno> getListaAluno(){
		return listaAlunosDoProfessor;
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
	public static List<Professor> readObjectList(String nomeArq) {
		
		List<Professor> lista = new ArrayList<Professor>();
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<Professor>) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
		
	}
	
	//Destroi Professor
	static void deletarProfessor(String cpf) {
		for(Professor x : Professor.getLista()) {
			if(x.getCpf().equals(cpf)) {
				Professor professor = x;
				Professor.getLista().remove(professor);
				break;
			}
		}
	}

}
