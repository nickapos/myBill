/*
 *  Copyright (C) 2011 nickapos
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
package gr.oncrete.nick.mybill.businesslogic;

import java.text.*;

/**
 * This class will accept an amortization object and create the amortization
 * table based on that object.
 *
 * The technique that will be used is the following. Supposedly we have a loan
 * of capital 10.000 euro an interest rate 10% a payment period of 24 months and
 * a frequency of payment monthly. The first installment calculated by the
 * amortization will be somethind like 460,62euro
 *
 * the part of this installment that corresponds the the interest can be
 * calculated with the following formula (capital*interest rate)/365 is the
 * daily interest. when multiplied by 30 we get the interest part of the
 * installment of the monthly installment so (capital*interest rate)*30/365 is
 * the interest the amount of the capital is installment-(capital*interest
 * rate)*30/365
 *
 * we calculate the remaining capital by substracting the initial capital from
 * installment-(capital*interest rate)*30/365 and repeat the process for all the
 * payment period
 *
 * @author nickapos
 */
public class AmortizationTable {

    double initialAmount, remainingAmount, rate, numberOfMonths, paymentFrequency = 1, installment, interestPerDay, totalInterest = 0, totalCapital = 0;
    int DAYSPERMONTH = 30, daysOfPeriod, installmentDuration = -1;
    String amortizationTableString = "";

    /**
     *
     *
     *
     * @param amount
     * @param totalPaymentPeriod in months
     * @param rate percent (e.g 10% without the percent sign) paymentFrequency
     * how many times do you pay per year.
     *
     * If you pay monthly then you pay 12 if you pay bimonthly then you pay 6 if
     * you pay 3monthly then you pay 4 if you pay 4monthly then you pay 3 if you
     * pay 6monthly then you pay 2 if you pay yearly then you pay 1
     */
    public AmortizationTable(String tableHeader, Amortization a) {
        amortizationTableString += tableHeader + "\n\n\n";
        amortizationTableString = amortizationTableString + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("NO") + "\t" + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" INSTALMENT") + "\t" + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" REM CAPITAL") + "\t" + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" INST CAPITAL") + "\t" + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString(" INST INTEREST") + "\n";
        amortizationTableString = amortizationTableString + "----------------------------------------------------------------";
        a.calculateAmortization();
        initialAmount = remainingAmount = a.getAmount();
        rate = a.getRate();
        numberOfMonths = a.getNumberOfMonths();
        paymentFrequency = a.getPaymentFrequency();
        installment = a.getInstallment();
        if (paymentFrequency == 12) {
            installmentDuration = 1;
        } else if (paymentFrequency == 6) {
            installmentDuration = 2;
        } else if (paymentFrequency == 4) {
            installmentDuration = 3;
        } else if (paymentFrequency == 3) {
            installmentDuration = 4;
        } else if (paymentFrequency == 2) {
            installmentDuration = 6;
        } else if (paymentFrequency == 1) {
            installmentDuration = 12;
        }
        daysOfPeriod = DAYSPERMONTH * installmentDuration;
        this.calculateAmortizationTable();
    }

    private void calculateAmortizationTable() {
        DecimalFormat df = new DecimalFormat("#,###,###,###,###.##");
        int i = 1;
        int loops = (int) (numberOfMonths / installmentDuration);
        while (i <= loops) {
            interestPerDay = (remainingAmount * rate) / 365;
            double installmentInterest = interestPerDay * daysOfPeriod;
            double installmentCapital = installment - installmentInterest;
            totalInterest += installmentInterest;
            totalCapital += installmentCapital;
            remainingAmount = remainingAmount - installmentCapital;

            amortizationTableString = amortizationTableString + "\n" + i + "\t" + df.format(installment) + "\t" + df.format(remainingAmount) + "\t" + df.format(installmentCapital) + "\t" + df.format(installmentInterest);
            i++;
            if (remainingAmount < installment) {
                installment = remainingAmount;
            }

        }
        amortizationTableString = amortizationTableString + "\n" + "\n" + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("TOTAL INTEREST PAID:") + df.format(totalInterest) + "\n" + java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("TOTAL CAPITAL PAID:") + df.format(initialAmount);
    }

    /**
     * This method will return the whole amortization table as a properly
     * formated string
     *
     * @return
     */
    public String amortizationTableString() {
        return amortizationTableString;
    }
}
