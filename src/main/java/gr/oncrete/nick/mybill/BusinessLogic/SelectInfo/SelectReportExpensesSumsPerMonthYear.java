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

import java.text.DecimalFormat;
import java.text.DateFormatSymbols;
import gr.oncrete.nick.myBill.BusinessLogic.PlotDataObject;
import gr.oncrete.nick.mybill.UserInterface.PlotWindow;

/**
 *
 * @author nickapos
 */
public class SelectReportExpensesSumsPerMonthYear extends SelectAllIDS {
    //String sql="select sum(price),month(dayofpayment) from bills where dayofpayment >= '2010-01-01' and dayofpayment <= '2010-12-31'  group by month(dayofpayment) order by month(dayofpayment)";

    String sql;
    PlotDataObject plotData = new PlotDataObject(java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("MONTHS"), java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("AMOUNT"), java.util.ResourceBundle.getBundle("gr/oncrete/nick/myBill/UserInterface/myBillUIBundle").getString("EXPENSES PER MONTH"));

    /**
     *
     */
    public SelectReportExpensesSumsPerMonthYear() {
    }

    /**
     *
     * @param year
     */
    public SelectReportExpensesSumsPerMonthYear(String year) {
        String sqla = "select sum(price),month(dayofpayment) from bills where dayofpayment >= '";
        String sqlb = "-01-01' and dayofpayment <= '";
        String sqlc = "-12-31'  group by month(dayofpayment) order by month(dayofpayment)";
        sql = sqla + year + sqlb + year + sqlc;
        //System.out.println(sql);
        super.runQuery(sql);
    }

    /**
     * This method returns the properly formated data from the query in order to fit in the jtable
     *
     *
     * @return
     */
    public String[][] getColumns() {



        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        //fill the result jtable without the percentage
        int idListSizeNumByTwo = this.getIds().size() / 2;
        String[][] array = new String[idListSizeNumByTwo + 2][3];
        
        float fullSum = 0;
        float totalPercentage = 0;
        for (int i = 0, o = 0; i < this.getIds().size(); i += 2, o++) {
            String priceS = (String) this.getIds().get(i);
            fullSum += Float.valueOf(priceS.trim()).floatValue();
            int monthNum = Integer.valueOf((String) this.getIds().get(i + 1)) - 1;
            plotData.addXaxisElementD((monthNum + 1));
            plotData.addYaxisElementD((double) (Float.valueOf(priceS.trim()).floatValue()));
            String[] row = {"" + (new DecimalFormat("#,###.##").format((double) (Float.valueOf(priceS.trim()).floatValue()))), "0", months[monthNum]};
            array[o] = row;
            

        }
        //fill the percentage of the jtable
        for (int i = 0, o = 0; i < this.getIds().size(); i += 2, o++) {
            String priceS = (String) this.getIds().get(i);
            totalPercentage += (Float.valueOf(priceS.trim()).floatValue() / fullSum) * 100;
            double percentage = (double) (Float.valueOf(priceS.trim()).floatValue() / fullSum) * 100;
            //plotData.addYaxisElement( ""+percentage);//if we want to present persentage and not actual values
            array[o][1] = "" + (new DecimalFormat("#,###.##").format(percentage)) + "%";
        }


        String[] header = {"Full Sum", "---", "number of Records"};
        String[] value = {"" + new DecimalFormat("###,###.##").format(fullSum), "" + new DecimalFormat("#,###.##").format(totalPercentage) + "%", "" + (array.length - 2)};
        if (this.isEmpty()) {
            idListSizeNumByTwo = 0;
        }
        array[idListSizeNumByTwo] = header;
        array[idListSizeNumByTwo + 1] = value;

        //create the plot
        //PlotWindow pW = new PlotWindow();
        //draw the plot
        //pW.plotDoubleXAxis(plotData);
        return array;
    }

    /**
     * This method returns the properly formated data from the query in order to fit in the jtable
     * with a difference that the column list is filled in with the month number and not the month name
     *
     * @return
     */
    public String[][] getColumnsWithMonthsAsNumbers() {



        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        //fill the result jtable without the percentage
        int idListSizeNumByTwo = this.getIds().size() / 2;
        String[][] array = new String[idListSizeNumByTwo + 2][3];

        float fullSum = 0;
        float totalPercentage = 0;
        for (int i = 0, o = 0; i < this.getIds().size(); i += 2, o++) {
            String priceS = (String) this.getIds().get(i);
            fullSum += Float.valueOf(priceS.trim()).floatValue();
            int monthNum = Integer.valueOf((String) this.getIds().get(i + 1)) - 1;
            plotData.addXaxisElementD((monthNum + 1));
            plotData.addYaxisElementD((double) (Float.valueOf(priceS.trim()).floatValue()));
            String[] row = {priceS, "0", ""+monthNum};
            array[o] = row;

        }
        //fill the percentage of the jtable
        for (int i = 0, o = 0; i < this.getIds().size(); i += 2, o++) {
            String priceS = (String) this.getIds().get(i);
            totalPercentage += (Float.valueOf(priceS.trim()).floatValue() / fullSum) * 100;
            double percentage = (double) (Float.valueOf(priceS.trim()).floatValue() / fullSum) * 100;
            //plotData.addYaxisElement( ""+percentage);//if we want to present persentage and not actual values
            array[o][1] = "" + (new DecimalFormat("#,###.##").format(percentage)) + "%";
        }


        String[] header = {"Full Sum", "---", "number of Records"};
        String[] value = {"" + new DecimalFormat("###,###.##").format(fullSum), "" + new DecimalFormat("#,###.##").format(totalPercentage) + "%", "" + (array.length - 2)};
        if (this.isEmpty()) {
            idListSizeNumByTwo = 0;
        }
        array[idListSizeNumByTwo] = header;
        array[idListSizeNumByTwo + 1] = value;

        //create the plot
        //PlotWindow pW = new PlotWindow();
        //draw the plot
        //pW.plotDoubleXAxis(plotData);
        return array;
    }

    /**
     *
     * @return
     */
    public PlotDataObject returnPlotData() {
        return plotData;
    }
}
