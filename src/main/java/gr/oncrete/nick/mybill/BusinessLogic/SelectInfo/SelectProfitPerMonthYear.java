/*
 *  Copyright (C) 2010 nickapos
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package gr.oncrete.nick.mybill.BusinessLogic.SelectInfo;

import gr.oncrete.nick.mybill.BusinessLogic.PlotDataObject;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;

/**
 *
 * This class will use one SelectReportIncomeSumsPerMonthYear oject and one
 * SelectReportexpensesSumsPerMonthYear. subract row by row for each month income-expenses
 * and prepare the data for both textual and graphic representation
 *
 * @author nickapos 5 Αυγ 2010
 */
public class SelectProfitPerMonthYear {

    SelectReportIncomeSumsPerMonthYear income;
    SelectReportExpensesSumsPerMonthYear expenses;
    String[][] profitColumns, incomeColumns, expensesColumns, paddedIncomeColumns, paddedexpensesColumns;
    PlotDataObject plotData = new PlotDataObject(java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("MONTHS"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("AMOUNT"), java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("PROFIT OR LOSS PER MONTH"));
    int arraySize;

    /**
     *
     */
    public SelectProfitPerMonthYear() {
    }

    /**
     *
     * @param year
     */
    public SelectProfitPerMonthYear(String year) {
        income = new SelectReportIncomeSumsPerMonthYear(year);
        expenses = new SelectReportExpensesSumsPerMonthYear(year);
        if (!this.isEmpty()) {
            incomeColumns = income.getColumnsWithMonthsAsNumbers();
            expensesColumns = expenses.getColumnsWithMonthsAsNumbers();
            this.padArrays();
        }
    }

    /**
     *
     * this method will pad the incomeColumns and the expensesColumns so that they
     * will get to 12 size (the number of months) with 0 in the months with no
     * data
     */
    public void padArrays() {
        profitColumns = new String[14][3];
        paddedIncomeColumns = new String[12][3];
        paddedexpensesColumns = new String[12][3];

        for (int i = 0; i < 12; i++) {
            paddedIncomeColumns[i] = new String[]{"0.0", "0.0", "" + i};
            paddedexpensesColumns[i] = new String[]{"0.0", "0.0", "" + i};
            profitColumns[i] = new String[]{"0,0", "0,0", "" + i};
        }
        profitColumns[12] = new String[]{"0.0", "0.0", "0"};
        profitColumns[13] = new String[]{"0.0", "0.0", "0"};
        for (int i = 0; i < incomeColumns.length - 2; i++) {

            int monthNum = Integer.valueOf(incomeColumns[i][2]).intValue();
            if (monthNum < 0) {
                monthNum = 0;
            }
            paddedIncomeColumns[monthNum][0] = incomeColumns[i][0];
            paddedIncomeColumns[monthNum][1] = incomeColumns[i][1];
            paddedIncomeColumns[monthNum][2] = incomeColumns[i][2];
        }
        for (int i = 0; i < expensesColumns.length - 2; i++) {
            int monthNum = Integer.valueOf(expensesColumns[i][2]).intValue();
            if (monthNum < 0) {
                monthNum = 0;
            }
            paddedexpensesColumns[monthNum][0] = expensesColumns[i][0];
            paddedexpensesColumns[monthNum][1] = expensesColumns[i][1];
            paddedexpensesColumns[monthNum][2] = expensesColumns[i][2];
        }

        this.fillProfitColumns();
    }

    /**
     * do the appropriate data manipulation in order to fill the profit data array
     *
     */
    private void fillProfitColumns() {
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        double[] profperCent={0,0,0,0,0,0,0,0,0,0,0,0};//array to hold the profit values unformatted
        double totalSum = 0;
        for (int i = 0; i < profitColumns.length - 2; i++) {
            String incS = paddedIncomeColumns[i][0].trim();
            String expS = paddedexpensesColumns[i][0].trim();
            double inc = Float.valueOf(incS);
            double exp = Float.valueOf(expS);
            double profit;
            if ((inc == 0) && (exp == 0)) {
                profit = 0;
            } else {
                profit = inc - exp;
            }
            profperCent[i]=profit;
            totalSum = totalSum + profit;
            profitColumns[i][0] = "" + (new DecimalFormat("#,###.##").format(profit));//the profit
            //profitColumns[i][0] = "" + profit;//the profit
            int monthNum = Integer.valueOf(profitColumns[i][2]);
            profitColumns[i][2] = months[monthNum];
            plotData.addXaxisElementD((monthNum + 1));
            plotData.addYaxisElementD(profit);
        }

        for (int i = 0; i < profitColumns.length - 2; i++) {
            double percentage = (profperCent[i] / totalSum) * 100;
            profitColumns[i][1] = "" + (new DecimalFormat("0.##").format(percentage)) + "%";//the percentage
        }

        String[] header = {"Full Sum", "---", "number of Records"};
        String[] value = {"" + new DecimalFormat("#,###.##").format(totalSum), "---", "" + (profitColumns.length - 2)};
        profitColumns[profitColumns.length - 2] = header;
        profitColumns[profitColumns.length - 1] = value;
    }

    /**
     *
     *
     * @return true if the object has no data
     */
    public boolean isEmpty() {
        if (income.isEmpty() || expenses.isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     *
     *
     * @return the plot data
     */
    public PlotDataObject returnPlotData() {
        return plotData;
    }

    /**
     *
     *
     * @return re data column or an empty array if there are no data
     */
    public String[][] getColumns() {
        if (!this.isEmpty()) {
            return profitColumns;
        } else {
            String a[][] = {{"", "", ""}};
            return a;
        }
    }
}
