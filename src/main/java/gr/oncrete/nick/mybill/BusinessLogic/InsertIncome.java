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
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gr.oncrete.nick.mybill.BusinessLogic;

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectCompanyDetails;
import gr.oncrete.nick.mybill.RDBMS.InsertIntoTable;

/**
 *
 * @author nickapos
 *
 * This class is used to insert new income entries into the database
 */
public class InsertIncome {

    InsertIntoTable in;

    /**
     * Constructor insert bill without an id
     *
     * @param cName
     * @param amount
     * @param dateOfPayment
     */
    public InsertIncome(String cName, String amount, String dateOfPayment, String comment) {
        if (cName.length() > 0 && amount.length() > 0 && dateOfPayment.length() > 0) {
            SelectCompanyDetails a = new SelectCompanyDetails();
            a.SelectCompanyDetailsWithName(cName);
            String cid = a.getID();
            String sql = "";
            if (comment.length() > 0) {
                sql = "insert into income (cid,amount,dateOfPayment,comment) values (" + cid + "," + amount + ",'" + dateOfPayment + "','" + comment + "')";
            } else {
                sql = "insert into income (cid,amount,dateOfPayment) values (" + cid + "," + amount + ",'" + dateOfPayment + "')";
            }

            in = new InsertIntoTable(sql);
            //System.out.println(sql);
        } else {
            in = new InsertIntoTable();
            in.warningPopUp(java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("ERROR IN INCOME INSERTION"));
        }

    }

    /**
     * /**
     * Constructor insert income with a specific id
     * used in restoring the database from csv file format
     *
     * @param inid
     * @param cid
     * @param amount
     * @param dateOfPayment
     */
    public InsertIncome(String inid, String cid, String amount, String dateOfPayment, String comment) {
        if (inid.length() > 0 && cid.length() > 0 && amount.length() > 0 && dateOfPayment.length() > 0) {
            String sql = "";
            if (comment.length() > 0) {
                sql = "insert into income (inid,cid,amount,dateofpayment,comment) values (" + inid + "," + cid + "," + amount + ",'" + dateOfPayment + "','" + comment + "')";
            } else {
                sql = "insert into income (inid,cid,amount,dateofpayment) values (" + inid + "," + cid + "," + amount + ",'" + dateOfPayment + "')";
            }

            in = new InsertIntoTable(sql);
            //System.out.println(sql);
        } else {
            in = new InsertIntoTable();
            in.warningPopUp(java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("ERROR IN INCOME INSERTION"));
        }

    }
}
