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
package gr.oncrete.nick.myBill.BusinessLogic.SelectInfo;

import java.util.ArrayList;
import java.util.Iterator;
import gr.oncrete.nick.myBill.RDBMS.SelectFromTable;

/**
 *This class implements the dump database
 *operation.
 *
 * @author nickapos
 */
public class DumpDatabase {

    ArrayList<String> companies, bills, eforia, income, categories;
    String queryC = "SELECT cid,companyname,afm FROM companies where catid is null";
    String queryB = "SELECT * FROM bills";
    String queryD = "SELECT * FROM income";
    

    /** Creates a new instance of DumpDatabase */
    public DumpDatabase() {
        //this.readCompaniesTable();
        //this.readDBEforia();
    }

    /**
     * This method will call the apropriate method in order to form the
     * right query to export the the companies table
     * @return
     */
    public ArrayList getCompaniesCsv() {
        this.readCompaniesTable();
        return companies;
    }

    /**
     *
     * @return
     */
    public ArrayList getCategoriesCsv() {
        this.readCategoriesTable();
        return categories;
    }

    /**
     * This method will call the apropriate method in order to form the
     * right query to export the the income table
     * @return
     */
    public ArrayList getIncomeCsv() {
        this.readIncomeTable();
        return income;
    }

    /**
     * This method will call the apropriate method in order to form the
     * right query to export the the bills table
     * @return
     */
    public ArrayList getBillsCsv() {
        this.readBillsTable();
        return bills;
    }

    /**
     * This method will call the apropriate method in order to form the
     * right query to export the whole database as required by the eforia
     * @param year 
     * @return
     */
    public ArrayList getEforiaCsv(String year) {
        this.readDBEforia(year);
        return eforia;
    }

    /**
     * This method will call the apropriate method in order to form the
     * right query to export the whole database as required by the eforia for the selected period of time
     * @param year
     * @return
     */
    public ArrayList getRecordsForAPeriod(String startDate, String endDate) {
        this.readDBForAPeriod(startDate, endDate);
        return eforia;
    }

    /**
     *
     * This method will dump the companies table
     * as a csv in an arrayList
     *
     */
    private void readCompaniesTable() {
        SelectFromTable select = new SelectFromTable();

        ArrayList results = select.executeQuery(queryC);
        ArrayList results1 = select.executeQuery("SELECT * FROM companies where catid is not null");
        companies = new ArrayList();
        if (results.isEmpty() && results1.isEmpty()) {
            System.out.println("companies result is empty. Dump database operation aborted");
            System.exit(1);
        } else {

            if (!results.isEmpty()) {
                for (int i = 0; i < results.size(); i += 3) {
                    String row = "" + results.get(i) + ";" + results.get(i + 1) + ";" + results.get(i + 2) + ";" + "" + ";";
                    companies.add(row);
                    // System.out.println(row);
                }
            }
            if (!results1.isEmpty()) {
                for (int i = 0; i < results1.size(); i += 4) {
                    String row = "" + results1.get(i) + ";" + results1.get(i + 1) + ";" + results1.get(i + 2) + ";" + results1.get(i + 3) + ";";
                    companies.add(row);
                    // System.out.println(row);
                }
            }

        }
    }

    private void readCategoriesTable() {
        SelectFromTable select = new SelectFromTable();


        ArrayList results1 = select.executeQuery("SELECT * FROM categories");
        categories = new ArrayList();
        if (results1.isEmpty()) {
            System.out.println("categories result is empty. Dump database operation aborted");
            System.exit(1);
        } else {

            if (!results1.isEmpty()) {
                for (int i = 0; i < results1.size(); i += 2) {
                    String row = "" + results1.get(i) + ";" + results1.get(i + 1) + ";";
                    categories.add(row);
                    // System.out.println(row);
                }
            }

        }
    }

    /**
     *
     *
     * This method will dump the bills table
     * as a csv in an arrayList
     *
     *
     */
    private void readBillsTable() {
        SelectFromTable select = new SelectFromTable();

        ArrayList results = select.executeQuery(queryB);
        if (results.isEmpty()) {
            System.out.println("bills result is empty. Dump database operation aborted");
            System.exit(1);
        } else {
            bills = new ArrayList();
            for (int i = 0; i < results.size(); i += 6) {

                String row = "" + results.get(i) + ";" + results.get(i + 1) + ";" + results.get(i + 2) + ";" + results.get(i + 3) + ";"+results.get(i + 4) + ";"+results.get(i + 5) + ";";
                //System.out.println(row);
                bills.add(row);

            }
        }
    }

    /**
     *
     * this method will dump therecords of the db for one specific year
     * in one csv arraylist properly formated for the eforia
     */
    private void readDBEforia(String year) {
        if (year.length() != 0) {
            //if we are seeking the records for a specific year
            String startdate = year + "-01-01";
            String enddate = year + "-12-31";
            this.readDBForAPeriod(startdate, enddate);
        } else//if we are seeking all the records so the year string would be empty
        {
            this.readDBForAPeriod(year, year);
        }

    }

    /**
     *
     * this method will dump therecords of the db for one specific period
     * in one csv arraylist properly formated for the eforia
     */
    private void readDBForAPeriod(String startDate, String endDate) {
        String queryE = "SELECT a.bid,b.companyname,b.afm,a.price,a.dateofissue,a.dayofpayment,a.comment FROM bills a left join companies b on a.cid=b.cid";
        SelectFromTable select = new SelectFromTable();
        ArrayList results;
        if (startDate.length() == 0 && endDate.length() == 0) {
            results = select.executeQuery(queryE);
        } else {
            String queryWithYear = queryE + " where a.dayofpayment>='" + startDate + "' and a.dayofpayment<='" + endDate + "'";
            System.out.println(queryWithYear);
            results = select.executeQuery(queryWithYear);
        }
        if (results.isEmpty()) {
            System.out.println("result set is empty. Dump database operation aborted");
            System.exit(1);
        } else {
            eforia = new ArrayList();
            for (int i = 0; i < results.size(); i += 7) {
                //with ids
                //String row = "" + results.get(i) + ";" + results.get(i + 1) + ";" + results.get(i + 2) + ";" + results.get(i + 3) + ";" + results.get(i + 4) + ";"+results.get(i+5)+";"+results.get(i+6)+";"+results.get(i+7)+";";

                //without ids and dates
                //strin E from the amount  and replace . with ,
                String withoutE = results.get(i + 3).toString().replaceAll("E", "");
                String replaceComma = withoutE.replaceAll("\\.", ",");
                String row = "" + results.get(i + 1) + ";" + results.get(i + 2) + ";" + replaceComma+";"+results.get(i+6);
                eforia.add(row);
                //System.out.println(row);
            }
        }
    }

    /**
     *
     * This method will dump the income table
     * as a csv in an arrayList
     *
     */
    private void readIncomeTable() {
        SelectFromTable select = new SelectFromTable();

        ArrayList results = select.executeQuery(queryD);

        if (results.isEmpty()) {
            System.out.println("income result is empty. Dump database operation aborted");
            System.exit(1);
        } else {

            income = new ArrayList();
            for (int i = 0; i < results.size(); i += 5) {
                String row = "" + results.get(i) + ";" + results.get(i + 1) + ";" + results.get(i + 2) + ";" + results.get(i + 3) + ";" + results.get(i + 4) + ";";
                income.add(row);
                //System.out.println(row);
            }

        }
    }
}
