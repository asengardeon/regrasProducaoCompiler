package Models;

import java.util.ArrayList;

public class Variavel {
	

	String nome = "";
	ArrayList<String> valores = new ArrayList<String>();
	int cnf = 100;//o valor padrão é sempre 100%

	public int getCnf() {
		return cnf;
	}

	public void setCnf(int cnf) {
		this.cnf = cnf;
	}

	public ArrayList<String> getValores() {
		return valores;
	}

	public String getNome() {
		return nome;
	}

	public void addVariavel(String valor) {
		if (!(this.valores.indexOf(valor) > -1)) {
			this.valores.add(valor);
		}
	}

	public Variavel(String nome) {
		super();
		this.nome = nome;
	}
	
	
}
