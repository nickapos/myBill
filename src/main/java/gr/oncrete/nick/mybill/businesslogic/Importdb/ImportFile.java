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
package gr.oncrete.nick.mybill.businesslogic.Importdb;

import gr.oncrete.nick.mybill.businesslogic.filehandlers.FileHandlerReadFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class ImportFile {

    private String fileName;//the file name to be imported
    private FileHandlerReadFile reader;
    private int rowsAffected = 0;
    private final static Logger LOGGER = Logger.getLogger(ImportFile.class.getName());

    /**
     *
     */
    public ImportFile() {

    }

    /**
     *
     * @param fname
     */
    public void setFileName(String fname) {
        fileName = fname;
    }

    /**
     * this method will return the rows affected from an import operation
     *
     * @return
     */
    public int getRowsAffected() {
        return rowsAffected;
    }

    /**
     * this method will increase the rows affected by one
     */
    public void increaseRowsAffectedByOne() {
        rowsAffected++;
    }

    /**
     *
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * this method will read the file provided by filename and return its
     * contents in an arraylist
     *
     *
     * @return the contents of the file read
     */
    protected ArrayList<String> readFile() {
        reader = new FileHandlerReadFile(fileName);
        reader.readFile();
        return reader.returnContents();
    }

    /**
     *
     */
    public void importFileToDB() {
        ArrayList a = this.readFile();

        Iterator b = a.iterator();
        while (b.hasNext() && b.next() != null) {
            LOGGER.log(Level.INFO, b.next().toString());
        }
    }

}
