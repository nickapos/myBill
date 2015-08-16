/*
 *This class will be used to invert records. From income to expenses and vice versa
 */
package gr.oncrete.nick.mybill.BusinessLogic;

/**
 *
 * @author nickapos
 */
public class InvertRecord {
    /**
        This method will invert an income record by creating the reverse expense record
    */
    public void invertIncome(){
        
    }
    
    /**
        This method will invert an expense record by creating the reverse income record
    */
    public void invertExpense(){
        
    }
    /**
     * this method will convert the incoming string to an integer record id
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
