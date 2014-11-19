/*
 *  Copyright (C) 2011 nickapos
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
package gr.oncrete.nick.myBill.UserInterface;

/**
 *
 * @author nickapos
 */
/**
 * table model class used for the exchange rate tables
 *
 *
 */
class ExchangeRatesTableModel extends MyTableModel {


    public ExchangeRatesTableModel(String[][] d, String[] cName) {
       super (d,cName);
    }

    public ExchangeRatesTableModel(Object[][] d, String[] cName) {
         super (d,cName);
    }

    /**
     * by overriding this method we can set the table cells editable or not
     * @param row
     * @param column
     * @return 
     */
    public boolean isCellEditable(int row, int column) {
        return true;
    }

}
