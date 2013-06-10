package Models;

import java.util.ArrayList;
import java.util.HashMap;

import diversos.VarList;

public class ListaVariaveis {
	VarList variaveis = new VarList();
	
	public Variavel addVariável(String nome, String valor){
		int i = variaveis.indexOf(nome);
		Variavel v ;
		if(i> -1){
			v = variaveis.get(i);
		}else{
			v = new Variavel(nome);
			variaveis.add(v);
		}
		v.addVariavel(valor);	
		return v;
	}
	
	public int size(){
		return variaveis.size();
	}
	
	public Variavel getVariavel(int i){
		return variaveis.get(i);
	}
}
