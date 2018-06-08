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
import java.util.HashMap;

/**
 *
 * @author nickapos
 */
public class ParseCsv {

    protected String fileName = "";
    protected String delimiter = ",";
    protected int numberOfFields = 1;
    
    private String[] header= {};
    

    public ParseCsv() {

    }

    public ParseCsv( int fields) {
        this.setNumberOfFields(fields);
    }

    public ParseCsv( String delim, int fields) {
        this.setDelimiter(delim);
        this.setNumberOfFields(fields);
    }

    /**
     * This method will parse the given file into an arrayList of string lists.
     *
     * @param file
     */
    public ArrayList<ArrayList> parseData(String file) {
        ArrayList<ArrayList> content = new ArrayList();
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
                            + numberOfFields);
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
        return content;
    }

    public void setDelimiter(String delim) {
        delimiter = delim;
    }

    public void setNumberOfFields(int fields) {
        numberOfFields = fields;
    }

   

    public int getNumOfFields() {
        return numberOfFields;
    }
    
    public String[] getColumnNames()
    {
        return header;
    }
    
    protected HashMap<String, Integer> initializeMonNumMap(){
        HashMap<String, Integer> monthNumMap = new HashMap<String, Integer>();
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
