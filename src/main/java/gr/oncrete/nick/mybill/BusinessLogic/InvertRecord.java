/*
 *This class will be used to invert records. From income to expenses and vice versa
 */
package gr.oncrete.nick.mybill.BusinessLogic;

import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectBillDetails;
import gr.oncrete.nick.mybill.BusinessLogic.SelectInfo.SelectIncomeDetails;

/**
 *
 * @author nickapos
 */
public class InvertRecord {

    /**
     * This method will invert an income record by creating the reverse expense
     * record
     */
    public void invertIncome(int recordID) {
        SelectIncomeDetails incDet = new SelectIncomeDetails();
        incDet.SelectBillDetailsWithID("" + recordID);
        //insert the inverted new Bill
        InsertBills newBill= new InsertBills(incDet.getCID(),incDet.getAmount(), incDet.getDateOfPayment(),incDet.getDateOfPayment(),incDet.getComment());
    }

    /**
     * This method will invert an expense record by creating the reverse income
     * record
     */
    public void invertExpense(int recordID) {
        SelectBillDetails billDet = new SelectBillDetails();
        billDet.SelectBillDetailsWithID("" + recordID);
        
        InsertIncome newInc = new InsertIncome(billDet.getCID(),billDet.getPrice(),billDet.getDateOfPayment(),billDet.getComment());
    }

    /**
     * this method will convert the incoming string to an integer record id
     *
     * @param id
     * @return
     */
    public int convertID(String id) {
        if (id.length() > 0) {
            try {
                return Integer.parseInt(id);
            } catch (NumberFormatException e) {
                return -1;
            } catch (NullPointerException e) {
                return -2;
            }
        } else {
            return -3;
        }
    }
}
