package principal;

public enum Disciplina {
	portugues("Portugu�s"), matematica("Matem�tica"), ciencia("Ci�ncia"), ingles("Ingl�s"),
	geografia("Geografia"), arte("Arte"), educacaoFisica("Educa��o Fis�ca"), filosofia("Filosofia"),
	historia("Hist�ria");
	
	public String disc;
	
	Disciplina(String x){
		disc = x;
	}
	
	String cadDisc(String a) {
		return a = disc;
	}
}
