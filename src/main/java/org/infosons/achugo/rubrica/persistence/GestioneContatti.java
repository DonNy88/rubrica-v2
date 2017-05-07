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

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

import org.infosons.achugo.rubrica.model.Persona;

/**
 * @author Achugo Donald Emeka
 *
 * @version 2015.09.20.v2
 */
public class GestioneContatti {

	private final String CARTELLA_FILE = "informazioni";

	/**
	 * Salva le informazioni del contatto nel file txt così nominato:
	 * NOME-COGNOME.txt.
	 * 
	 * @param nuovoContatto
	 * @throws IOException
	 */
	public void salvaContatto(Persona nuovoContatto) throws IOException {
		String nome = nuovoContatto.getNome().split("\\s+")[0];
		String cognome = nuovoContatto.getCognome().split("\\s+")[0];
		creaCartella(CARTELLA_FILE);
		File fout = new File(CARTELLA_FILE + "/" + nome.toUpperCase() + "-" + cognome.toUpperCase() + ".txt");
		creaFileSeNonEsiste(fout);
		salvaSeNonEsisteContatto(fout, nuovoContatto);
	}

	/**
	 * Modifica le infromazioni di un contatto già presente nel sistema.
	 * 
	 * @param contattoDaModificare
	 * @param nuovoContatto
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void modificaContatto(Persona contattoDaModificare, Persona nuovoContatto) throws FileNotFoundException, IOException {
		if (contattoDaModificare.getCognome().equalsIgnoreCase(nuovoContatto.getCognome())
				&& contattoDaModificare.getNome().equalsIgnoreCase(nuovoContatto.getNome())) {
			modificaNelloStessoFile(contattoDaModificare, nuovoContatto);
		} else {
			modificaInUnFileDiverso(contattoDaModificare, nuovoContatto);
		}
	}

	/**
	 * Elimina tutte le informazioni di {@code contattoDaCancellare} presenti
	 * nel sistema
	 * 
	 * @param contattoDaCancellare
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void eliminaContatto(Persona contattoDaCancellare) throws FileNotFoundException, IOException {
		String nome = contattoDaCancellare.getNome().split("\\s+")[0];
		String cognome = contattoDaCancellare.getCognome().split("\\s+")[0];
		String path = CARTELLA_FILE + "/" + nome.toUpperCase() + "-" + cognome.toUpperCase() + ".txt";
		FileInputStream fin = new FileInputStream(path);

		Scanner sin = new Scanner(fin);
		String line = "";
		String contattoDaModificareStr = contattoDaCancellare.toString();
		File tmpFile = new File(CARTELLA_FILE + "/tmpFile");
		tmpFile.createNewFile();
		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(tmpFile, true)));
		boolean flag = false;

		while (!flag) {
			try {
				line = sin.nextLine();
				if (!line.equalsIgnoreCase(contattoDaModificareStr)) {
					p.println(line);
				}
			} catch (NoSuchElementException e) {
				flag = true;
			}
		}
		p.close();
		sin.close();

		File f = new File(path);
		aggiornaFile(tmpFile, f);
		if (isEmptyFile(f)) {
			Files.deleteIfExists(f.toPath());
		}
	}

	/**
	 * Carica in un {@link ArrayList} di {@link Persona} con tutti contatti
	 * presenti nel sistema. Metodo che ha una complessita' di O(n).
	 * 
	 * @return tutti i contatti
	 * @throws IOException
	 */
	public List<Persona> recuperaTuttiContatti() throws IOException {
		File cartella = new File(CARTELLA_FILE);

		if (!listaVuoto(cartella)) {
			List<Persona> risultato = new ArrayList<Persona>();

			for (final File fileEntry : cartella.listFiles()) {
				List<Persona> tmp = new ArrayList<Persona>();
				for (String s : Files.readAllLines(fileEntry.toPath(), Charset.defaultCharset())) {
					String[] campiContatto = s.split("\\;");
					tmp.add(new Persona(campiContatto[0], campiContatto[1], campiContatto[2], campiContatto[3], Integer.parseInt(campiContatto[4])));
				}
				risultato.addAll(tmp);
				tmp.clear();
			}

			return risultato;
		}

		return null;
	}

	/**
	 * Controlla che lista dei contatti è vuota.
	 * 
	 * @param cartella
	 *            da controllare
	 * @return {@code true} se la rubrica è vuota, {@code false} altrimenti
	 */
	private boolean listaVuoto(File cartella) {
		return !cartella.exists() || cartella.isFile() || cartella.list().length == 0;
	}

	/**
	 * Crea una cartella, se non già presente, nominandola {@code nomeCartella}.
	 * 
	 * @param nomeCartella
	 */
	private void creaCartella(String nomeCartella) {
		File cartella = new File(nomeCartella);

		if (!cartella.exists()) {
			cartella.mkdir();
		}
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

	/**
	 * Metodo che si occupa esclusivamente di rendere persistente le
	 * informazioni di {@code contatto}.
	 * 
	 * @param f
	 * @param contatto
	 * @throws FileNotFoundException
	 */
	private void salvaSeNonEsisteContatto(File f, Persona contatto) throws FileNotFoundException {
		Scanner s = new Scanner(f);
		String line = "";
		String output = contatto.toString();
		boolean flag = false;

		while (!flag) {
			try {
				line = s.nextLine();
				if (output.equalsIgnoreCase(line)) {
					s.close();
					return;
				}
			} catch (NoSuchElementException e) {
				flag = true;
			}
		}
		s.close();

		// salva il contatto nel file ./informazioni/NOME-COGNOME.txt
		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(f, true)));
		p.println(output);
		p.close();
	}

	/**
	 * Modifica le informazioni di un contatto già presente nel sistema.
	 * 
	 * @param contattoDaModificare
	 * @param nuovoContatto
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void modificaInUnFileDiverso(Persona contattoDaModificare, Persona nuovoContatto) throws FileNotFoundException, IOException {
		String nome = contattoDaModificare.getNome().split("\\s+")[0];
		String cognome = contattoDaModificare.getCognome().split("\\s+")[0];
		File fin = new File(CARTELLA_FILE + "/" + nome.toUpperCase() + "-" + cognome.toUpperCase() + ".txt");

		Scanner sin = new Scanner(fin);
		String line = "";
		String contattoDaModificareStr = contattoDaModificare.toString();
		File tmpFile = new File(CARTELLA_FILE + "/tmpFile");
		tmpFile.createNewFile();
		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(tmpFile, true)));
		boolean flag = false;

		while (!flag) {
			try {
				line = sin.nextLine();
				if (!line.equalsIgnoreCase(contattoDaModificareStr)) {
					p.println(line);
				}
			} catch (NoSuchElementException e) {
				flag = true;
			}
		}
		p.close();
		sin.close();

		if (!isEmptyFile(tmpFile)) {
			aggiornaFile(tmpFile, fin);
		} else {
			Files.deleteIfExists(tmpFile.toPath());
			Files.deleteIfExists(fin.toPath());
		}
		salvaContatto(nuovoContatto);
	}

	/**
	 * Metedo che controlla se un file è vuoto.
	 * 
	 * @param f
	 * @return {@code true} se il {@link File} {@code f} è vuoto, {@code false}
	 *         altrimenti
	 */
	private boolean isEmptyFile(File f) {
		return f.length() == 0;
	}

	/**
	 * Cambia le informazioni relative di un contatto che non modifica i campi
	 * {@code nome} e {@code cognome}.
	 * 
	 * @param contattoDaModificare
	 * @param nuovoContatto
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private void modificaNelloStessoFile(Persona contattoDaModificare, Persona nuovoContatto) throws FileNotFoundException, IOException {
		String nome = contattoDaModificare.getNome().split("\\s+")[0];
		String cognome = contattoDaModificare.getCognome().split("\\s+")[0];
		FileInputStream fin = new FileInputStream(CARTELLA_FILE + "/" + nome.toUpperCase() + "-" + cognome.toUpperCase() + ".txt");

		Scanner sin = new Scanner(fin);
		String line = "";
		String contattoDaModificareStr = contattoDaModificare.toString();
		File tmpFile = new File(CARTELLA_FILE + "/tmpFile");
		tmpFile.createNewFile();
		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(tmpFile, false)));
		boolean flag = false;

		while (!flag) {
			try {
				line = sin.nextLine();
				if (!line.equalsIgnoreCase(contattoDaModificareStr)) {
					p.println(line);
				}
			} catch (NoSuchElementException e) {
				flag = true;
			}
		}
		p.close();
		sin.close();

		File f = new File(CARTELLA_FILE + "/" + nome.toUpperCase() + "-" + cognome.toUpperCase() + ".txt");
		aggiornaFile(tmpFile, f);
		salvaSeNonEsisteContatto(f, nuovoContatto);
	}

	/**
	 * Copia tutto il contenuto del {@link File} {@code tmpFile} in {@link File}
	 * {@code f}. Ed infine elimina {@code tmpFile} dal sistema.
	 * 
	 * @param tmpFile
	 * @param f
	 * @throws IOException
	 */
	private void aggiornaFile(File tmpFile, File f) throws IOException {
		Path sorgente = tmpFile.toPath();
		Path destinazione = f.toPath();

		Files.deleteIfExists(destinazione);
		Files.copy(sorgente, destinazione);
		Files.deleteIfExists(sorgente);
	}
}
