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
 * This class is used to insert new bill entries into the database
 */
public class InsertBills {

    boolean dryRun=false;
    
     
    
    /**
     * Constructor insert bill without an id
     *
     * @param cName
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     */
    public InsertBills(String cName, String price, String dateOfIssue, String dateOfPayment, String comment) {
        if (cName.length() > 0 && price.length() > 0 && dateOfIssue.length() > 0 && dateOfPayment.length() > 0) {
            SelectCompanyDetails a = new SelectCompanyDetails();
            a.SelectCompanyDetailsWithName(cName);
            String cid = a.getID();
            String sql = "";
            if (comment.length() > 0) {
                sql = "insert into bills (cid,price,dateofissue,dayofpayment,comment) values (" + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "','" + comment + "')";
            } else {
                sql = "insert into bills (cid,price,dateofissue,dayofpayment) values (" + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "')";
            }
            this.commitToDB(sql);
        } else {
            this.commitToDB("");
        }

    }
    
    /**
     * Constructor insert bill without an id and with numerical company id
     *
     * @param cName
     * @param price
     * @param dateOfIssue
     * @param dateOfPayment
     */
    public InsertBills(int cid, String price, String dateOfIssue, String dateOfPayment, String comment) {
        if (cid > 0 && price.length() > 0 && dateOfIssue.length() > 0 && dateOfPayment.length() > 0) {
            String sql = "";
            if (comment.length() > 0) {
                sql = "insert into bills (cid,price,dateofissue,dayofpayment,comment) values (" + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "','" + comment + "')";
            } else {
                sql = "insert into bills (cid,price,dateofissue,dayofpayment) values (" + cid + "," + price + ",'" + dateOfIssue + "','" + dateOfPayment + "')";
            }
            this.commitToDB(sql);
        } else {
            this.commitToDB("");
        }

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

    }

    /**
     * This is the commit method. It has been separating in order to provide a dry run option for testing purposes
     * @param sql
     * @param dryRun 
     */
    private void commitToDB(String sql) {
        if(dryRun){
        InsertIntoTable in;
        if (sql.length() > 0) {
            in = new InsertIntoTable(sql);
        } else {
            in = new InsertIntoTable();
            in.warningPopUp(java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("ERROR IN BILL INSERTION"));
        }
        }else
        {
            System.out.println("This is a dry run");
        }
    }
    /**
     * this method can be used to turn dry run on for testing purposes.
     * @param dr 
     */
    public void setDyRun(boolean dr){
        dryRun=dr;
    }
}
