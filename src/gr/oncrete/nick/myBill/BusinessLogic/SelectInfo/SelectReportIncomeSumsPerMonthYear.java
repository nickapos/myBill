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
package gr.oncrete.nick.myBill.BusinessLogic.SelectInfo;


import gr.oncrete.nick.myBill.BusinessLogic.PlotDataObject;


/**
 *
 * @author nickapos
 */
public class SelectReportIncomeSumsPerMonthYear extends SelectReportExpensesSumsPerMonthYear {
    //String sql="select sum(price),month(dayofpayment) from bills where dayofpayment >= '2010-01-01' and dayofpayment <= '2010-12-31'  group by month(dayofpayment) order by month(dayofpayment)";

    /**
     *
     * @param year
     */
    public SelectReportIncomeSumsPerMonthYear(String year) {
        this.constructObject(year);
    }

    private void constructObject(String year) {
        plotData = new PlotDataObject("Months", "Amount", "Income Per Month");
        String sqla = "select sum(amount),month(dateofpayment) from income where dateofpayment >= '";
        String sqlb = "-01-01' and dateofpayment <= '";
        String sqlc = "-12-31'  group by month(dateofpayment) order by month(dateofpayment)";
        sql = sqla + year + sqlb + year + sqlc;
        //System.out.println(sql);
        super.runQuery(sql);
    }
}
