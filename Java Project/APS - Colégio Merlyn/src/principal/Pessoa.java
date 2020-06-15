package principal;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;
import java.util.ArrayList;

import bdConnection.*;

@SuppressWarnings("serial")
public abstract class Pessoa implements Serializable{
	
	private String nome, cpf, rg, dtNasc;
	private String senha;
	
	Pessoa(String nome, String cpf, String rg, String dtNasc, String senha) {
		this.nome = nome;
		this.cpf = cpf;
		this.rg = rg;
		this.dtNasc = dtNasc;
		this.senha = senha;
	}
	
	/* Métodos Básicos*/
	
	public String getNome() {
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
	
	public String getSenha() {
		return senha;
	}

}


