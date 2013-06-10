package diversos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import Models.ListaVariaveis;
import Models.Variavel;

public class Utils {

	public void gravarVariaveisEmDisco(ListaVariaveis lista) throws IOException {
		File arquivo;

		arquivo = new File("c:\\variaveis.txt");
		FileOutputStream fos = null;
		fos = new FileOutputStream(arquivo);
		String texto;
		Variavel v;
		try {
			for (int i = 0; i < lista.size(); i++) {
				v = lista.getVariavel(i);
				texto = "*Objetiva*\n";
				fos.write(texto.getBytes());
				texto = "[" + v.getNome() + "]\n";
				fos.write(texto.getBytes());
				for (int j = 0; j < v.getValores().size(); j++) {
					texto = "    " + v.getValores().get(j) + "\n";
					fos.write(texto.getBytes());
				}
			}
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		// Lendo do arquivo
		/**
		 * arquivo = new File("arquivo.txt"); FileInputStream fis = new
		 * FileInputStream(arquivo);
		 * 
		 * int ln; while ( (ln = fis.read()) != -1 ) { System.out.print(
		 * (char)ln ); }
		 * 
		 * fis.close();
		 */
	}
}
