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
import com.google.common.collect.Lists;
import java.text.DecimalFormat;
import java.util.stream.Collectors;

/**
 * This class will perform two functions. It will return the average expenses
 * for a company over a period of time, it will also return the number of
 * records for that company over the same period of time. It will accept the
 * startPeriod and endPeriod as arguments
 *
 * @author nickapos
 */
public class SelectAverageExpensesPerCompanyInRange {

    private List<AnalyticsRecord> analyticsRecordList = new <AnalyticsRecord> ArrayList();
    private String sql1 = "select b.cid,b.companyname,count(b.companyname) as numberOfRecords,sum(a.price) as "
            + "total, avg(a.price) as average "
            + "from bills a, companies b "
            + "where a.cid=b.cid ";
    private String sql2 = " and a.dayofpayment>='";
    private String sql3 = " and a.dayofpayment<= '";
    private String sql4 = " group by b.companyname,b.cid order by numberOfRecords desc";
    private SelectFromTable sel = new SelectFromTable();

    public SelectAverageExpensesPerCompanyInRange() {

    }

    public SelectAverageExpensesPerCompanyInRange(String startPeriod, String endPeriod) {
        String sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4;
        this.splitResults(sql);
    }

    private void splitResults(String sql) {
        //System.out.println(sql);
        List<String> a = sel.executeQuery(sql);
        List<List<String>> partitionedList = Lists.partition(a, 5);
        analyticsRecordList = partitionedList.stream().map(n -> new AnalyticsRecord(n.get(0), n.get(1), n.get(2), n.get(3), n.get(4))).collect(Collectors.toList());
    }

    public String toString() {
        String results = analyticsRecordList.stream().map(n -> n.toString()).collect(Collectors.joining("\n"));
        System.out.println(results);
        return results;
    }

    public List getAnalyticsRecordList() {
        return analyticsRecordList;
    }

    private class AnalyticsRecord {

        public String companyName, numberOfRecords, totalAmount, avPrice, dateofPayment, cid;

        public AnalyticsRecord(String cid, String companyName, String numberOfRecords, String totalAmount, String avPrice) {
            this.companyName = companyName;
            this.numberOfRecords = numberOfRecords;
            this.totalAmount = totalAmount;
            this.avPrice = avPrice;
            this.cid = cid;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getNumberOfRecords() {
            return numberOfRecords;
        }

        public void setNumberOfRecords(String numberOfRecords) {
            this.numberOfRecords = numberOfRecords;
        }

        public String getTotalAmount() {
            return new DecimalFormat("####.##").format(Double.valueOf(totalAmount));
        }

        public void setTotalAmount(String totalAmount) {
            this.totalAmount = totalAmount;
        }

        public String getAvPrice() {
            return new DecimalFormat("####.##").format(Double.valueOf(avPrice));
        }

        public void setAvPrice(String avPrice) {
            this.avPrice = avPrice;
        }

        public String getDateofPayment() {
            return dateofPayment;
        }

        public void setDateofPayment(String dateofPayment) {
            this.dateofPayment = dateofPayment;
        }

        public String getCid() {
            return cid;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public String toString() {
            return " Num Of Records:" + this.getNumberOfRecords()+ " Total Amount:" + this.getTotalAmount() + " Average Amount:" + this.getAvPrice()+" Company Name:" + this.getCompanyName();
        }

    }
}
