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

import gr.oncrete.nick.mybill.BusinessLogic.InsertCompany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class ImportCompaniesTable extends ImportFile {

    private final static Logger LOGGER = Logger.getLogger(ImportCompaniesTable.class.getName());

    /**
     *
     */
    public ImportCompaniesTable() {
        super.setFileName("companiesCsv.csv");

    }

    /**
     * method used to import the companies csv to the database
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
                if (splitLine.length == 3 && splitLine[0].length() > 0 && splitLine[1].length() > 0 && splitLine[2].length() > 0) {
                    LOGGER.log(Level.INFO, String.format("Importing line %s", line));
                    InsertCompany ins = new InsertCompany(splitLine[0], splitLine[1], splitLine[2]);
                    LOGGER.log(Level.INFO, ins.toString());
                    this.increaseRowsAffectedByOne();

                } else if (splitLine.length == 4 && splitLine[0].length() > 0 && splitLine[1].length() > 0 && splitLine[2].length() > 0 && splitLine[3].length() > 0) {
                    LOGGER.log(Level.INFO, String.format("Importing line %s", line));
                    InsertCompany ins = new InsertCompany(splitLine[0], splitLine[1], splitLine[2], splitLine[3]);
                    LOGGER.log(Level.INFO, ins.toString());
                    this.increaseRowsAffectedByOne();

                }

            }
        }
    }
}
