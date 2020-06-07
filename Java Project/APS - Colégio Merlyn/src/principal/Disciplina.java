package principal;

public enum Disciplina {
	portugues("Português"), matematica("Matemática"), ciencia("Ciência"), ingles("Inglês"),
	geografia("Geografia"), arte("Arte"), educacaoFisica("Educação Fisíca"), filosofia("Filosofia"),
	historia("História");
	
	public String disc;
	
	Disciplina(String x){
		disc = x;
	}
	
	String cadDisc(String a) {
		return a = disc;
	}
}
