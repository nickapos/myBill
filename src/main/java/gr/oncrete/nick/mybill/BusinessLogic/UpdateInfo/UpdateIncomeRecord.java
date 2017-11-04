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
package gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo;

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectCompanyDetails;

/**
 *
 * @author nickapos
 */
public class UpdateIncomeRecord extends UpdateRecord {

    String sql = "update income set cid='";
    //update companies set companyname='test2', afm='blabla' where cid=2

    /**
     *
     * @param inid
     * @param companyName
     * @param amount
     * @param dateofpayment
     */
    public UpdateIncomeRecord(String inid, String companyName, String amount, String dateofpayment, String comment) {
        if (inid.length() > 0 && companyName.length() > 0 && amount.length() > 0 && dateofpayment.length() > 0) {
            SelectCompanyDetails i = new SelectCompanyDetails();
            i.SelectCompanyDetailsWithName(companyName);
            String sql1 = "";
            if (comment.length() > 0) {
                sql1 = sql + i.getID() + "', amount=" + amount + ", dateofpayment='" + dateofpayment + "', comment='" + comment + "' where inid=" + inid;
            } else {
                sql1 = sql + i.getID() + "', amount=" + amount + ", dateofpayment='" + dateofpayment + "' where inid=" + inid;
            }
            super.runQuery(sql1);
        }
    }

    /**
     *
     * @param inid
     * @param companyID
     * @param amount
     * @param dateofpayment
     */
    public UpdateIncomeRecord(String inid, int companyID, String amount, String dateofpayment, String comment) {
        if (inid.length() > 0 && companyID >= 0 && amount.length() > 0 && dateofpayment.length() > 0) {
            String sql1 = "";
            if (comment.length() > 0) {
                sql1 = sql + companyID + "', amount=" + amount + ", dateofpayment='" + dateofpayment + "', comment='" + comment + "' where inid=" + inid;
            } else {
                sql1 = sql + companyID + "', amount=" + amount + ", dateofpayment='" + dateofpayment + "' where inid=" + inid;
            }
            super.runQuery(sql1);
        }
    }
}
