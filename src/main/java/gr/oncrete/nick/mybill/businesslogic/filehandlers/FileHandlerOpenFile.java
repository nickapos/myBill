/*
        myBill, bills tracking program
    Copyright (C) 2010  Nick Apostolakis

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
 /*
 * FileHandler.java
 *
 * Created on 6 Ιούλιος 2005, 8:06 πμ
 */
package gr.oncrete.nick.mybill.businesslogic.filehandlers;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class FileHandlerOpenFile {

    FileReader file;
    private final static Logger LOGGER = Logger.getLogger(FileHandlerOpenFile.class.getName());

    /**
     * Creates a new instance of FileHandler
     *
     * @param name
     */
    public FileHandlerOpenFile(String name) {
        try {
            file = new FileReader(name);
        }
        catch (FileNotFoundException fnf) {
            LOGGER.log(Level.SEVERE, "File Not Found");
            System.exit(1);
        }
    }

    /**
     * A method that closes the open File reader
     */
    public void closeFile() {
        try {
            file.close();
        }
        catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, "Tried to close the file but failed");
            System.exit(1);
        }
    }

    /**
     * This method returns the open file reader object
     *
     * @return This method returns the open file reader object
     */
    public FileReader getOpenFileReader() {
        return file;
    }

}
