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
 * InsertIntoTable.java
 *
 * Created on 12 Ιούλιος 2005, 11:27 πμ
 */
package gr.oncrete.nick.mybill.RDBMS;

import java.sql.SQLException;
import gr.oncrete.nick.mybill.UserInterface.PopupMessageFrame;

/**
 *
 * @author nickapos
 */
public class InsertIntoTable extends BasicTableOperation {

    boolean succesfullCompletion=false;

    /**
     * a constructor used only in cases when we want to present the error message.
     * for example when of the arguments of the insert command is missing
     */
    public InsertIntoTable()
    {
        
    }
    /** Creates a new instance of InsertIntoTable
     * @param query
     */
    public InsertIntoTable(String query) {
        super.initiateDBConnection();
        DatabaseConnection.setAutoCommitOff();
        try {
            DatabaseConnection.update(query);
            DatabaseConnection.commitTransaction();
            //PopupMessageFrame mes = new PopupMessageFrame();
            //mes.setNotification("Insertion Successfull!");
            succesfullCompletion=true;
        } catch (SQLException sqle) {
            this.warningPopUp();
            System.out.println("Rolling Back");
            DatabaseConnection.rollbackTransaction();
            System.out.println("Exception encountered. Insertion Cancelled");
            //sqle.printStackTrace();
        }
         //DatabaseConnection.shutdown ();
    }

    /**
     *
     * @return true if the insertion has completed sucesfully false if not
     */
    public boolean hasCompletedSucesfully()
    {
        return succesfullCompletion;
    }

    /**this method will present a generic message with a warning that something has gone awry with the
     * insertion of data
     */
    public void warningPopUp()
    {
        PopupMessageFrame mes = new PopupMessageFrame();
            mes.setWarning(java.util.ResourceBundle.getBundle("i18n/myBillUIBundle").getString("SOMETHING-WENT-WRONG-PLEASE-TRY-AGAIN!"));
    }

    /**this method will present a specific message with a warning that something has gone awry with the
     * insertion of data
     */
    public void warningPopUp(String message)
    {
        PopupMessageFrame mes = new PopupMessageFrame();
            mes.setWarning(message);
    }
}
