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

import java.io.IOException;

import org.infosons.achugo.rubrica.exceptions.ContactDidNotSave;
import org.infosons.achugo.rubrica.exceptions.SomeFieldsIsEmpty;
import org.infosons.achugo.rubrica.model.Persona;
import org.infosons.achugo.rubrica.persistence.GestioneContatti;

/**
 * @author Achugo Donald Emeka
 *
 * @version 2015.09.19.v1
 */
public class NuovoContatto {

	private GestioneContatti gc;

	private NuovoContatto() {
		gc = new GestioneContatti();
	}

	private static class SingletonHelper {
		private static final NuovoContatto INSTANCE = new NuovoContatto();
	}

	public static NuovoContatto getInstance() {
		return SingletonHelper.INSTANCE;
	}

	/**
	 * Salva {@code nuovoContatto} nel sistema.
	 * 
	 * @param nuovoContatto
	 * @throws SomeFieldsIsEmpty
	 * @throws ContactDidNotSave
	 */
	public void inserisciNuovoContatto(Persona nuovoContatto) throws /*SomeFieldsIsEmpty,*/ ContactDidNotSave {
		// if (!isComplete(nuovoContatto)) {
		// throw new SomeFieldsIsEmpty();
		// }

		try {
			gc.salvaContatto(nuovoContatto);
		} catch (IOException e) {
			throw new ContactDidNotSave();
		}
	}

	/**
	 * Controlla se tutti i campi sono stati compilati.
	 * 
	 * @param nuovoContatto
	 * @return
	 */
	@SuppressWarnings("unused")
	private boolean isComplete(Persona nuovoContatto) {
		if (nuovoContatto.getCognome() == null || nuovoContatto.getCognome().equals("")) {
			return false;
		} else if (nuovoContatto.getIndirizzo() == null || nuovoContatto.getIndirizzo().equals("")) {
			return false;
		} else if (nuovoContatto.getNome() == null || nuovoContatto.getNome().equals("")) {
			return false;
		} else if (nuovoContatto.getTelefono() == null || nuovoContatto.getTelefono().equals("")) {
			return false;
		} else if (nuovoContatto.getEta() < 1 || nuovoContatto.getEta() > 130) {
			return false;
		}

		return true;
	}
}
