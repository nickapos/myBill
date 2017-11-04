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
package gr.oncrete.nick.mybill.businesslogic;

import gr.oncrete.nick.mybill.businesslogic.selectinfo.SelectCompanyDetails;
import gr.oncrete.nick.mybill.rdbms.InsertIntoTable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 *
 * This class is used to insert new bill entries into the database
 */
public class InsertBills {

    String sql = "";
    boolean dryRun = false;
    private final static Logger LOGGER = Logger.getLogger(InsertBills.class.getName());

    /**
     * empty constructor that can be used for decoupling class creation from
     * entry insertion
     */
    public InsertBills() {
    }

    /**
     * Constructor insert bill without an id
     *
     * @param cName
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     */
    public InsertBills(String cName, String price, String dateOfIssue, String dateOfPayment, String comment) {
        this.insertBillsWithoutId(cName, price, dateOfIssue, dateOfPayment, comment);

    }

    /**
     * Constructor insert bill without an id and with numerical company id
     *
     * @param cid
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     */
    public InsertBills(int cid, String price, String dateOfIssue, String dateOfPayment, String comment) {
        String sql = this.parseinsertBillsArgumentsWithoutId("" + cid, price, dateOfIssue, dateOfPayment, comment);
        this.commitToDB(sql);
        sql = sql;
    }

    /**
     * Wrapper of parse arguments without id, the only thing this method does is
     * to get the cid from the cname
     *
     * @param cName
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     */
    public void insertBillsWithoutId(String cName, String price, String dateOfIssue, String dateOfPayment, String comment) {
        if (cName.length() > 0 && price.length() > 0 && dateOfIssue.length() > 0 && dateOfPayment.length() > 0) {
            SelectCompanyDetails a = new SelectCompanyDetails();
            a.SelectCompanyDetailsWithName(cName);
            String cid = a.getID();
            String sql = this.parseinsertBillsArgumentsWithoutId(cid, price, dateOfIssue, dateOfPayment, comment);
            this.commitToDB(sql);
        }
        sql = sql;
    }

    /**
     * this method will be used by two constructors to create records without an
     * id and with company id either numerical or string
     *
     * @param cid
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     * @param comment
     */
    public String parseinsertBillsArgumentsWithoutId(String cid, String price, String dateOfIssue, String dateOfPayment, String comment) {
        String sql = "";
        if (cid.length() > 0 && price.length() > 0 && dateOfIssue.length() > 0 && dateOfPayment.length() > 0) {
            if (comment.length() > 0) {
                sql = "insert into bills (cid,price,dateofissue,dayofpayment,comment) values (" + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "','" + comment + "')";
            } else {
                sql = "insert into bills (cid,price,dateofissue,dayofpayment) values (" + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "')";
            }
            LOGGER.log(Level.INFO, sql);

        } else {
            sql = "";
        }
        return sql;
    }

    /**
     * /**
     * Constructor insert bill with a specific id used in restoring the database
     * from csv file format
     *
     * @param bid
     * @param cid
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     */
    public InsertBills(String bid, String cid, String price, String dateOfIssue, String dateOfPayment, String comment) {
        if (bid.length() > 0 && cid.length() > 0 && price.length() > 0 && dateOfIssue.length() > 0 && dateOfPayment.length() > 0) {
            String sql = "";
            if (comment.length() > 0) {
                sql = "insert into bills (bid,cid,price,dateofissue,dayofpayment,comment) values (" + bid + "," + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "','" + comment + "')";
            } else {
                sql = "insert into bills (bid,cid,price,dateofissue,dayofpayment) values (" + bid + "," + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "')";
            }
            this.commitToDB(sql);
        } else {
            this.commitToDB("");
        }
        sql = sql;
    }

    /**
     * This is the commit method. It has been separating in order to provide a
     * dry run option for testing purposes
     *
     * @param sql
     * @param dryRun
     */
    private void commitToDB(String sql) {
        LOGGER.log(Level.INFO, String.format("I am inserting: %s", sql));
        if (!dryRun) {
            InsertIntoTable in;
            if (sql.length() > 0) {

                in = new InsertIntoTable(sql);
            } else {
                in = new InsertIntoTable();
                in.warningPopUp(java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("ERROR IN BILL INSERTION"));
            }
        } else {
            LOGGER.log(Level.INFO, "This is a dry run");
        }
    }

    /**
     * this method can be used to turn dry run on for testing purposes.
     *
     * @param dr
     */
    public void setDyRun(boolean dr) {
        dryRun = dr;
    }

    public String toString() {
        return sql;
    }
}
