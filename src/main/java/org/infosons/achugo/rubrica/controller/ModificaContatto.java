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

import org.infosons.achugo.rubrica.model.Persona;
import org.infosons.achugo.rubrica.persistence.GestioneContatti;

/**
 * @author Achugo Donald Emeka
 *
 * @version 2015.09.19.v1
 */
public class ModificaContatto {

	private GestioneContatti gc;

	private ModificaContatto() {
		gc = new GestioneContatti();
	}

	private static class SingletonHelper {
		private static final ModificaContatto INSTANCE = new ModificaContatto();
	}

	public static ModificaContatto getInstance() {
		return SingletonHelper.INSTANCE;
	}

	/**
	 * Modifica le informazioni di contatto già presnte nel sistema
	 * 
	 * @param contattoDaModificare
	 * @param nuovoContatto
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void modificaContatto(Persona contattoDaModificare, Persona nuovoContatto) throws FileNotFoundException, IOException {
		gc.modificaContatto(contattoDaModificare, nuovoContatto);
	}
}
