package diversos;

import java.util.ArrayList;

import Models.Variavel;

public class VarList extends ArrayList {
	
	public int indexOf(String o) {
		Variavel v = null;
		for(int i = 0; i< size(); i++){
			v = (Variavel) get(i);
			if(o.equals(v.getNome())){
				return i;
			}
		}
		return -1;
		
	}
	
	@Override
	public Variavel get(int index) {
		return (Variavel)super.get(index);
	}

}
