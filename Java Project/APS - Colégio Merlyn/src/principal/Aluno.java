package principal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("serial")
public class Aluno extends Pessoa implements Serializable, Autenticacao {

	private static List<Aluno> listaAlunos =  new ArrayList<>();
	
	private Map<String, Float[]> disc = new HashMap<String, Float[]>();
	
	private static int nmTotalMat;
	
	private int serie;
	private Sala sala;
	private int mat;
	private String turno;
	
	Aluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) {
		super(nome, cpf, rg, dtNasc, senha);
		this.serie = serie;
		this.turno = turno;
		nmTotalMat++;
		String matSwap = sala + "" + "" + serie + "" + nmTotalMat;
		mat = Integer.parseInt(matSwap);
		
		if(serie < 6) {
			disc.put("portugues", new Float[2]);
			disc.put("matematica", new Float[2]);
			disc.put("educacao fisica", new Float[2]);
			disc.put("arte", new Float[2]);
		}else if(serie > 5) {
			disc.put("portugues", new Float[2]);
			disc.put("matematica", new Float[2]);
			disc.put("educacao fisica", new Float[2]);
			disc.put("ciencia", new Float[2]);
			disc.put("ingles", new Float[2]);
			disc.put("filosofia", new Float[2]);
			disc.put("geografia", new Float[2]);
			disc.put("historia", new Float[2]);
			disc.put("arte", new Float[2]);
		}
		for(Sala x : Sala.getLista()) {
			if(sala.equals(x.getSala())) {
				x.addAluno(this);
				this.sala = x;
			}
		}
	}
	
	static void deletarAluno(int mat) {
		for(Aluno x : Aluno.getLista()) {
			if(x.getMat() == mat) {
				Aluno.getLista().remove(x);
			}
		}
	}
	
	void atualizarAluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) {
		
	}
	
	/* Autenticação */
	
	@Override
	public boolean autenticar(String cpf, String senha) {
		
		Aluno usuario = null;
		
		for(int x=0; x<listaAlunos.size(); x++) {
			if(cpf.equals(listaAlunos.get(x).getCpf())) {
				usuario = listaAlunos.get(x);
				break;
			}
		}
		
		if(usuario == null) {
			InterfaceGrafica.resultAutenticacao("Usuario não Existe.");
			return false;
		}
		
		if(usuario.getSenha().equals(senha)) {
			return true;
		}else if(!usuario.getSenha().equals(senha)){
			InterfaceGrafica.resultAutenticacao("Senha Incorreta.");
			return false;
		}else {
			return false;
		}
		
		
	}
	
	/* Métodos Básicos */
	
	public static List<Aluno> getLista(){
		return listaAlunos;
	}
	
	public static void colocarNaLista(Aluno aluno){
		listaAlunos.add(aluno);
	}
	
	public int getMat() {
		return mat;
	}
	
	String getTurno() {
		return turno;
	}
	
	String getSala() {
		return sala.getSala();
	}
	
	int getSerie() {
		return serie;
	}
	
	float getNP1(String a) {
		Float[] x = disc.get(a);
		
		if(x[0].equals(null)) {
			return 0;
		}
		
		return x[0];
	}
	
	float getNP2(String a) {
		Float[] x = disc.get(a);
		
		if(x[1].equals(null)) {
			return 0;
		}
		
		return x[1];
	}
	
	void setNP1(Float np1, Professor prof) {
		Float[] np = disc.get(prof.getDisc());
		disc.remove(prof.getDisc());
		np[0] = np1;
		disc.put(prof.getDisc(), np);
	}
	
	void setNP2(Float np2, Professor prof) {
		Float[] np = disc.get(prof.getDisc());
		disc.remove(prof.getDisc());
		np[1] = np2;
		disc.put(prof.getDisc(), np);
	}
	
	Float getMedia(Professor prof) {
		Float np[] = disc.get(prof.getDisc());
		Float media = (np[0] + np[1]) / 2;
		return media;
	}
	
	/* Serialization Handler */
	
	public static void serialization() {
		Aluno.saveObjectList(listaAlunos, "Alunos");
		
		Aluno.saveMatricula(nmTotalMat, "Matricula");
	}
	
	public static void desserialization() {
		listaAlunos = Aluno.readObjectList("Alunos");
		
		nmTotalMat = Aluno.lerMatricula("Matricula");
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
		
		List<Aluno> lista = new ArrayList<Aluno>();
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				lista = (List<Aluno>) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(lista);
	}
	
	/* Salvar Matricula */
	public static void saveMatricula(int matricula, String nomeArq) {
	      File arq = new File(nomeArq);
	      try {
	    	  arq.delete();
	    	  arq.createNewFile();
	    
	    	  ObjectOutputStream objOutput = new ObjectOutputStream(new FileOutputStream(arq));
	        
	    	  objOutput.writeObject(matricula);
	    	  objOutput.close();
	    
	      } catch(IOException erro) {
	    	  System.out.printf("Erro: %s", erro.getMessage());
	      }
	}
	
	/* Salvar Matricula */
	public static int lerMatricula(String nomeArq) {
		
		int aluno = 0;
		
		try {
			File arq = new File(nomeArq);
			if (arq.exists()) {
				ObjectInputStream objInput = new ObjectInputStream(new FileInputStream(arq));
				aluno = (int) objInput.readObject();
				objInput.close();
			}
			
		} catch(IOException erro1) {
			System.out.printf("Erro: %s", erro1.getMessage());
		} catch(ClassNotFoundException erro2) {
			System.out.printf("Erro: %s", erro2.getMessage());
		}
	    
		return(aluno);
	}
	

}
