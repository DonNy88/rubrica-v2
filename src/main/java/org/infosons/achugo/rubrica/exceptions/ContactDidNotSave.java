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
package org.infosons.achugo.rubrica.exceptions;

import java.io.IOException;

/**
 * @author Achugo Donald Emeka
 *
 * @version  2014.03.3.v1.1
 */
public class ContactDidNotSave extends IOException {

	private static final long serialVersionUID = -3638841870985270924L;
	private String type;

	public ContactDidNotSave() {
		type = getClass().getSimpleName();
	}

	public String getType() {
		return type;
	}
}
