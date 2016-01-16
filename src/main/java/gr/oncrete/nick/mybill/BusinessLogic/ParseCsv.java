/*
 * Copyright (C) 2016 nickapos
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package gr.oncrete.nick.mybill.BusinessLogic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author nickapos
 */
public class ParseCsv {

    protected String fileName = "";
    protected String delimiter = ",";
    protected int numberOfFields = 1;
    ArrayList<ArrayList> content = new ArrayList();

    public ParseCsv() {

    }

    public ParseCsv(String file,int fields) {
        this.setNumberOfFields(fields);
        this.setFileName(file);
    }

    public ParseCsv(String file, String delim, int fields) {
        this.setDelimiter(delim);
        this.setNumberOfFields(fields);
        this.setFileName(file);
    }

    /**
     * This method will parse the given file into an arrayList of string lists.
     *
     * @param file
     */
    public void setFileName(String file) {
        fileName = file;
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {

                String[] lineArr = line.split(delimiter);
                ArrayList<String> lineContent = new ArrayList<String>(Arrays.asList(lineArr));
                if (lineContent.size() != numberOfFields) {
                    System.out.println("Received a line with more fields than expected. I am going to ignore it. Expected number of fields:"
                            +numberOfFields);
                    System.out.println("Line contents: " + lineContent.toString());
                } else {
                    content.add(lineContent);
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setDelimiter(String delim) {
        delimiter = delim;
    }

    public void setNumberOfFields(int fields) {
        numberOfFields = fields;
    }

    public ArrayList<ArrayList> getContent() {
        return content;
    }

}
