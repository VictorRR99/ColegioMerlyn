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

@SuppressWarnings("serial")
public class Diretor extends Pessoa implements Serializable, Autenticacao {

	private static List<Diretor> listaDiretores = new ArrayList<Diretor>();
	
	Diretor(String nome, String cpf, String rg, String dtNasc, String senha) {
		super(nome, cpf, rg, dtNasc, senha);
	}
	
	/* Autenticação */
	
	@Override
	public boolean autenticar(String cpf, String senha) {
		
		Diretor usuario = null;
		
		for(int x=0; x<listaDiretores.size(); x++) {
			if(cpf.equals(listaDiretores.get(x).getCpf())) {
				usuario = listaDiretores.get(x);
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
			System.out.println("n sei o motivo");
			return false;
		}
		
		
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
	
	public static boolean cadastrarAluno(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) throws SQLException {

		if("" + sala.charAt(0) == Integer.toString(serie)) {
			System.out.println("Sala errada para série escolhida!");
			return true;
		}
		
		Aluno.colocarNaLista(new Aluno(nome, cpf, rg, dtNasc, serie, turno, sala, senha));
		
		return false;
	}
	
	public static boolean cadastrarAlunoFundamental2(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) throws SQLException {

		if("" + sala.charAt(0) == Integer.toString(serie)) {
			System.out.println("Sala errada para série escolhida!");
			return true;
		}
		
		Aluno.colocarNaLista(new AlunoFundamental2(nome, cpf, rg, dtNasc, serie, turno, sala, senha));
		
		return false;
	}
	
	/* Cadastro Aluno */
	
	public static void cadastrarProfessor(String nome, String cpf, String rg, String dtNasc, String senha, Disciplina disciplina) throws SQLException {
		Professor.colocarNaLista(new Professor(nome, cpf, rg, dtNasc, senha, disciplina));
	}
	
	/* Cadastro Diretor */
	
	public static void cadastrarDiretor(String nome, String cpf, String rg, String dtNasc, String senha) throws SQLException {
		Diretor.colocarNaLista(new Diretor(nome, cpf, rg, dtNasc, senha));
	}
	
	
	/*Cadastro Sala */
	
	static void cadastrarSala(String nmSala) {
		Sala.colocarNaLista(new Sala(nmSala));
	}
	
	/*Destruir diretor */
	static void deletarDiretor(String cpf) {
		for(Diretor x : Diretor.getListaDir()) {
			if(x.getCpf().equals(cpf)) {
				Diretor.getListaDir().remove(x);
				break;
			}
		}
	}
}
