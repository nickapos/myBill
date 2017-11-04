/*
 *  Copyright (C) 2010 nickapos
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.oncrete.nick.mybill.BusinessLogic;

/**
 *
 * @author nickapos 6 Σεπ 2010
 */
public class AFMGenerator {

    CheckAFM u;

    /**
     *
     */
    public AFMGenerator() {

    }

    /**
     *
     * @param a
     * @param nomafm
     */
    public AFMGenerator(int a, int nomafm) {
        for (int i = a; i < a + nomafm; i++) {
            u = new CheckAFM("" + i);
            if (u.returnResult()) {
                System.out.println(u.getAfm());
            }
        }
    }

}
