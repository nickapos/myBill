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

import com.opencsv.CSVReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 *
 * @author nickapos
 */
public class ParseCsv {

    protected String fileName = "";
    protected int numberOfFields = 1;

    private String[] header = {};

    public ParseCsv() {

    }

    public ParseCsv(int fields) {
        this.setNumberOfFields(fields);
    }

    /**
     * This method will parse the given file into an arrayList of string lists.
     *
     * @param file
     * @param delim
     * @return 
     */
    public ArrayList<ArrayList> parseData(String file, char delim) {
        ArrayList<ArrayList> content = new ArrayList();
        fileName = file;
        BufferedReader br = null;
        try {
            CSVReader reader = new CSVReader(new FileReader(fileName),delim);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                content.add(new ArrayList( Arrays.asList(nextLine)));
            }

        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                }
            }
        }
        return content;
    }

    public void setNumberOfFields(int fields) {
        numberOfFields = fields;
    }

    public int getNumOfFields() {
        return numberOfFields;
    }

    public String[] getColumnNames() {
        return header;
    }

    protected HashMap<String, Integer> initializeMonNumMap() {
        HashMap<String, Integer> monthNumMap;
        monthNumMap = new HashMap();
        monthNumMap.put("Jan", 1);
        monthNumMap.put("Feb", 2);
        monthNumMap.put("Mar", 3);
        monthNumMap.put("Apr", 4);
        monthNumMap.put("May", 5);
        monthNumMap.put("Jun", 6);
        monthNumMap.put("Jul", 7);
        monthNumMap.put("Aug", 8);
        monthNumMap.put("Sep", 9);
        monthNumMap.put("Oct", 10);
        monthNumMap.put("Nov", 11);
        monthNumMap.put("Dec", 12);
        return monthNumMap;
    }
}
