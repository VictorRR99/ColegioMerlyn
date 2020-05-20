package principal;

public abstract class Pessoa {
	private String nome, cpf, rg, dtNasc;
	
	Pessoa(String nome, String cpf, String rg, String dtNasc){
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dtNasc = dtNasc;
	}
	
	String getNome() {
		return nome;
	}

	public String getCpf() {
		return cpf;
	}

	public String getRg() {
		return rg;
	}

	public String getDtNasc() {
		return dtNasc;
	}

}
