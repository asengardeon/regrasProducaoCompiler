package compiler;

import java.io.IOException;
import java.util.ArrayList;

import diversos.Utils;

import Models.ListaVariaveis;
import Models.Variavel;

public class Semantico implements Constants
{
	ArrayList<String> regras = new ArrayList<String>();
	ListaVariaveis lista = new ListaVariaveis();
	String nome;
	String varNome = "";
	boolean terminouCondicionais = false;
	private Utils util = new Utils();
	Variavel ultimaVar;
	
    public void executeAction(int action, Token token)	throws SemanticError, IOException
    {	
        System.out.println("Ação #"+action+", Token: "+token);
        switch (action) {
        case 1:{//inicialização do programa
        	ArrayList<String> regras = new ArrayList<String>();
        	ListaVariaveis lista = new ListaVariaveis();
        	String nome;
        	String varNome = "";
        	boolean terminouCondicionais = false;
			break;
			}
		case 2:{
			nome = token.getLexeme();
			break;
			}
		
		case 3:{
			//fim das regras grava em arquivo as variáveis e seus valores
			util.gravarVariaveisEmDisco(lista);
			
		}
		case 5:{
			String nomeRegra = token.getLexeme();
			int i = regras.indexOf(nomeRegra);
			if(i > -1){
				throw new SemanticError("Regra "+nomeRegra+" já declarada");
			}
			break;
			}
		case 6:{
			//é a sintaxe ENTAO
			terminouCondicionais = true;
			break;
			}
		
		case 7:{
			//é a sintaxe E e não faz anda por enqunato
			break;
			}
		
		case 9:{
			varNome = token.getLexeme();
			break;
			}
		case 10:{
			lista.addVariável(varNome, token.getLexeme());
			break;
			}
		case 11:{
			varNome = token.getLexeme();//para resultado
			break;
			}
		case 12:{
			ultimaVar = lista.addVariável(varNome, token.getLexeme());
			break;
			}
		case 13:{
			ultimaVar.setCnf(Integer.valueOf(token.getLexeme()));
			break;
			}
		default:
			break;
		}
    }		
}
