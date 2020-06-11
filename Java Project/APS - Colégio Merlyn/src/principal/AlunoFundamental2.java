package principal;

public class AlunoFundamental2 extends Aluno{
	
	private static final long serialVersionUID = 1L;
	
	Float[] cie = new Float[2];
	Float[] ing = new Float[2];
	Float[] fil = new Float[2];
	Float[] geo = new Float[2];
	Float[] his = new Float[2];

	AlunoFundamental2(String nome, String cpf, String rg, String dtNasc, int serie, String turno, String sala, String senha) {
		super(nome, cpf, rg, dtNasc, serie, turno, sala, senha);
		
		cie[0] = 0f;
		cie[1] = 0f;
		
		ing[0] = 0f;
		ing[0] = 0f;
		
		fil[0] = 0f;
		fil[1] = 0f;
		
		geo[0] = 0f;
		geo[1] = 0f;
		
		his[0] = 0f;
		his[1] = 0f;
		
		disc.put("ciencia", cie);
		disc.put("ingles", ing);
		disc.put("filosofia", fil);
		disc.put("geografia", geo);
		disc.put("historia", his);
	}

}
