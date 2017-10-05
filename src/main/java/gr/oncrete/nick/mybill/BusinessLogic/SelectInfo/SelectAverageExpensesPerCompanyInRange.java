/*
 * Copyright (C) 2017 nickapos
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
package gr.oncrete.nick.mybill.BusinessLogic.SelectInfo;

import gr.oncrete.nick.mybill.RDBMS.SelectFromTable;
import java.util.*;

/**
 * This class will perform two functions. It will return the average expenses
 * for a company over a period of time, it will also return the number of
 * records for that company over the same period of time. It will accept the
 * startPeriod and endPeriod as arguments
 *
 * @author nickapos
 */
public class SelectAverageExpensesPerCompanyInRange {

    private String companyName, numberOfRecords, totalAmount, avPrice, dateofPayment, cid;
    private String sql1 = "select a,cid,b.companyname,count(b.companyname) as numberOfRecords,sum(a.price) as"
            + "total, avg(a.price) "
            + "from bills a, companies b "
            + "where a.cid=b.cid ";
    private String sql2 = "and a.dayofpayment >='";
    private String sql3 = "and a.dayofpayment <= '";
    private String sql4 = "group by b.companyname";
    private SelectFromTable sel = new SelectFromTable();

    public SelectAverageExpensesPerCompanyInRange() {

    }

    public SelectAverageExpensesPerCompanyInRange(String startPeriod, String endPeriod) {
        String sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4;
        this.splitResults(sql);
    }

    private void splitResults(String sql) {
        //System.out.println(sql);
        ArrayList<String> a = sel.executeQuery(sql);

        if (a.size() > 0) {
            cid = a.get(0);
            companyName = a.get(1);
            numberOfRecords = a.get(2);
            totalAmount = a.get(3);
            avPrice = a.get(4);
            dateofPayment = a.get(5);
            //System.out.println(id+name+afm);
        }
    }

    public String getCid() {
        return cid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getNumberOfRecords() {
        return numberOfRecords;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public String getAvPrice() {
        return avPrice;
    }

    public String getDateofPayment() {
        return dateofPayment;
    }

    public String toString() {
        return "Cid"+this.getCid()+" CompanyName:"+this.getCompanyName()+ 
                " TotalAmount:"+this.totalAmount+" AveragePrice:"+this.avPrice+
                " DateOfPayment:"+this.dateofPayment;
    }
}
