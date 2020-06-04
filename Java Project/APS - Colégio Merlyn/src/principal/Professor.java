package principal;

public class Professor extends Pessoa{
	
	private static final long serialVersionUID = 1L;
	
	Disciplina disciplina;
	
	Professor(String nome, String cpf, String rg, String dtNasc) {
		super(nome, cpf, rg, dtNasc);
	}

}
