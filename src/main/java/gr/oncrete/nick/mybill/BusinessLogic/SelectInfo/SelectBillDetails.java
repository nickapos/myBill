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
package gr.oncrete.nick.mybill.BusinessLogic.SelectInfo;

import gr.oncrete.nick.mybill.RDBMS.SelectFromTable;
import java.util.*;

/**
 *
 * @author nickapos
 * this class will be used to retrieve all the details available for a bill
 */
public class SelectBillDetails {

    private String bid, price, dateofissue, dateofpayment, cid,comment;
    private boolean exists = false;
    private String sql1 = "select bid,cid,price,dateofissue,dayofpayment,comment from bills where";
    private SelectFromTable sel = new SelectFromTable();

    /**
     *
     */
    public SelectBillDetails() {
    }

    /*
    public void SelectCompanyDetailsWithName(String n) {
    String sql = sql1 + " companyname='" + n + "';";
    this.splitResults(sql);
    }

    public void SelectCompanyDetailsWithAfm(String a) {
    String sql = sql1 + " afm='" + a + "';";
    this.splitResults(sql);
    }
     */
    /**
     *
     * @param id
     */
    public void SelectBillDetailsWithID(String id) {
        String sql = sql1 + " bid=" + id + ";";
        this.splitResults(sql);
    }

    private void splitResults(String sql) {
        //System.out.println(sql);
        ArrayList<String> a = sel.executeQuery(sql);
        
        if (a.size() > 0) {
            exists = true;
            bid = a.get(0);
            cid = a.get(1);
            price = a.get(2);
            dateofissue = a.get(3);
            dateofpayment = a.get(4);
            comment = a.get(5);
            //System.out.println(id+name+afm);
        }
    }

    /**
     *
     * @return
     */
    public String getCID() {
        return cid;
    }

    /**
     *
     * @return
     */
    public String getBID() {
        return bid;
    }

    /**
     *
     * @return
     */
    public String getPrice() {
        return price;
    }
    /**
     *
     * @return
     */
    public String getDateOfIssue() {
        return dateofissue;
    }
    /**
     *
     * @return
     */
    public String getDateOfPayment() {
        return dateofpayment;
    }

    public String getComment() {
        return comment;
    }

    /**
     *
     * @return
     */
    public boolean resultsExist() {
        return exists;
    }
}
