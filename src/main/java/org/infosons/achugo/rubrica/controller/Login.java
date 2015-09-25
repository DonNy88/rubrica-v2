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
package org.infosons.achugo.rubrica.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.infosons.achugo.rubrica.model.Utente;
import org.infosons.achugo.rubrica.persistence.GestioneUtente;

/**
 * @author Achugo Donald Emeka
 *
 * @version 2015.09.20.v1
 */
public class Login {

	private GestioneUtente gu;

	private Login() {
		gu = new GestioneUtente();
	}

	private static class Container {
		public final static Login instance = new Login();
	}

	public static Login getInstance() {
		return Container.instance;
	}

	/**
	 * Verifica che gli <code>username</code> e <code>password</code>
	 * corrispondono al {@link Utente} registro nel sistema.
	 *
	 * @param username
	 * @param password
	 * @return
	 * @throws NoSuchElementException
	 * @throws IOException 
	 */
	public boolean verifica(String username, String password) throws NoSuchElementException, IOException {
		Utente u = gu.recuperaUtente();

		return u.getUsername().equalsIgnoreCase(username) && u.getPassword().equals(password);
	}

	public static void main(String[] arg) {
		Login l = Login.getInstance();
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
		
		try {
			System.out.println(l.verifica("user", "password"));
		} catch (FileNotFoundException | NoSuchElementException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
