/**
 *  This file is part of Rubrica.
 *
 *  Rubrica is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 * 	(at your option) any later version.
 *
 *  Rubrica is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the 
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Rubrica. If not, see <http://www.gnu.org/licenses/>.
 */
package org.infosons.achugo.rubrica.persistence;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.infosons.achugo.rubrica.model.Utente;

/**
 * @author Achugo Donald Emeka
 *
 * @version 2015.09.20.v1
 */
public class GestioneUtente {

	private final static String nomeFile = "utente.dat";

	/**
	 * Salva l'utente nel sistema
	 * 
	 * @param u
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void salvaUtente(Utente u) throws FileNotFoundException {
		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File(nomeFile), false)));
		p.println(u.toString());
		p.close();
	}

	/**
	 * Legge il file contente le credeziali dell'utente.
	 * 
	 * @param u
	 * @return
	 * @throws NoSuchElementException
	 * @throws IOException
	 */
	public Utente recuperaUtente() throws NoSuchElementException, IOException {
		Scanner s = null;
		try {
			s = new Scanner(new BufferedInputStream(new FileInputStream(new File(nomeFile))));
		} catch (FileNotFoundException fnfe) {
			creaFileCredezialiStandard();
		}
		String[] campiUtente = s.nextLine().split("\\;");
		s.close();

		return new Utente(campiUtente[0], campiUtente[1]);
	}

	private void creaFileCredezialiStandard() throws IOException {
		String testo = "user;password";
		File fout = new File(nomeFile);
		
		System.out.println("Ciao bello moio");

		creaFileSeNonEsiste(fout);

		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(fout, true)));
		p.println(testo);
		p.close();
	}

	/**
	 * Crea un file, se non già presente, nel sistema.
	 * 
	 * @param f
	 * @throws IOException
	 */
	private void creaFileSeNonEsiste(File f) throws IOException {
		if (!f.exists()) {
			f.createNewFile();
		}
	}

	public static void main(String[] args) {
		GestioneUtente gu = new GestioneUtente();

		try {
			gu.salvaUtente(new Utente("user", "password"));
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			Utente utenteRecuperato = gu.recuperaUtente();
			System.out.println(utenteRecuperato);
		} catch (FileNotFoundException | NoSuchElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
