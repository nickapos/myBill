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
 * DumpDatabase.java
 *
 * Created on 21 Ιούλιος 2005, 9:39 πμ
 */
package gr.oncrete.nick.mybill.BusinessLogic.SelectInfo;

import gr.oncrete.nick.mybill.RDBMS.SelectFromTable;
import java.util.ArrayList;

/**
 * This class implements the dump database operation.
 *
 * @author nickapos
 */
public class DumpDatabaseinQIF {

    final static String QIFHEADER = "!Type:Cash";

    /**
     * Creates a new instance of DumpDatabase
     */
    public DumpDatabaseinQIF() {
        //this.readCompaniesTable();
        //this.readDBEforia();
    }

    /**
     * This method will call the appropriate method in order to form the right
     * query to export the the bills table
     *
     * @return
     */
    public ArrayList getBillsQIF() {
        String billQuery = "SELECT b.categoryname,c.companyname,a.price,a.dayofpayment,a.comment FROM bills a, categories b,companies c where a.cid=c.cid and b.catid=c.catid";
        return this.readTable(billQuery, true);
    }

    /**
     * This method will call the appropriate method in order to form the right
     * query to export the the bills table
     *
     * @return
     */
    public ArrayList getIncomesQIF() {
        String incomeQuery = "SELECT b.categoryname,c.companyname,a.amount,a.dateofpayment,a.comment FROM income a, categories b,companies c where a.cid=c.cid and b.catid=c.catid";
        return this.readTable(incomeQuery, false);
    }

    /**
     *
     *
     * This method will dump the bills table as a qif in an arrayList
     *
     * The expense argument will add a - symbol in the amount field if true
     *
     */
    private ArrayList readTable(String query, boolean expense) {
        ArrayList<String> qifRecords = new ArrayList();
        qifRecords.add(QIFHEADER);
        SelectFromTable select = new SelectFromTable();

        ArrayList results = select.executeQuery(query);
        if (results.isEmpty()) {
            System.out.println("result set is empty. Dump database operation aborted");
            System.exit(1);
        } else {

            for (int i = 0; i < results.size(); i += 5) {
                String category = (String) results.get(i);
                String company = (String) results.get(i + 1);
                String amount;
                if (expense) {
                    amount = "-" + results.get(i + 2);
                } else {
                    amount = (String) results.get(i + 2);
                }
                String date = (String) results.get(i + 3);
                String comment = (String) results.get(i + 4);
                /* System.out.println("Category:" + category);
                 System.out.println("company:" + company);
                 System.out.println("amount:" + amount);
                 System.out.println("date:" + date);
                 System.out.println("comment:" + comment);
                 System.out.println("");*/
                String qifRow = convertRecordToQIF(category, company, amount, date, comment);
                System.out.println(qifRow);
                qifRecords.add(qifRow);
                //System.out.println(results.get(i+1));

            }
        }
        return qifRecords;
    }

    /**
     * This little method will accept the arguments and return a properly QIF
     * formatted string
     *
     * @param category
     * @param company
     * @param amount
     * @param date
     * @param comment
     * @return
     */
    public String convertRecordToQIF(String category, String company, String amount, String date, String comment) {
        return "D" + this.qifDateConvert(date)+ "\n" + "T" + amount.replace("E0","") + "\n" + "P" + company + "\n" + "^";
    }
    
    /**
     * this method will convert a date from YYYY-MM-DD to MM/DD/YYYY
     * @param date
     * @return 
     */
    private String qifDateConvert(String date){
        String[] dateparts=date.split("-");
        //System.out.println("length "+dateparts.length+" 0: "+dateparts[0]);
        return ""+dateparts[1]+"/"+dateparts[2]+"/"+dateparts[0];
    }

}
