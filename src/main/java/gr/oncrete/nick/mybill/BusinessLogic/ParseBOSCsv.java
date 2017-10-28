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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author nickapos
 */
public class ParseBOSCsv extends ParseCsv {

    //tsb has 8 fields in its csv

    static final int numOfFields = 8;
    private String[] header = {"Transaction Date", "Transaction Type", "Sort Code", "Account Number", "Transaction Description", "Debit Amount", "Credit Amount", "Balance", "Import"};

    public ParseBOSCsv() {
        super(numOfFields);

    }

    public ParseBOSCsv(int fields) {
        super(fields);

    }

    @Override
    public int getNumOfFields() {
        return numOfFields;
    }

    @Override
    public String[] getColumnNames() {
        return header;
    }

    /**
     * This method is a wrapper around parseData. It will convert the three
     * letter month to its number and remove negative values from withdrawals
     *
     * @param file
     * @return
     */
    public ArrayList<ArrayList> filterMonthsAndNegValues(String file) {
        ArrayList content = this.parseData(file);
        ArrayList<ArrayList> correctedContent = new ArrayList();
        Iterator it = content.iterator();
        while (it.hasNext()) {
            Object recordO = it.next();
            ArrayList record = (ArrayList) recordO;
            //System.out.println(record.toString());
            String dateUnc = (String) record.get(0);
            System.out.println(dateUnc);
            //correct date and replace the value in the array list   
            String withdrUnc = (String) record.get(5);
            String depUnc = (String) record.get(6);
            record.set(6, depUnc.replace("-", ""));
            ArrayList<String> newRow = new ArrayList<String>();
            newRow.add(this.convertDate(dateUnc));
            newRow.add((String) record.get(1));
            newRow.add((String) record.get(2));
            newRow.add((String) record.get(3));
            newRow.add((String) record.get(4));
            newRow.add(withdrUnc.replace("-", ""));
            newRow.add(depUnc.replace("-", ""));
            newRow.add((String) record.get(7));
            
            correctedContent.add(newRow);

        }

        return correctedContent;

    }

    /**
     * take dates of the format 01/Jun/2016 and return 01/6/2016
     *
     * @param dateUnc
     * @return
     */
    public String convertDate(String dateUnc) {
        HashMap<String, Integer> map = this.initializeMonNumMap();
        String[] dateParts = dateUnc.split("/");
        if (dateParts.length < 3 || this.isInteger(dateParts[1]) ) {
            return dateUnc;
        } else {
            return "" + dateParts[0] + "/" + map.get(dateParts[1]) + "/" + dateParts[2];
        }

    }
    public boolean isInteger(String intStr){
        if(!intStr.isEmpty() &&intStr.matches("^-?\\d+$")) 
            return true;
        return false;
    }
}
