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
 * this class will be used to retrieve all the details available for an income record
 */
public class SelectIncomeDetails {

    private String inid, amount,dateofpayment, cid,comment;
    private boolean exists = false;
    private String sql1 = "select inid,cid,amount,dateofpayment,comment from income where";
    private SelectFromTable sel = new SelectFromTable();

    /**
     *
     */
    public SelectIncomeDetails() {
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
        String sql = sql1 + " inid=" + id + ";";
        this.splitResults(sql);
    }

    private void splitResults(String sql) {
        //System.out.println(sql);
        ArrayList<String> a = sel.executeQuery(sql);
        
        if (a.size() > 0) {
            exists = true;
            inid = a.get(0);
            cid = a.get(1);
            amount = a.get(2);
            dateofpayment = a.get(3);
            comment = a.get(4);
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
    public String getInID() {
        return inid;
    }

    /**
     *
     * @return
     */
    public String getAmount() {
        return amount;
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
