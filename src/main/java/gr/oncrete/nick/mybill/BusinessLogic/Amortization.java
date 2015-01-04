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
package gr.oncrete.nick.myBill.BusinessLogic;

import java.text.*;

/**
 *This class will calculate the amortization when given the payout period, the interest rate and the initial amount
 *
 * pa=amount
 * c=amortization
 * ν=totalPaymentPeriod (in months)
 * r=rate for one year
 * rm=rate per month
 * am=amortizationPeriod the number of months between two amortization installments
 *
 * @author nickapos
 */
public class Amortization {

    private double lamount, lrate, lnumberOfMonths, lpaymentFrequency = 1,installment=-1;

    /**
     *
     *
     *
     * @param amount
     * @param totalPaymentPeriod in months
     * @param rate percent (e.g 10% without the percent sign)
     * paymentFrequency how many times do you pay per year.
     *
     * If you pay monthly then you pay 12
     * if you pay bimonthly then you pay 6
     * if you pay 3monthly then you pay 4
     * if you pay 4monthly then you pay 3
     * if you pay 6monthly then you pay 2
     * if you pay yearly then you pay 1
     */
    public Amortization(double amount, double totalPaymentPeriod, double rate, int paymentFrequency) {
        lamount = amount;
        lnumberOfMonths = totalPaymentPeriod;
        lrate = rate / 100;
        lpaymentFrequency = paymentFrequency;

    }

    public String calculateAmortization() {
        double arithmosPeriodwn = lnumberOfMonths * lpaymentFrequency / 12;
        double epitokioPeriodou = lrate / (arithmosPeriodwn / (lnumberOfMonths / 12));


        System.out.println("epitokio periodoy" + epitokioPeriodou);
        System.out.println("arithmos periodwn" + arithmosPeriodwn);
        double result = lamount / this.calcSyntelesti(epitokioPeriodou, arithmosPeriodwn);
        //save the result in the installment instance variable
        installment=result;
        //double result=pa*(r/(12/am))/(1-1/Math.pow((1+r/(12/am)),n));
        DecimalFormat df = new DecimalFormat("#############.##");
        //System.out.println("result " + df.format(result));
        return df.format(result);
    }

    double calcSyntelesti(double epitokioPeriodou, double arithmosPeriodwn) {
        double dynami = Math.pow((1 + epitokioPeriodou), arithmosPeriodwn);
        return (1 - (1 / dynami)) / epitokioPeriodou;
    }

    public double getAmount() {
        return lamount;
    }

    public double getRate() {
        return lrate;
    }

    public double getNumberOfMonths() {
        return lnumberOfMonths;
    }

    public double getPaymentFrequency() {
        return lpaymentFrequency;
    }

    public double getInstallment() {
        return installment;
    }
}
