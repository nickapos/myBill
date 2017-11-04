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
package gr.oncrete.nick.mybill.BusinessLogic.ImportDB;

import gr.oncrete.nick.mybill.BusinessLogic.InsertBills;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author nickapos
 */
public class ImportBillsTable extends ImportFile {

    /**
     *
     */
    public ImportBillsTable() {
        super.setFileName("billsCsv.csv");
    }

    /**
     * method used to import the bills csv to the database
     */
    @Override
    public void importFileToDB() {
        ArrayList a = super.readFile();

        Iterator b = a.iterator();
        while (b.hasNext()) {
            String line = (String) b.next();
            if (line != null) {
                String[] splitLine = line.split(";");
                if (splitLine[0].length() > 0 && splitLine[1].length() > 0 && splitLine[2].length() > 0 && splitLine[3].length() > 0 && splitLine[4].length() > 0) {
                    System.out.println("Importing bills line " + line);
                    //see if splitLine[5] exists. if not catch the exception and replace the splitline[5] with a space character
                    try {
                        InsertBills ins = new InsertBills(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4], splitLine[5]);
                        ins.toString();
                        this.increaseRowsAffectedByOne();

                    }
                    catch (java.lang.ArrayIndexOutOfBoundsException e) {
                        InsertBills ins = new InsertBills(splitLine[0], splitLine[1], splitLine[2], splitLine[3], splitLine[4], " ");
                        ins.toString();
                    }

                }

            }
        }
    }
}
