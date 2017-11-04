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
package gr.oncrete.nick.mybill.BusinessLogic;

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAllBillsIDS;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectAllIncomeDetails;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectBillDetails;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectCompanyDetails;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectIncomeDetails;
import gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo.UpdateBillRecord;
import gr.oncrete.nick.mybill.BusinessLogic.UpdateInfo.UpdateIncomeRecord;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;

/**
 * This class wil be used to change the currency of all the records of the
 * database from one to another
 *
 * @author nickapos 6 Σεπ 2010
 */
public class MassiveChangeCurrency {

    private final static Logger LOGGER = Logger.getLogger(MassiveChangeCurrency.class.getName());
    private String rateStr;
    private JLabel l;

    /**
     *
     * @param rateS
     */
    public MassiveChangeCurrency(String rateS, JLabel lbl) {
        l = lbl;
        this.setRateStr(rateS);
    }

    /**
     *
     */
    public MassiveChangeCurrency() {
    }

    /**
     * set rate string
     *
     * @param rateS
     */
    public void setRateStr(String rateS) {
        rateStr = rateS;
    }

    /**
     * get rate string
     *
     * @return
     */
    public String getRateStr() {
        return rateStr;
    }

    /**
     * this method will preform the actual conversion
     */
    public void changeCurrency() {
        if (rateStr.length() > 0) {
            double rate = Double.parseDouble(rateStr);

            //change the expenses
            SelectAllBillsIDS allids = new SelectAllBillsIDS();
            List idList = allids.getIds();
            Iterator it = idList.iterator();

            int counter = 0;
            String expensesProgressText = "";
            while (it.hasNext()) {
                SelectBillDetails bdt = new SelectBillDetails();
                bdt.SelectBillDetailsWithID((String) it.next());
                //get the price of the current bill
                String billPrice = bdt.getPrice();
                SelectCompanyDetails cdt = new SelectCompanyDetails();
                cdt.SelectCompanyDetailsWithID(bdt.getCID());
                //calculate the new price
                String newPrice = "" + Double.parseDouble(billPrice) * rate;
                UpdateBillRecord uptdBill = new UpdateBillRecord(bdt.getBID(), cdt.getName(), newPrice, bdt.getDateOfIssue(), bdt.getDateOfPayment(), bdt.getComment());
                LOGGER.log(Level.INFO, uptdBill.toString());
                counter++;
                expensesProgressText = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("NUMBER OF EXPENSES RECORDS CONVERTED : ") + counter;
                l.setText(expensesProgressText);
            }

            //change the income
            SelectAllIncomeDetails incRecors = new SelectAllIncomeDetails();
            List inciDList = incRecors.getAllRows();
            counter = 0;
            while (counter <= inciDList.size()) {
                String[] record = (String[]) inciDList.get(counter);
                counter++;
                SelectIncomeDetails indet = new SelectIncomeDetails();
                indet.SelectBillDetailsWithID(record[0]);
                String incPriceStr = indet.getAmount();
                String newPrice = "" + Double.parseDouble(incPriceStr) * rate;
                int companyID = Integer.parseInt(indet.getCID());
                UpdateIncomeRecord upinr = new UpdateIncomeRecord(indet.getInID(), companyID, newPrice, indet.getDateOfPayment(), indet.getComment());
                LOGGER.log(Level.INFO, upinr.toString());
                String incomeProgressText = java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" INCOME RECORDS CONVERTED : ") + counter;
                l.setText(expensesProgressText + incomeProgressText);
            }

        }
    }
}
