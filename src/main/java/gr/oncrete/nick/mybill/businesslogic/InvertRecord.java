/*
 *This class will be used to invert records. From income to expenses and vice versa
 */
package gr.oncrete.nick.mybill.businesslogic;

import gr.oncrete.nick.mybill.businesslogic.selectinfo.SelectBillDetails;
import gr.oncrete.nick.mybill.businesslogic.selectinfo.SelectIncomeDetails;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nickapos
 */
public class InvertRecord {

    private final static Logger LOGGER = Logger.getLogger(InvertRecord.class.getName());

    /**
     * This method will invert an income record by creating the reverse expense
     * record
     *
     * @param recordID
     */
    public void invertIncome(int recordID) {
        SelectIncomeDetails incDet = new SelectIncomeDetails();
        incDet.SelectBillDetailsWithID("" + recordID);

        //insert the inverted new Bill
        InsertBills newBill = new InsertBills(Integer.parseInt(incDet.getCID()), incDet.getAmount(), incDet.getDateOfPayment(), incDet.getDateOfPayment(), incDet.getComment());
        LOGGER.log(Level.INFO, newBill.toString());
    }

    /**
     * This method will invert an expense record by creating the reverse income
     * record
     *
     * @param recordID
     */
    public void invertExpense(int recordID) {
        SelectBillDetails billDet = new SelectBillDetails();
        billDet.SelectBillDetailsWithID("" + recordID);
        //System.out.println(billDet.toString());
        InsertIncome newInc = new InsertIncome(Integer.parseInt(billDet.getCID()), billDet.getPrice(), billDet.getDateOfPayment(), billDet.getComment());
        System.out.println(newInc.toString());
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
            }
            catch (NumberFormatException e) {
                return -1;
            }
            catch (NullPointerException e) {
                return -2;
            }
        } else {
            return -3;
        }
    }
}
