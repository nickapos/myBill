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

import gr.oncrete.nick.mybill.BusinessLogic.InsertCategory;
import gr.oncrete.nick.mybill.BusinessLogic.ShutdownDB;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class ImportCategoriesTable extends ImportFile {
private final static Logger LOGGER = Logger.getLogger(ImportCategoriesTable.class.getName());
    /**
     *
     */
    public ImportCategoriesTable() {
        super.setFileName("categoriesCsv.csv");

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
                if (splitLine[0].length() > 0 && splitLine[1].length() > 0) {
                    LOGGER.log(Level.INFO, String.format("Importing line %s",line));
                    InsertCategory ins = new InsertCategory(splitLine[0], splitLine[1]);
                    ins.toString();
                    this.increaseRowsAffectedByOne();
                }

            }
        }
    }
}
