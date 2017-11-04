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
 * this class will retrieve the expenses per category from the database for two given years, and compare them
 * 
 */
package gr.oncrete.nick.mybill.BusinessLogic.SelectInfo;

import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author nickapos
 */
public class CompareExpensesPerCategoryForTwoYears extends SelectReportExpensesPerCategoryForAYear {

    String sql1 = "select a.catid,sum(c.price),a.categoryname from categories a, companies b, bills c where a.catid=b.catid and b.cid=c.cid and c.dayofpayment>='";
    String sql2 = " group by a.categoryname,a.catid order by a.categoryname;";
    List<String> firstYearResults, secondYearResults;
    Hashtable<String, String> firstYearIDPrice = new Hashtable(), firstYearIDName = new Hashtable(), secondYearIDPrice = new Hashtable(), secondYearIDName = new Hashtable();

    /**
     * ths constructor will accept the startyear and the end year. it will
     * select the data from the database and accept the result in two lists. the
     * lists will be processed later in order to produce two comparable hash
     * tables for each year
     *
     * @param startYear,endYear
     */
    public CompareExpensesPerCategoryForTwoYears(String startYear, String endYear) {
        if (startYear.length() > 0) {
            String sqlS = sql1 + startYear + "-01-01' and c.dayofpayment<='" + startYear + "-12-31' " + sql2;
            super.runQuery(sqlS);
            //System.out.println(sqlS);
            firstYearResults = this.getIds();
            this.parseResults(firstYearResults, firstYearIDName, firstYearIDPrice);
            String sqlE = sql1 + endYear + "-01-01' and c.dayofpayment<='" + endYear + "-12-31' " + sql2;
            super.runQuery(sqlE);
            secondYearResults = this.getIds();
            this.parseResults(secondYearResults, secondYearIDName, secondYearIDPrice);

        }
    }

    /**
     * this method will parse the incoming datalist and returl two hash tables.
     * one containing ids and category names and the second containing ids and
     * prices.
     *
     * @param incomingData
     * @param idname
     * @param idprice
     */
    private void parseResults(List incomingData, Hashtable idname, Hashtable idprice) {

        if (incomingData.isEmpty()) {
            idname = null;
            idprice = null;
        } else {
            for (int i = 0; i < incomingData.size(); i += 3) {

                //fill the hash table with the relation id-price
                idprice.put(incomingData.get(i), incomingData.get(i + 1));
                //fill the hash table with the relation id-name
                idname.put(incomingData.get(i), incomingData.get(i + 2));
            }

        }

    }

    /**
     * This method returns the properly formated data from the query in order to
     * fit in the jtable
     *
     *
     * @return
     */
    public String[][] getColumns() {

        if (firstYearIDPrice != null && secondYearIDPrice != null && firstYearIDName != null && secondYearIDName != null) {
            //get the categories ids from the first year
            Set firstYearIDs = firstYearIDPrice.keySet();
            //get the categories ids from the second year
            Set secondYearIDs = secondYearIDPrice.keySet();
            //concatenate the two sets keeping the first one. this set will contain all the ids in our dataset and will be used as a reference
            TreeSet wholeIdSet = new TreeSet();
            wholeIdSet.addAll(firstYearIDs);
            wholeIdSet.addAll(secondYearIDs);

            String[][] array = new String[wholeIdSet.size()][4];

            //fill the result jtable without the percentage
            Iterator<String> it = wholeIdSet.iterator();
            int i = 0;
            while (it.hasNext()) {
                String id = it.next();
                //the name of the category
                String catName = "";
                //the value of the first year and second year. initialized to 0. if the value exists in our set we update it.
                double firstYearValue = 0;
                double secondYearValue = 0;

                if (firstYearIDs.contains(id)) {
                    catName = firstYearIDName.get(id);
                    firstYearValue = Double.parseDouble(firstYearIDPrice.get(id));
                }
                if (secondYearIDs.contains(id)) {
                    catName = secondYearIDName.get(id);
                    secondYearValue = Double.parseDouble(secondYearIDPrice.get(id));
                }
                if (firstYearValue != 0 && secondYearValue != 0 && firstYearValue >= secondYearValue) {
                    String[] row = {catName, "" + (new DecimalFormat("###,###.##").format(firstYearValue)), (new DecimalFormat("##,###.##").format(secondYearValue)), (new DecimalFormat("#,###.##").format(100 * (firstYearValue - secondYearValue) / firstYearValue)) + "%"};
                    array[i] = row;
                } else if (firstYearValue != 0 && secondYearValue != 0 && firstYearValue <= secondYearValue) {
                    String[] row = {catName, "" + (new DecimalFormat("###,###.##").format(firstYearValue)), (new DecimalFormat("##,###.##").format(secondYearValue)), "-" + (new DecimalFormat("#,###.##").format(100 * (secondYearValue - firstYearValue) / secondYearValue)) + "%"};
                    array[i] = row;
                } else {
                    String[] row = {catName, "" + (new DecimalFormat("###,###.##").format(firstYearValue)), (new DecimalFormat("##,###.##").format(secondYearValue)), "-"};
                    array[i] = row;
                }

                i++;
            }
            return array;

        } else {
            String[][] array = new String[1][4];
            String[] row = {"", "", "", ""};
            array[0] = row;
            return array;
        }

    }
}
