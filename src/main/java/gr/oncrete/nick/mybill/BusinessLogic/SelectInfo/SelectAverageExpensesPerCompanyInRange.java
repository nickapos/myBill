/*
 * Copyright (C) 2018 nickapos
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
import com.google.common.collect.Lists;
import gr.oncrete.nick.mybill.RDBMS.SelectFromTable;
import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
/**
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
    private String sql4 = " group by b.companyname,b.cid";
    private String orderByNumOfRecords = " order by numberOfRecords desc";
    private String orderByAmount = " order by total desc";
    private String orderByAverage = " order by average desc";
    private SelectFromTable sel = new SelectFromTable();

    public SelectAverageExpensesPerCompanyInRange() {

    }

    public SelectAverageExpensesPerCompanyInRange(String startPeriod, String endPeriod) {
        String sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4;
        this.splitResults(sql);
    }

    public SelectAverageExpensesPerCompanyInRange(String startPeriod, String endPeriod, String orderBy) {
        String sql = "";
        if (orderBy.equals("NumOfRecords")) {
            sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4 + orderByNumOfRecords;
        } else if (orderBy.equals("Amount")) {
            sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4 + orderByAmount;
        } else if (orderBy.equals("Average")) {
            sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4 + orderByAverage;
        } else {
            sql = sql1 + sql2 + startPeriod + "'" + sql3 + endPeriod + "'" + sql4 + orderByAmount;
        }
        this.splitResults(sql);
    }

    private void splitResults(String sql) {
        List<String> a = sel.executeQuery(sql);
        List<List<String>> partitionedList = Lists.partition(a, 5);
        analyticsRecordList = partitionedList.stream().map(n -> new AnalyticsRecord(n.get(0), n.get(1), n.get(2), n.get(3), n.get(4))).collect(Collectors.toList());
    }

    public String toString() {
        String results = analyticsRecordList.stream().map(n -> n.toString()).collect(Collectors.joining("\n"));
        return results;
    }

    public List<String> getCidList() {
        List<String> cidList = analyticsRecordList.stream().map(n -> n.getCid()).collect(Collectors.toList());
        return cidList;
    }

    public List getAnalyticsRecordList() {
        return analyticsRecordList;
    }

    public class AnalyticsRecord {

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
            return new DecimalFormat("####.##").format(this.getAvPriceDouble());
        }

        public double getAvPriceDouble() {
            return Double.valueOf(avPrice);
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
            return String.format(" Num Of Records: %s Total Amount: %s  Average Amount: %s  Company Name: %s", this.getNumberOfRecords(), this.getTotalAmount(), this.getAvPrice(), this.getCompanyName());
        }

    }
}
