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
package gr.oncrete.nick.mybill.UserInterface;

/**
 *
 * @author nickapos
 */
/**
 * table model class used for the exchange rate tables
 *
 *
 */
class ImportBankStatementTableModel extends javax.swing.table.DefaultTableModel {

    //designate one column to contain booleans
    int booleanColumn = 8;

    public ImportBankStatementTableModel(String[][] d, String[] cName) {
        super(d, cName);
    }

    public ImportBankStatementTableModel(Object[][] d, String[] cName) {
        super(d, cName);
    }

    public ImportBankStatementTableModel(Object[][] d, String[] cName, int boolColumn) {
        super(d, cName);
        booleanColumn = boolColumn;
    }

    @Override
    public Class getColumnClass(int c) {
        if (c == booleanColumn) {
            return java.lang.Boolean.class;
        } else {
            return java.lang.String.class;
        }
    }

    

    private boolean[] getcanEditArr(int sizeOfArr, int editPos) {
        boolean[] canEdit = new boolean[sizeOfArr];
        for (int i = 0; i < sizeOfArr; i++) {
            if (i == editPos && editPos < sizeOfArr && editPos >= 0) {
                canEdit[i] = true;
            } else {
                canEdit[i] = false;
            }
        }
        return canEdit;
    }

    /**
     * by overriding this method we can set the table cells editable or not
     *
     * @param row
     * @param column
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        boolean[] canEdit=this.getcanEditArr(9, 8);
        return canEdit[column];
    }
}
