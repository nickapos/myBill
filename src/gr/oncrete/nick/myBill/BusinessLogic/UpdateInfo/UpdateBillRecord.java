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
package gr.oncrete.nick.myBill.BusinessLogic.UpdateInfo;

import gr.oncrete.nick.myBill.BusinessLogic.SelectInfo.SelectCompanyDetails;

/**
 *
 * @author nickapos
 */
public class UpdateBillRecord extends UpdateRecord {

    String sql = "update bills set cid=";
    //update bills set cid=7, price=456, dateofissue='2010-04-25', dayofpayment='2010-12-05' where bid=7

    /**
     * empty constructor used when we want to use the alternative factory method instead of the normal constructor
     */
    public UpdateBillRecord() {
    }

    /**
     * when we do not have the company id and we have the company name we use this
     * @param bid
     * @param cName
     * @param price
     * @param dateOfissue
     * @param dayOfPayment
     */
    public UpdateBillRecord(String bid, String cName, String price, String dateOfissue, String dayOfPayment, String comment) {
        SelectCompanyDetails i = new SelectCompanyDetails();
        i.SelectCompanyDetailsWithName(cName);
        String sql1 = "";
        if (comment.length() > 0) {
            sql1 = sql + i.getID() + ", price=" + price + ", dateofissue='" + dateOfissue + "', dayofpayment='" + dayOfPayment + "', comment='" + comment + "' where bid=" + bid;
        } else {
            sql1 = sql + i.getID() + ", price=" + price + ", dateofissue='" + dateOfissue + "', dayofpayment='" + dayOfPayment + "' where bid=" + bid;
        }
        //System.out.println(sql1);
        super.runQuery(sql1);

    }

    /**
     * when we have the company id instead of the name we use this
     *
     * @param bid
     * @param cid
     * @param price
     * @param dateOfissue
     * @param dayOfPayment
     */
    public void updateBillRecordWithCid(String bid, String cid, String price, String dateOfissue, String dayOfPayment, String comment) {
        String sql1 = "";
        if (comment.length() > 0) {
            sql1 = sql + cid + ", price=" + price + ", dateofissue='" + dateOfissue + "', dayofpayment='" + dayOfPayment + "', comment='" + comment + "' where bid=" + bid;
        } else {
            sql1 = sql + cid + ", price=" + price + ", dateofissue='" + dateOfissue + "', dayofpayment='" + dayOfPayment + "' where bid=" + bid;
        }

        //System.out.println(sql1);
        super.runQuery(sql1);

    }
}
