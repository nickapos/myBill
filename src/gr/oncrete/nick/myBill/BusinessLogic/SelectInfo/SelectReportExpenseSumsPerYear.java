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


/**
 *
 * @author nickapos
 */
public class SelectReportExpenseSumsPerYear extends SelectReportTotalExpenseSums {
    String sql;
    /**
     *
     * @param year
     */
    public SelectReportExpenseSumsPerYear(String year)
    { String sql0="select sum(price),companyname,afm from bills, companies where bills.cid=companies.cid  and dayofpayment>='";
      String sql2="-01-01' and dayofpayment <='";
      String sql3="-12-31'  group by companies.companyname, companies.afm order by sum(price)";
      sql=sql0+year+sql2+year+sql3;
      //System.out.println(sql);
      super.runQuery(sql);
    }

}
