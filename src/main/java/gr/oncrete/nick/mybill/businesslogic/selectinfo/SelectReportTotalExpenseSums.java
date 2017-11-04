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
package gr.oncrete.nick.mybill.businesslogic.selectinfo;

import java.text.DecimalFormat;

/**
 *
 * @author nickapos
 */
public class SelectReportTotalExpenseSums extends SelectAllIDS {

    String sql1 = "select sum(price),companyname,afm from bills, companies where bills.cid=companies.cid group by companies.companyname, companies.afm order by sum(price)";

    /**
     *
     */
    public SelectReportTotalExpenseSums() {
        super.runQuery(sql1);
    }

    /**
     * This method returns the properly formated data from the query in order to
     * fit in the jtable
     *
     *
     * @return
     */
    public String[][] getColumns() {
        //fill the result jtable without the percentage
        int idListSizeNumByThree = this.getIds().size() / 3;
        String[][] array = new String[idListSizeNumByThree + 2][4];
        float fullSum = 0;
        float percentage = 0;
        for (int i = 0, o = 0; i < this.getIds().size(); i += 3, o++) {
            String priceS = (String) this.getIds().get(i);
            fullSum += Float.valueOf(priceS.trim()).floatValue();
            String[] row = {"" + (new DecimalFormat("###,###.##").format((double) (Float.valueOf(priceS.trim()).floatValue()))), "0", (String) this.getIds().get(i + 1), (String) this.getIds().get(i + 2)};
            array[o] = row;

        }
        //fill the percentage of the jtable
        for (int i = 0, o = 0; i < this.getIds().size(); i += 3, o++) {
            String priceS = (String) this.getIds().get(i);
            percentage += (Float.valueOf(priceS.trim()).floatValue() / fullSum) * 100;
            array[o][1] = "" + (new DecimalFormat("###,###.##").format((double) (Float.valueOf(priceS.trim()).floatValue() / fullSum) * 100)) + "%";
        }

        String[] header = {"Full Sum", "---", "---", "number of Records"};
        array[idListSizeNumByThree] = header;
        String[] value = {"" + new DecimalFormat("###,###.##").format(fullSum), "" + new DecimalFormat("###,###.##").format(percentage) + "%", "", "" + (array.length - 2)};
        array[(idListSizeNumByThree + 1)] = value;
        return array;
    }
}
