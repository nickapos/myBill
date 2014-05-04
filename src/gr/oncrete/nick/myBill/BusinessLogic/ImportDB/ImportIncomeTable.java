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
package gr.oncrete.nick.myBill.BusinessLogic.ImportDB;

import java.util.ArrayList;
import java.util.Iterator;
import gr.oncrete.nick.myBill.BusinessLogic.InsertIncome;

/**
 *
 * @author nickapos
 */
public class ImportIncomeTable extends ImportFile {

    /**
     *
     */
    public ImportIncomeTable() {
        super.setFileName("incomeCsv.csv");

    }

    /**
     * method used to import the income csv to the database
     *
     */
    @Override
    public void importFileToDB() {
        ArrayList a = super.readFile();

        Iterator b = a.iterator();
        while (b.hasNext()) {
            String line = (String) b.next();
            if (line != null) {
                String[] splitLine = line.split(";");
                if (splitLine[0].length() > 0 && splitLine[1].length() > 0 && splitLine[2].length() > 0 && splitLine[3].length() > 0) {
                    System.out.println("Importing line " + line);
                    //see if splitLine[4] exists. if not catch the exception and replace the splitline[4] with a space character
                    try {
                        InsertIncome ins = new InsertIncome(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4]);
                        this.increaseRowsAffectedByOne();
                    } catch (java.lang.ArrayIndexOutOfBoundsException e) {
                        InsertIncome ins = new InsertIncome(splitLine[0], splitLine[1], splitLine[2], splitLine[3], " ");
                    }


                }

            }
        }
    }
}
